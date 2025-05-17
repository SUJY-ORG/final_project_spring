package com.ssafy.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long userId;
	private String userServiceId;
	private String password;
	private Integer age;
	private String gender;
	private String tendency;
	
	public UserDto(SignupRequestUserDto signupRequestUserDto) {
		this.userServiceId = signupRequestUserDto.getUserServiceId();
		this.password = signupRequestUserDto.getPassword();
		this.age = signupRequestUserDto.getAge();
		this.gender = signupRequestUserDto.getGender();
		this.tendency = signupRequestUserDto.getTendency();
	}
}
