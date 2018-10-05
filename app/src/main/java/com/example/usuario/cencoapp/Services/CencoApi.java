package com.example.usuario.cencoapp.Services;

import com.example.usuario.cencoapp.ModelData.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CencoApi {

    @GET("/3/movie/popular")
    Call<MovieResult> getPopular(@Query("api_key") String key);

    @GET("3/movie/top_rated")
    Call<MovieResult> getTopRate(@Query("api_key") String key);

}
