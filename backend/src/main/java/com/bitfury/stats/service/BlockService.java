package com.bitfury.stats.service;

import java.util.List;

import com.bitfury.stats.model.Block;

/**
 * Created by Denys_Makarov on 9/28/2017.
 */
public interface BlockService {
    List<Block> getBlocksWithMiningTime();
}
