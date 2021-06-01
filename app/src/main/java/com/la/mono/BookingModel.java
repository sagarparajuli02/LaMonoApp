package com.la.mono;

import android.text.Editable;
import android.widget.EditText;

import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

public class BookingModel {
    String numberofGuest;
    String BookingDate;
    String BookingTime;
    String email;
    String name;


    public BookingModel(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public String getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(String bookingTime) {
        BookingTime = bookingTime;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getNumberofGuest() {
        return numberofGuest;
    }

    public void setNumberofGuest(String numberofGuest) {
        this.numberofGuest = numberofGuest;
    }
}
