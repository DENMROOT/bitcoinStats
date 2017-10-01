package com.bitfury.stats.service;

import com.bitfury.stats.model.BlockData;

/**
 *  Interface for bitcoin rest api calls
 */
public interface BitcoinRestApiService {

    /**
     * Get blocks data from remote rest service
     * This method invocation is cacheable
     * @return BlockData
     */
    BlockData getBlocksData();
}
