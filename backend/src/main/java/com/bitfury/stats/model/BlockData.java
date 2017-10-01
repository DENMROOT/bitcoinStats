package com.bitfury.stats.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * Block data class wrapper
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockData {
    private List<Block> data;

    @Builder
    public static BlockData build(List<Block> data) {
        BlockData blocksData = new BlockData();
        blocksData.setData(data);
        return blocksData;
    }
}
