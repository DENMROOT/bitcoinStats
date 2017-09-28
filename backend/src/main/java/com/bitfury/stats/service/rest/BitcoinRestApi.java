package com.bitfury.stats.service.rest;

import com.bitfury.stats.model.BlockData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Bitcoin REST Api
 */
public interface BitcoinRestApi {

    @GET("btc/all-blocks")
    Call<BlockData> getBlocksWithPagination(@Query("page") int page, @Query("limit") int limit,
                                            @Query("sort_dir") String sortDir, @Query("api_key") String apiKey);
}
