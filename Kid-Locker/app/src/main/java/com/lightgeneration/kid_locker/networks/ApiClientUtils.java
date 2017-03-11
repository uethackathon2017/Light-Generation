package com.lightgeneration.kid_locker.networks;

/**
 * Created by PhamVanLong on 3/11/2017.
 */

public class ApiClientUtils {
    public static ApiClient getApiClient() {
        return RetrofitClient.getClient().create(ApiClient.class);
    }
}
