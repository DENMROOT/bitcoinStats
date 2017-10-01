package com.bitfury.stats.controller;

import static com.bitfury.stats.ApplicationConstants.API_BASE_PATH;
import static com.bitfury.stats.ApplicationConstants.API_VERSION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;
import com.bitfury.stats.service.BlockService;

/**
 * Block controller
 */
@RestController
public class BlockController {
    private static final String BLOCKS_API_PATH = API_BASE_PATH + "/" + API_VERSION;

    @Autowired
    private BlockService blockService;

    @RequestMapping(
        path = BLOCKS_API_PATH + "/blocks",
        produces = "application/json;charset=UTF-8"
    )
    BlockData getBlocks() {
        return blockService.getBlocksWithMiningTime();
    }

    @RequestMapping(
        path = BLOCKS_API_PATH + "/stats",
        produces = "application/json;charset=UTF-8"
    )
    ChartData getChartData() {
        return blockService.getChartData();
    }
}
