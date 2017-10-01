package com.bitfury.stats;

/**
 * Application constants
 */
public final class ApplicationConstants {
    private ApplicationConstants() {
        throw new AssertionError("Designed not for instantiation");
    }

    //Remote API constants
    public static final String BITCOIN_API_URL_KEY = "${apiUrlKey}";
    public static final String BITCOIN_API_VERSION = "${apiUrlVersion}";

    public static final int LIMIT = 100;
    public static final int PAGE = 1;
    public static final String SORT_DIR = "desc";
    public static final String APIKEY = "MY_APIKEY";

    //Application API constants
    public static final String API_BASE_PATH = "api";
    public static final String API_VERSION = "v1";

    public static final int DEFAULT_CACHE_EXP_TIME = 5;

    public static final int DEFAULT_MINING_TIME = 600000;
    public static final int PERCENTAGE_CONVERSION_MULTIPLIER = 100;
}
