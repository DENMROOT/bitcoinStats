package com.bitfury.stats.service.impl;

import static com.bitfury.stats.ApplicationConstants.APIKEY;
import static com.bitfury.stats.ApplicationConstants.LIMIT;
import static com.bitfury.stats.ApplicationConstants.PAGE;
import static com.bitfury.stats.ApplicationConstants.SORT_DIR;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bitfury.stats.config.CacheConfiguration;
import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.service.BitcoinRestApiService;
import com.bitfury.stats.service.rest.BitcoinRestApi;
import retrofit2.Response;

/**
 * Bitcoin cacheable rest API service
 */
@Service
public class BitcoinCacheableRestApiServiceImpl implements BitcoinRestApiService {
    private static final Logger LOG = LoggerFactory.getLogger(BitcoinCacheableRestApiServiceImpl.class);

    @Autowired
    private BitcoinRestApi restService;

    @Cacheable(CacheConfiguration.BLOCKS_CACHE)
    public BlockData getBlocksData() {
        Response<BlockData> response = null;

        //we need to get 1 more block to calculate stats for oldest block from range
        try {
            response = restService.getBlocksWithPagination(PAGE, LIMIT + 1, SORT_DIR, APIKEY)
                .execute();
        } catch (IOException e) {
            LOG.error("getBlocksWithPagination() Response to api failed, {}", response.message());
            throw new IllegalStateException(response.message());
        }
        return response.body();
    }
}
