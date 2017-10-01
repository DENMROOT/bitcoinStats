package com.bitfury.stats.config;

import static com.bitfury.stats.ApplicationConstants.DEFAULT_CACHE_EXP_TIME;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

/**
 * Cache configuration
 */
@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {

    public static final String BLOCKS_CACHE = "blocksCache";

    @Bean
    @Override
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        GuavaCache blocksCache = new GuavaCache(BLOCKS_CACHE, CacheBuilder.newBuilder()
            .expireAfterWrite(DEFAULT_CACHE_EXP_TIME, TimeUnit.MINUTES)
            .build());

        cacheManager.setCaches(Collections.singletonList(blocksCache));

        return cacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
