package com.example.project.Ingredient;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("meals")
    public Call<Repository> getResponse();
}
