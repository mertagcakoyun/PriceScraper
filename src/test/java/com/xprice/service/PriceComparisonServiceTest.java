package com.xprice.service;

import com.xprice.model.ProductInfo;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceComparisonServiceTest {
    @InjectMocks
    private PriceComparisonService priceComparisonService;
    @Mock
    private WebScrapeService webScrapeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGetMacBookAirM2Prices() {
        //Given
        ProductInfo info1 = new ProductInfo(PriceComparisonService.MEDIAMARKT_URL, 5000.0, "Product 1");
        ProductInfo info2 = new ProductInfo(PriceComparisonService.VATAN_URL, 6000.0, "Product 2");
        ProductInfo info3 = new ProductInfo(PriceComparisonService.TEKNOSA_URL, 4000.0, "Product 3");

        doReturn(info1).when(webScrapeService).scrapeWebsite(PriceComparisonService.MEDIAMARKT_URL, PriceComparisonService.MEDIAMARKT_PRICE_TAG, PriceComparisonService.MEDIAMARKT_TITLE_TAG);
        doReturn(info2).when(webScrapeService).scrapeWebsite(PriceComparisonService.VATAN_URL, PriceComparisonService.VATAN_PRICE_TAG, PriceComparisonService.VATAN_TITLE_TAG);
        doReturn(info3).when(webScrapeService).scrapeWebsite(PriceComparisonService.TEKNOSA_URL, PriceComparisonService.TEKNOSA_PRICE_TAG, PriceComparisonService.TEKNOSA_TITLE_TAG);

        //When
        List<ProductInfo> prices = priceComparisonService.getMacBookAirM2Prices();

        //Then
        assertEquals(3, prices.size());
        assertEquals("Product 3", prices.get(0).getTitle()); // Assuming info3 has the lowest price
        assertEquals("Product 1", prices.get(1).getTitle());
        assertEquals("Product 2", prices.get(2).getTitle()); // Assuming info1 has the highest price
    }
}
