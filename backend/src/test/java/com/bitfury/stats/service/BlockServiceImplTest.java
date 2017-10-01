package com.bitfury.stats.service;

import static com.bitfury.stats.ApplicationConstants.DEFAULT_MINING_TIME;
import static com.bitfury.stats.ApplicationConstants.LIMIT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bitfury.stats.TestMain;
import com.bitfury.stats.model.Block;
import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;
import com.bitfury.stats.service.impl.BlockServiceImpl;

/**
 * BlockService test
 */
public class BlockServiceImplTest extends TestMain {
    @Mock
    private BitcoinRestApiService bitcoinRestApiService;

    @InjectMocks
    private BlockService unit = new BlockServiceImpl();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBlocksWithMiningTime_shouldReturnCorrectMiningTime() {
        List<Block> blockStream = Stream.generate(() -> Block.builder()
            .hash(UUID.randomUUID().toString())
            .height(1)
            .build())
            .limit(101)
            .collect(Collectors.toList());

        BlockData data = BlockData.builder().data(blockStream).build();

        //set 1 sec blockTime difference between blocks
        for (int i = 0; i < data.getData().size(); i++) {
            data.getData().get(i).setBlockTime(new Date(i * 1000L));
        }

        when(bitcoinRestApiService.getBlocksData()).thenReturn(data);

        BlockData blocksData = unit.getBlocksWithMiningTime();

        assertNotNull(blocksData);
        assertThat(blocksData.getData(), hasSize(LIMIT));

        blocksData.getData()
            .forEach(
                block -> {
                    assertNotNull(block.getMiningTime());
                    assertThat(block.getMiningTime().getTime(), is(-1000L));
                }
            );
    }

    @Test
    public void getChartsData_shouldReturnCorrectEventsAndLabels() {
        List<Block> blockStream = Stream.generate(() -> Block.builder()
            .hash(UUID.randomUUID().toString())
            .height(1)
            .build())
            .limit(101)
            .collect(Collectors.toList());

        BlockData data = BlockData.builder().data(blockStream).build();

        //set 1 sec blockTime difference between blocks
        for (int i = 0; i < data.getData().size(); i++) {
            data.getData().get(i).setBlockTime(new Date(i * DEFAULT_MINING_TIME));
        }

        when(bitcoinRestApiService.getBlocksData()).thenReturn(data);

        ChartData chartData = unit.getChartData();

        assertNotNull(chartData);
        assertThat(chartData.getEvents(), hasSize(LIMIT));
        assertThat(chartData.getLabels(), hasSize(LIMIT));

        chartData.getEvents()
            .forEach(
                event -> assertThat(event, is(-100.0))
            );

        chartData.getLabels()
            .forEach(
                Assert::assertNotNull
            );
    }
}
