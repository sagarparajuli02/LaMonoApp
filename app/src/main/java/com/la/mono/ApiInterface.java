package com.la.mono;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("5f24958e1111e6c20633")
    Call<List<Movie>> getMovies();


    @GET("f38591e849cd65c0f211")
    Call<List<Movie>> getMenu();
}