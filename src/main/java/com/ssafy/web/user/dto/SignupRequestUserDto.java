package com.ssafy.web.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestUserDto {
	private String userServiceId;
	private String password;
	private Integer age;
	private String gender;
	private String tendency;
}
