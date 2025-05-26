package com.ssafy.web.user.service;

import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.mapper.primary.UserMapper;
import com.ssafy.web.user.mapper.secondary.SaltMapper;
import com.ssafy.web.util.Argon2Hash;

import java.util.Base64;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	private final SaltMapper saltMapper;
	
	public int signup(UserDto dto) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		// 비밀번호 암호화 (약 0.55초 소요)
		byte[] salt = Argon2Hash.createSalt();
		String hashValue = Argon2Hash.createHash(salt, dto.getPassword());
		dto.setPassword(hashValue);
		
		// salt 저장
		int result1 = userMapper.signup(dto);
		int result2 = saltMapper.addUserSalt(dto.getUserId(), Base64.getEncoder().encodeToString(salt));
		
		long left = System.currentTimeMillis() - startTime;
		long delay = 1000 - left;
		
		if (delay > 0) {
			Thread.sleep(delay);
		}

		return (result1 == 1 && result2 == 1) ? 1 : 0;
	}

	public UserDto login(String serviceId, String password) {
		return userMapper.login(serviceId, password);
	}
	
}
