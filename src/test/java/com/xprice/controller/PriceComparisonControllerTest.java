package com.xprice.controller;

import com.xprice.model.ProductInfo;
import com.xprice.service.PriceComparisonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(controllers = PriceComparisonController.class)
class PriceComparisonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceComparisonService priceComparisonService;

    @Test
    void testGetMacBookAirM2Prices() throws Exception {
        ProductInfo info1 = new ProductInfo("http://test-url1.com", 5000.0, "Product 1");
        ProductInfo info2 = new ProductInfo("http://test-url2.com", 6000.0, "Product 2");
        ProductInfo info3 = new ProductInfo("http://test-url3.com", 4000.0, "Product 3");
        List<ProductInfo> productList = Arrays.asList(info1, info2, info3);

        when(priceComparisonService.getMacBookAirM2Prices()).thenReturn(productList);

        mockMvc.perform(get("/prices/macbook-air-m2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product 1")))
                .andExpect(content().string(containsString("Product 2")))
                .andExpect(content().string(containsString("Product 3")));
    }
}
