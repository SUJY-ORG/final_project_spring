package com.ssafy.web.sales.service;

import com.ssafy.web.sales.dto.Sale;
import com.ssafy.web.sales.dto.SalesThumbnailResponseDto;
import com.ssafy.web.sales.mapper.SalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesMapper salesMapper;

    public List<SalesThumbnailResponseDto> getSalesThumbnailList(String keyword) {
        return salesMapper.getSalesThumbnailList(keyword);
    }

    public int registSales(Sale sale){
        return salesMapper.registSales(sale);
    }

    public Sale saleInfo(String saleId) {
        return salesMapper.saleInfo(saleId);
    }
}
