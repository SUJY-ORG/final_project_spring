package com.ssafy.web.sales.mapper;

import com.ssafy.web.sales.dto.SalesThumbnailResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface SalesMapper {
    List<SalesThumbnailResponseDto> getAllSalesThumbnailList();
}
