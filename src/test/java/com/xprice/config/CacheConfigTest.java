package com.xprice.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Import;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Import(CacheConfig.class)
public class CacheConfigTest {

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        assertNotNull(cacheManager);
    }

    /**
     * This test checks the cache period. So it can increase build time for 15 mins.
     **/
    @Test
    void testCacheManager() {
        // Retrieve the cache manager and check if it's correctly configured
        CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager;
        CaffeineCache cache = (CaffeineCache) caffeineCacheManager.getCache("prices");
        assertNotNull(cache);

        // Adding an item to the cache
        cache.put("key1", "value1");
        assertNotNull(cache.get("key1").get());

        // Sleep for more than the cache expiry time (15 seconds) and check if the item is evicted
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(15) + 1000); // Sleep for a bit more than 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure that the cached item has been evicted
        assertNull(cache.get("key1"));
    }
}
