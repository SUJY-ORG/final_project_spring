package com.ssafy.web.user.service;

import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.mapper.UserMapper;
import com.ssafy.web.util.Argon2Hash;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;

	public int signup(UserDto dto) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		// 비밀번호 암호화 (약 2초 소요)
		byte[] salt = Argon2Hash.createSalt();
		String hashValue = Argon2Hash.createHash(salt, dto.getPassword());
		dto.setPassword(hashValue);
		
		long left = System.currentTimeMillis() - startTime;
		long delay = 3000 - left;
		
		if (delay > 0) {
			Thread.sleep(delay);
		}

		return userMapper.signup(dto);
	}

	public UserDto login(String serviceId, String password) {
		return userMapper.login(serviceId, password);
	}
	
}
