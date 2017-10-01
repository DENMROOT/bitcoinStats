package com.bitfury.stats.service;

import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;

/**
 *  Interface for all blocks and blocks statistics data
 */
public interface BlockService {

    /**
     * Get blocks with mining time
     * @return BlockData
     */
    BlockData getBlocksWithMiningTime();

    /**
     * Get chart statistics data
     * @return ChartData
     */
    ChartData getChartData();
}
