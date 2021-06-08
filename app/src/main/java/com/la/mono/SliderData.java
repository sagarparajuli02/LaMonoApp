package com.la.mono;

public class SliderData {

    private String itemDescription;
    public  String title,image_url,itemPrice;

    public SliderData() {
    }

    public SliderData(String itemDescription, String title, String image_url, String itemPrice) {
        this.itemDescription = itemDescription;
        this.title = title;
        this.image_url = image_url;
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}