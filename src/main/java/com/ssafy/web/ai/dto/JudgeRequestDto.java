package com.ssafy.web.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeRequestDto {
    private String hospital;
    private String convenience_store;
    private String education;
    private String restaurant;
    private String location;
}
