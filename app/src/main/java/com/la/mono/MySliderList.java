package com.la.mono;


import com.google.gson.annotations.SerializedName;

public class MySliderList {

    @SerializedName("image_url")
    public String image_url;

    @SerializedName("title")
    public String title;

    @SerializedName("desc")
    public String description;


    public MySliderList( String image_url, String title) {

        this.image_url = image_url;
        this.title = title;

    }


    public String getImage_url() {
        return image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}