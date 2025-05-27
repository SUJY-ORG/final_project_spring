package com.ssafy.web.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesThumbnailResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String address;
}
