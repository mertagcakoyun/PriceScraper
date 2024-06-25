package com.xprice.controller;

import com.xprice.model.ProductInfo;
import com.xprice.service.PriceComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceComparisonController {

    @Autowired
    private PriceComparisonService priceComparisonService;

    @GetMapping("/prices/macbook-air-m2")
    public List<ProductInfo> getMacBookAirM2Prices() {
        return priceComparisonService.getMacBookAirM2Prices();
    }
}
