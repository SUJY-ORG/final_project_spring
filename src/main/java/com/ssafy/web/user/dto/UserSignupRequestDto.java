package com.ssafy.web.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
	private String serviceId;
	private String password;
	private String email;
	private String preference;
}
