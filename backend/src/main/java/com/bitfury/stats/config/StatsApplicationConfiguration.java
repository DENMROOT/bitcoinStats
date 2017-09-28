package com.bitfury.stats.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bitfury.stats.ApplicationConstants;
import com.bitfury.stats.model.Block;
import com.bitfury.stats.service.BlockService;
import com.bitfury.stats.service.impl.BlockServiceImpl;
import com.bitfury.stats.service.rest.BitcoinRestApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Denys_Makarov on 9/27/2017.
 */
@Configuration
public class StatsApplicationConfiguration {

    @Value(ApplicationConstants.BITCOIN_API_URL_KEY)
    private String apiUrlKey;

    @Value(ApplicationConstants.BITCOIN_API_VERSION)
    private String apiUrlVersion;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(Block.class);
        return objectMapper;
    }

    @Bean
    public BitcoinRestApi bitcoinRestApi() {
        return new Retrofit.Builder()
            .baseUrl(apiUrlKey + "/" + apiUrlVersion + "/")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper()))
            .build()
            .create(BitcoinRestApi.class);
    }

    @Bean
    public BlockService blockService() {
        return new BlockServiceImpl();
    }
}
