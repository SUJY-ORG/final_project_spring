package com.ssafy.web.map;

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
