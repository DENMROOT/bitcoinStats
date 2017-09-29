package com.bitfury.stats.service.impl;

import static com.bitfury.stats.ApplicationConstants.APIKEY;
import static com.bitfury.stats.ApplicationConstants.LIMIT;
import static com.bitfury.stats.ApplicationConstants.PAGE;
import static com.bitfury.stats.ApplicationConstants.SORT_DIR;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitfury.stats.model.Block;
import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;
import com.bitfury.stats.service.BlockService;
import com.bitfury.stats.service.rest.BitcoinRestApi;
import retrofit2.Response;

/**
 * Created by Denys_Makarov on 9/28/2017.
 */
public class BlockServiceImpl implements BlockService {
    private static final Logger LOG = LoggerFactory.getLogger(BlockServiceImpl.class);
    public static final int DEFAULT_MINING_TIME = 600000;
    public static final int PERCENTAGE_CONVERSION_MULTIPLIER = 100;

    @Autowired
    private BitcoinRestApi restService;

    @Override
    public BlockData getBlocksWithMiningTime() {

        BlockData blocksData = getBlocksData();
        List<Block> blocks = calculateMiningTime(blocksData);

        BlockData updatedBlocksData = new BlockData();
        updatedBlocksData.setData(blocks.stream().limit(LIMIT).collect(Collectors.toList()));

        return updatedBlocksData;
    }

    @Override
    public ChartData getChartData() {
        List<Block> blocksWithMiningTime = getBlocksWithMiningTime().getData();

        List<String> labels = blocksWithMiningTime.stream()
            .map(Block::getHeight)
            .map(Object::toString)
            .collect(Collectors.toList());

        List<Double> events = blocksWithMiningTime.stream()
            .map(block -> calculateMiningTimePercentage(block))
            .collect(Collectors.toList());

        return ChartData.builder().labels(labels).events(events).build();
    }

    private double calculateMiningTimePercentage(Block block) {
        double d = (block.getMiningTime().getTime() * 1.0 / DEFAULT_MINING_TIME) * PERCENTAGE_CONVERSION_MULTIPLIER;

        return Double.parseDouble(String.format("%.0f", d));
    }

    private List<Block> calculateMiningTime(BlockData blocksData) {
        List<Block> sortedBlocks = blocksData.getData().stream()
            .sorted((o1, o2) -> ((Integer) o2.getHeight()).compareTo(o1.getHeight()))
            .collect(Collectors.toList());

        for (int i = 0; i < LIMIT; i++) {

            Duration miningTime = Duration.between(
                sortedBlocks.get(i + 1).getBlockTime().toInstant(),
                sortedBlocks.get(i).getBlockTime().toInstant()
            );

            sortedBlocks.get(i).setMiningTime(new Date(miningTime.toMillis()));
        }

        return sortedBlocks;
    }

    private BlockData getBlocksData() {
        Response<BlockData> response = null;

        try {
            response = restService.getBlocksWithPagination(PAGE, LIMIT + 1, SORT_DIR, APIKEY)
                .execute();
        } catch (IOException e) {
            LOG.error("GetCurrencies() Response to api failed, {}", response.message());
            throw new IllegalStateException(response.message());
        }
        return response.body();
    }
}
