package com.xprice.service;

import com.xprice.model.ProductInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebScrapeService {
    private final Logger logger = LoggerFactory.getLogger(WebScrapeService.class);

    public ProductInfo scrapeWebsite(String url, String priceSelector, String titleSelector) {
        try {
            Document doc = getDocument(url);
            Elements priceElement = doc.select(priceSelector);
            Elements titleElement = doc.select(titleSelector);
            String priceText = priceElement.first().text()
                    .replace("TL", "")
                    .replace(".", "")
                    .replace(",", ".")
                    .replace("-", "").trim();
            double price = Double.parseDouble(priceText);
            String title = titleElement.first().text().trim();
            logger.info("Scraping successful for url: " + url);
            return new ProductInfo(url, price, title);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new ProductInfo(url, Double.MAX_VALUE, "Error fetching title"); // Return the highest price and error message in case of error
        }
    }
    protected Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
