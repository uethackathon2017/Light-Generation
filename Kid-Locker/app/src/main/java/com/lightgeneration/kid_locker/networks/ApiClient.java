package com.lightgeneration.kid_locker.networks;

import com.lightgeneration.kid_locker.models.FullQuestion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public interface ApiClient {
    @GET("api/questions/{username}/{count}")
    Call<List<FullQuestion>> getAllQuestion(@Path("username") String username,@Path("count") int count);

}
