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
import com.bitfury.stats.service.BlockService;
import com.bitfury.stats.service.rest.BitcoinRestApi;
import retrofit2.Response;

/**
 * Created by Denys_Makarov on 9/28/2017.
 */
public class BlockServiceImpl implements BlockService {
    private static final Logger LOG = LoggerFactory.getLogger(BlockServiceImpl.class);


    @Autowired
    private BitcoinRestApi restService;

    @Override
    public List<Block> getBlocksWithMiningTime() {

        BlockData blocksData = getBlocksData();
        List<Block> blocks = calculateMiningTime(blocksData);

        return blocks.stream().limit(LIMIT).collect(Collectors.toList());
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
