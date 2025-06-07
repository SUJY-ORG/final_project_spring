package com.ssafy.web.user.service;

import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.error.LoginException;
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
	private final int MAX_DELAY = 2000;
	
	public boolean signup(UserDto dto) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		byte[] salt = Argon2Hash.createSalt();
		String hashValue = Argon2Hash.createHash(salt, dto.getPassword());
		dto.setPassword(hashValue);
		
		int signupResult = userMapper.signup(dto);
		int addSaltResult = saltMapper.addUserSalt(dto.getUserId(), salt);
		
		long timeLeft = System.currentTimeMillis() - startTime;
		long delay = MAX_DELAY - timeLeft;

		if (delay > 0) {
			Thread.sleep(delay);
		}

		return (signupResult == 1 && addSaltResult == 1);
	}

	public UserDto login(String serviceId, String password) throws InterruptedException, LoginException {
		long startTime = System.currentTimeMillis();

		UserDto loginUser = userMapper.login(serviceId);

		if (loginUser == null) {
			Thread.sleep(getDelayMillis(startTime));
			throw new LoginException("로그인 실패");
		}

		if (loginUser.getFailCnt() == 5) {
			Thread.sleep(getDelayMillis(startTime));
			throw new LoginException("비밀번호 실패 횟수 제한에 도달해 계정이 잠금되었습니다. 관리자에게 연락해주세요. 010-1234-5678");
		}

		Map<String, Object> result = saltMapper.findUserSaltById(loginUser.getUserId());
		byte[] findSalt = (byte[]) result.get("salt");
		if (findSalt == null) {
			Thread.sleep(getDelayMillis(startTime));
			throw new LoginException("로그인 실패");
		}

		String validationHashValue = Argon2Hash.createHash(findSalt, password);
		if (validationHashValue.equals(loginUser.getPassword())) {
			Thread.sleep(getDelayMillis(startTime));
			return loginUser;
		} else {
			Thread.sleep(getDelayMillis(startTime));
			userMapper.addFailCnt(loginUser.getUserId());
			throw new LoginException("로그인 실패");
		}
	}

	private long getDelayMillis(long startTime) {
		long left = System.currentTimeMillis() - startTime;
		long delay = MAX_DELAY - left;
		return (delay > 0) ? delay : 0;
	}

}
