package com.ssafy.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long userId;
	private String serviceId;
	private String password;
	private String email;
	private String preference;
	
	public UserDto(UserSignupRequestDto userSignupRequestDto) {
		this.serviceId = userSignupRequestDto.getServiceId();
		this.password = userSignupRequestDto.getPassword();
		this.email = userSignupRequestDto.getEmail();
		this.preference = userSignupRequestDto.getPreference();
	}
}
