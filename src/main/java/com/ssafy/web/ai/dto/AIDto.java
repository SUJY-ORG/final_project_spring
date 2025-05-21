package com.ssafy.web.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AIDto {
    private Integer hospital;
    private Integer education;
    private Integer store;
    private Integer restaurant;
}
