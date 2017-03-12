package com.lightgeneration.kid_locker.networks;

import com.lightgeneration.kid_locker.models.Account;
import com.lightgeneration.kid_locker.models.FullQuestion;
import com.lightgeneration.kid_locker.models.ItemComparision;
import com.lightgeneration.kid_locker.models.ObjectProgress;
import com.lightgeneration.kid_locker.models.ResponceAccount;
import com.lightgeneration.kid_locker.models.ResponceLogin;
import com.lightgeneration.kid_locker.models.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public interface ApiClient {
    @GET("api/questions/{username}/{count}")
    Call<ArrayList<FullQuestion>> getAllQuestion(@Path("username") String username, @Path("count") int count);
    @POST("login")
    Call<ResponceLogin> login(@Body User user);
    @POST("register")
    Call<ResponceAccount> register(@Body Account account);
    @GET("api/log/{username}/comparision")
    Call<ItemComparision> getComparison(@Path("username") String userName);
    @PUT("api/user/{username}/{age}")
    Call<ResponceAccount> editInfo(@Path("username") String username,@Path("age") int age);
    @GET("api/log/{username}/progression")
    Call<ObjectProgress> getProgression(@Path("username") String username);

}
