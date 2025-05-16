package com.ssafy.web.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	
	public int signup(UserDto dto) {
		return userMapper.signup(dto);
	}
	
}
