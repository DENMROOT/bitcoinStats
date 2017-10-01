package com.bitfury.stats.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bitfury.stats.TestMain;
import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;
import com.bitfury.stats.service.BlockService;

/**
 * BlocksController test class
 */
public class BlocksControllerTest extends TestMain{

    @Mock
    private BlockService blockService;

    @InjectMocks
    private BlockController unit;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBlocks_happyPath(){
        BlockData data = getBlockData();

        when(blockService.getBlocksWithMiningTime()).thenReturn(data);
        BlockData blocks = unit.getBlocks();

        assertNotNull(blocks);
        assertThat(blocks.getData(), hasSize(3));
    }

    @Test
    public void getChartData_happyPath(){
        ChartData data = getChartData();

        when(blockService.getChartData()).thenReturn(data);

        ChartData blocks = unit.getChartData();

        assertNotNull(blocks);
        assertThat(blocks.getEvents(), hasSize(3));
        assertThat(blocks.getLabels(), hasSize(3));
    }

}
