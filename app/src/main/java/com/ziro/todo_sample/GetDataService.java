package com.ziro.todo_sample;

import com.ziro.todo_sample.models.ResponseModelClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("all-sections/7.json")
    Call<ResponseModelClass> getResponse(@Query("api-key") String apiKey);
}
