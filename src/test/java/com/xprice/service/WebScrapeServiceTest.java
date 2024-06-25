package com.xprice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import com.xprice.model.ProductInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class WebScrapeServiceTest {

    @InjectMocks
    private Document document;

    @Mock
    private WebScrapeService webScrapeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testScrapeWebsite_Successfully() throws IOException {
        //Given
        Element priceElement = new Element("price");
        priceElement.text("10.000 TL");
        Elements priceElements = new Elements(priceElement);

        Element titleElement = new Element("title");
        titleElement.text("Test Product");
        Elements titleElements = new Elements(titleElement);

        doReturn(priceElements).when(document).select(".price");
        doReturn(titleElements).when(document).select(".product-name");

        WebScrapeService spyService = org.mockito.Mockito.spy(webScrapeService);
        doReturn(document).when(spyService).getDocument("http://test-url.com");

        // When
        ProductInfo result = spyService.scrapeWebsite("http://test-url.com", ".price", ".product-name");

        //Then
        assertNotNull(result);
        assertEquals("Test Product", result.getTitle());
        assertEquals(10000.0, result.getPrice());
    }
}
