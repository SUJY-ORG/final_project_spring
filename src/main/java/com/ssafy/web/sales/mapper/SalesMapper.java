package com.ssafy.web.sales.mapper;

import com.ssafy.web.sales.dto.Sale;
import com.ssafy.web.sales.dto.SalesThumbnailResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface SalesMapper {
    List<SalesThumbnailResponseDto> getSalesThumbnailList(@Param("keyword") String keyword);

    int registSales(@Param("sale") Sale sale);

    Sale saleInfo(@Param("saleId") String saleId);
}
