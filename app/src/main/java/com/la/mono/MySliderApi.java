package com.la.mono;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MySliderApi {


    @GET("e01a8c55acf9b656e20c")
    Call<List<MySliderList>> getonbordingdata();
}