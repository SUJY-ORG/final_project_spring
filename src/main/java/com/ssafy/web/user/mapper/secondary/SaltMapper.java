package com.ssafy.web.user.mapper.secondary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SaltMapper {
	int addUserSalt(
		@Param("userId") Long userId, 
		@Param("salt") String salt
	);
}
