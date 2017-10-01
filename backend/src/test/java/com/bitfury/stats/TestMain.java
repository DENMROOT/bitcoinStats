package com.bitfury.stats;

import java.util.Arrays;
import java.util.UUID;

import com.bitfury.stats.model.Block;
import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;

/**
 * Test helper
 */
public class TestMain {

    public static BlockData getBlockData() {
        Block block1 = Block.builder()
            .hash(UUID.randomUUID().toString())
            .height(1)
            .build();
        Block block2 = Block.builder()
            .hash(UUID.randomUUID().toString())
            .height(2)
            .build();
        Block block3 = Block.builder()
            .hash(UUID.randomUUID().toString())
            .height(3)
            .build();

        return BlockData.builder().data(Arrays.asList(block1, block2, block3)).build();
    }

    public static ChartData getChartData() {
        return ChartData.builder()
            .events(Arrays.asList(1.0, 2.0, 3.0))
            .labels(Arrays.asList("first", "second", "third"))
            .build();
    }
}
