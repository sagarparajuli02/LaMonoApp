package com.la.mono;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    private static final String BASE_URL="https://api.npoint.io/";
    private static MyRetrofit myClient;
    private Retrofit retrofit;

    private MyRetrofit(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized MyRetrofit getInstance(){
        if (myClient==null){
            myClient=new MyRetrofit();
        }
        return myClient;
    }
    public MySliderApi getMyApi(){
        return retrofit.create(MySliderApi.class);
    }
}