package com.la.mono;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MySliderApi {


    @GET("PopularItems.json")
    Call<List<MySliderList>> getonbordingdata();
}