package com.ssafy.web.user.mapper.primary;

import com.ssafy.web.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	int signup(UserDto user);
	UserDto login(
		@Param("serviceId") String serviceId
	);
}
