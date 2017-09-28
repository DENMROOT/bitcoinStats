package com.bitfury.stats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitfury.stats.model.Block;
import com.bitfury.stats.service.BlockService;


/**
 * Block controller
 */
@RestController
public class BlockController {

    @Autowired
    private BlockService blockService;

    @RequestMapping(
        path = "/blocks",
        produces = "application/json;charset=UTF-8"
    )
    List<Block> getBlocks() {
        return blockService.getBlocksWithMiningTime();
    }
}
