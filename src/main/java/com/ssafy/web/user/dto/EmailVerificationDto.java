package com.ssafy.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationDto {
    private String email;
    private String authCode;
}
