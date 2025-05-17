package com.ssafy.web.user.service;

import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.mapper.UserMapper;
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
