package com.bitfury.stats;

/**
 * Created by Denys_Makarov on 9/27/2017.
 */
public final class ApplicationConstants {
    private ApplicationConstants() {
        throw new AssertionError("Designed not for instantiation");
    }

    public static final String BITCOIN_API_URL_KEY = "${apiUrlKey}";
    public static final String BITCOIN_API_VERSION = "${apiUrlVersion}";

    public static final int LIMIT = 100;
    public static final int PAGE = 1;
    public static final String SORT_DIR = "desc";
    public static final String APIKEY = "MY_APIKEY";
}
