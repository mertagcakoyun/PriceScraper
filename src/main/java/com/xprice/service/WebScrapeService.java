package com.xprice.service;

import com.xprice.model.ProductInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.io.IOException;
/**
 * Service class responsible for web scraping product information from URLs and caching the results.
 */

@Service
public class WebScrapeService {
    private final Logger logger = LoggerFactory.getLogger(WebScrapeService.class);

    /**
     * Scrapes the specified website for product information (price and title).
     * Caches the result based on the URL for efficient subsequent retrievals.
     *
     * @param url            The URL of the website to scrape
     * @param priceSelector  CSS selector for locating the price element on the webpage
     * @param titleSelector  CSS selector for locating the title element on the webpage
     * @return ProductInfo object containing website URL, price, and title
     */
    @Cacheable(value = "prices", key = "#url", sync = true)
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
