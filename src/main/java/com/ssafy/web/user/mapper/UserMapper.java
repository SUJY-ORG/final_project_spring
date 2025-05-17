package com.ssafy.web.user.mapper;

import com.ssafy.web.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public int signup(UserDto user);
}
