package com.ssafy.web.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesThumbnailResponseDto {
    private Long registered_sales_id;
    private String apt_name;
    private String price;
    private String address;
}
