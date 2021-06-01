package com.la.mono;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("HomePageItems.json")
    Call<List<Movie>> getMovies();


    @GET("Products/Rolls.json")
    Call<List<Movie>> getMenu();
}