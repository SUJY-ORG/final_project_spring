package com.ssafy.web.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public int signup(UserDto user);
}
