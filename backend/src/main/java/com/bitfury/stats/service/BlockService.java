package com.bitfury.stats.service;

import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;

/**
 * Created by Denys_Makarov on 9/28/2017.
 */
public interface BlockService {
    BlockData getBlocksWithMiningTime();
    ChartData getChartData();
}
