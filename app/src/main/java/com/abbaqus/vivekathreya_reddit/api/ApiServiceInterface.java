package com.abbaqus.vivekathreya_reddit.api;

import com.abbaqus.vivekathreya_reddit.repo.popular.PopularRepo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET("r/popular.json")
    Call<PopularRepo> getPopularTopics(@Query("limit") Integer limit);

    @GET("r/popular/rising.json")
    Call<PopularRepo> getPopularRisingTopics(@Query("limit") Integer limit);

    @GET("r/popular/hot.json")
    Call<PopularRepo> getPopularHotTopics(@Query("limit") Integer limit);

}
