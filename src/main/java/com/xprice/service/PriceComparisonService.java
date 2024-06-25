package com.xprice.service;

import com.xprice.model.ProductInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceComparisonService {
    protected static final String MEDIAMARKT_URL = "https://www.mediamarkt.com.tr/tr/product/_macbook-air-m2-8gb-256gb-ssd-13-6in%C3%A7-uzay-grisi-mlxw3tu-a-1222679.html?utm_content=nonbrand&gad_source=1&gclid=Cj0KCQjwj9-zBhDyARIsAERjds1wwlXawPzdTiEmOz2QhS-0StHCQJk32O-cSMvuhEAJVuhGbNzDnKsaAl-eEALw_wcB&gclsrc=aw.ds";
    protected static final String MEDIAMARKT_PRICE_TAG = ".price";
    protected static final String MEDIAMARKT_TITLE_TAG = ".product-name";
    protected static final String VATAN_URL = "https://www.vatanbilgisayar.com/macbook-air-mlxw3tu-a-m2-8gb-256gb-ssd-liquid-retina-13-6inc-uzay-grisi.html";
    protected static final String VATAN_PRICE_TAG = ".product-list__price";
    protected static final String VATAN_TITLE_TAG = ".product-list__product-name";
    protected static final String TEKNOSA_URL = "https://www.teknosa.com/apple-macbook-air-m2-8c-cpu-8c-gpu-256gb-ssd-136-gece-yarisi-dizustu-bilgisayar-mly33tua-p-125035429";
    protected static final String TEKNOSA_PRICE_TAG = ".prc-third";
    protected static final String TEKNOSA_TITLE_TAG = ".replaceName";

    @Autowired
    private WebScrapeService webScrapeService;

    public List<ProductInfo> getMacBookAirM2Prices() {
        List<ProductInfo> prices = new ArrayList<>();

        // scraping prices and product titles.
        prices.add(webScrapeService.scrapeWebsite(MEDIAMARKT_URL, MEDIAMARKT_PRICE_TAG, MEDIAMARKT_TITLE_TAG));
        prices.add(webScrapeService.scrapeWebsite(VATAN_URL, VATAN_PRICE_TAG, VATAN_TITLE_TAG));
        prices.add(webScrapeService.scrapeWebsite(TEKNOSA_URL, TEKNOSA_PRICE_TAG, TEKNOSA_TITLE_TAG));

        // sorting according to prices
        prices.sort(Comparator.comparingDouble(ProductInfo::getPrice));

        return prices;
    }
}