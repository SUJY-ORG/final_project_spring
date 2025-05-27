package com.ssafy.web.sales.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Sale {
    private Long registered_sales_id;
    private String apt_name;
    private String price;
    private String address;
    private String user_id;
    private double lat;
    private double lng;
}