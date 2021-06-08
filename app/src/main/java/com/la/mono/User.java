package com.la.mono;

import android.widget.TimePicker;

public class User {
    String image_url, itemName, itemPrice;
    String oderPickUpTime;

    public String getOderPickUpTime() {
        return oderPickUpTime;
    }

    public void setOderPickUpTime(String oderPickUpTime) {
        this.oderPickUpTime = oderPickUpTime;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}

