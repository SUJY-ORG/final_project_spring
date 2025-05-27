package com.ssafy.web.user.service;

import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.mapper.primary.UserMapper;
import com.ssafy.web.user.mapper.secondary.SaltMapper;
import com.ssafy.web.util.Argon2Hash;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	private final SaltMapper saltMapper;
	private final byte[] emptySalt = new byte[16];
	
	public int signup(UserDto dto) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		// 비밀번호 암호화 (약 0.55초 소요)
		byte[] salt = Argon2Hash.createSalt();
		String hashValue = Argon2Hash.createHash(salt, dto.getPassword());
		dto.setPassword(hashValue);
		
		// salt 저장
		int result1 = userMapper.signup(dto);
		int result2 = saltMapper.addUserSalt(dto.getUserId(), salt);
		
		long left = System.currentTimeMillis() - startTime;
		long delay = 1000 - left;

		if (delay > 0) {
			Thread.sleep(delay);
		}

		return (result1 == 1 && result2 == 1) ? 1 : 0;
	}

	public Map<String, Object> login(String serviceId, String password) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		Map<String, Object> mp = new HashMap<>();
		UserDto loginUser = userMapper.login(serviceId);
		System.out.println("loginUser = " + loginUser);

		if (loginUser == null) {
			Thread.sleep(getDelayMillis(startTime));
			mp.put("result", "로그인 실패");
			return mp;
		}

		if (loginUser.getFailCnt() == 5) {
			Thread.sleep(getDelayMillis(startTime));
			mp.put("result", "비밀번호 실패 횟수 제한에 도달해 계정이 잠금되었습니다. 관리자에게 연락해주세요. 010-1234-5678");
			return mp;
		}

		Map<String, Object> result = saltMapper.findUserSaltById(loginUser.getUserId());
		byte[] findSalt = (byte[]) result.get("salt");
		if (findSalt == null) {
			Thread.sleep(getDelayMillis(startTime));
			mp.put("result", "로그인 실패");
			return mp;
		}
		
		String validationHashValue = Argon2Hash.createHash(findSalt, password);
		if (validationHashValue.equals(loginUser.getPassword())) {
			Thread.sleep(getDelayMillis(startTime));
			mp.put("result", "로그인 성공");
			mp.put("user", loginUser);
			return mp;
		} else {
			Thread.sleep(getDelayMillis(startTime));
			userMapper.addFailCnt(loginUser.getUserId());
			System.out.println("fail cnt 증가" + loginUser.getFailCnt());
			mp.put("result", "로그인 실패");
			return mp;
		}
	}

	private long getDelayMillis(long startTime) {
		long left = System.currentTimeMillis() - startTime;
		long delay = 2000 - left;
		return (delay > 0) ? delay : 0;
	}
	
}
