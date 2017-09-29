package com.bitfury.stats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitfury.stats.model.Block;
import com.bitfury.stats.model.ChartData;
import com.bitfury.stats.service.BlockService;


/**
 * Block controller
 */
@RestController
public class BlockController {

    @Autowired
    private BlockService blockService;

    @RequestMapping(
        path = "api/blocks",
        produces = "application/json;charset=UTF-8"
    )
    List<Block> getBlocks() {
        return blockService.getBlocksWithMiningTime();
    }

    @RequestMapping(
        path = "api/stats",
        produces = "application/json;charset=UTF-8"
    )
    ChartData getChartData() {
        return blockService.getChartData();
    }
}
