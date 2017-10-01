package com.bitfury.stats.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * ChartData class
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartData {
    private List<String> labels;
    private List<Double> events;

    @Builder
    public static ChartData build(List<String> labels, List<Double> events) {
        ChartData chartData = new ChartData();
        chartData.labels = labels;
        chartData.events = events;
        return chartData;
    }
}
