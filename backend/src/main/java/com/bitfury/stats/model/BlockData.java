package com.bitfury.stats.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Block data class wrapper
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockData {
    private List<Block> data;
}
