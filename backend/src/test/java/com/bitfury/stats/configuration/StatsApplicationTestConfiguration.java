package com.bitfury.stats.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bitfury.stats.ApplicationConstants;
import com.bitfury.stats.model.Block;
import com.bitfury.stats.service.rest.BitcoinRestApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Application test configuration class
 */
@Configuration
public class StatsApplicationTestConfiguration {

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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
            .baseUrl(apiUrlKey + "/" + apiUrlVersion + "/")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper()))
            .client(httpClient.build())
            .build()
            .create(BitcoinRestApi.class);
    }
}
