package com.bitfury.stats.controller;

import static com.bitfury.stats.ApplicationConstants.API_BASE_PATH;
import static com.bitfury.stats.ApplicationConstants.API_VERSION;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bitfury.stats.TestMain;
import com.bitfury.stats.model.Block;
import com.bitfury.stats.model.BlockData;
import com.bitfury.stats.model.ChartData;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * BlocksController Spring MVC test
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BlockController.class)
public class BlocksControllerMVCTest extends TestMain {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BlockController blockController;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetBlocks_happyPath() throws Exception {
        String hash = UUID.randomUUID().toString();
        BlockData blockData = BlockData.builder()
            .data(Collections.singletonList(Block.builder().hash(hash).height(1).build()))
            .build();

        given(blockController.getBlocks()).willReturn(blockData);
        mockMvc.perform(post("/" + API_BASE_PATH + "/" + API_VERSION + "/blocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(blockData)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].hash", is(hash)))
            .andExpect(jsonPath("$.data[0].height", is(1)));
    }

    @Test
    public void testGetChartData_happyPath() throws Exception {
        ChartData chartData = ChartData.builder()
            .events(Arrays.asList(1.0, 2.0, 3.0))
            .labels(Arrays.asList("first", "second", "third"))
            .build();

        given(blockController.getChartData()).willReturn(chartData);
        mockMvc.perform(post("/" + API_BASE_PATH + "/" + API_VERSION + "/stats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(chartData)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.events[0]", is(1.0)))
            .andExpect(jsonPath("$.labels[0]", is("first")))
            .andExpect(jsonPath("$.events[1]", is(2.0)))
            .andExpect(jsonPath("$.labels[1]", is("second")))
            .andExpect(jsonPath("$.events[2]", is(3.0)))
            .andExpect(jsonPath("$.labels[2]", is("third")));
    }
}