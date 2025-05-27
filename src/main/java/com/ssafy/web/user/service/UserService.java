package com.ssafy.web.user.service;

import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.mapper.primary.UserMapper;
import com.ssafy.web.user.mapper.secondary.SaltMapper;
import com.ssafy.web.util.Argon2Hash;

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

	public UserDto login(String serviceId, String password) {
		UserDto loginUser = userMapper.login(serviceId);
		System.out.println("loginUser = " + loginUser);

		// 계정이 존재하지 않을 때에도 비밀번호 해싱
		if (loginUser == null) {
			Argon2Hash.createHash(emptySalt, password);
			return null;
		}

		Map<String, Object> result = saltMapper.findUserSaltById(loginUser.getUserId());
		byte[] findSalt = (byte[]) result.get("salt");
		if (findSalt == null) return null;
		
		String validationHashValue = Argon2Hash.createHash(findSalt, password);
		System.out.println("validationHashValue = " + validationHashValue);
		if (validationHashValue.equals(loginUser.getPassword())) {
			return loginUser;
		} else {
			return null;
		}
	}
	
}
