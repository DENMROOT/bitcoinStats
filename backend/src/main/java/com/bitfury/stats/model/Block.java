package com.bitfury.stats.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * Block class
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Block implements Comparable<Block> {

    private String hash;

    private String version;

    private int height;

    @Setter(onMethod = @__({@JsonProperty("block_time")}))
    private Date blockTime;

    @Setter(onMethod = @__({@JsonProperty("arrival_time")}))
    private Date arrivalTime;

    private Date miningTime;

    private int confirmations;

    private int transactions;

    @Setter(onMethod = @__({@JsonProperty("miningpool_name")}))
    private String miningPoolName;

    @Setter(onMethod = @__({@JsonProperty("miningpool_url")}))
    private String miningPoolUrl;

    @Setter(onMethod = @__({@JsonProperty("miningpool_slug")}))
    private String miningPoolSlug;

    @Builder
    public static Block build(String hash, String version, int height) {
        Block block = new Block();
        block.hash = hash;
        block.version = version;
        block.height = height;
        return block;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Block{");
        sb.append("hash='").append(hash).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", height=").append(height);
        sb.append(", blockTime=").append(blockTime);
        sb.append(", arrivalTime=").append(arrivalTime);
        sb.append(", confirmations=").append(confirmations);
        sb.append(", transactions=").append(transactions);
        sb.append(", miningPoolName='").append(miningPoolName).append('\'');
        sb.append(", miningPoolUrl='").append(miningPoolUrl).append('\'');
        sb.append(", miningPoolSlug='").append(miningPoolSlug).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Block other) {
        if (other == null) {
            return 1;
        }
        return hash.compareTo(other.hash);
    }
}
