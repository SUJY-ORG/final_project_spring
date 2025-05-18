package com.ssafy.web.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
    private String serviceId;
    private String password;
}
