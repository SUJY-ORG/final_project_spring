package com.ssafy.web.sales.controller;

import com.ssafy.web.sales.dto.Sale;
import com.ssafy.web.sales.dto.SalesThumbnailResponseDto;
import com.ssafy.web.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    @GetMapping("/getSalesThumbnailList")
    public ResponseEntity<List<SalesThumbnailResponseDto>> getSalesThumbnailList(@RequestParam String keyword){
        List<SalesThumbnailResponseDto> list = salesService.getSalesThumbnailList(keyword);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/registSales")
    public ResponseEntity<String> registSales(@RequestBody Sale sale) {

        int result = salesService.registSales(sale);

        if (result == 1) {
            System.out.println("성공");
            return new ResponseEntity<>("성공", HttpStatus.OK);
        } else {
            System.out.println("실패");
            return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/saleInfo")
    public ResponseEntity<Sale> saleInfo(@RequestParam String saleId) {
        Sale info = salesService.saleInfo(saleId);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
