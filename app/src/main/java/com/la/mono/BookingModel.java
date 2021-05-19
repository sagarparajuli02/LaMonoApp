package com.la.mono;

import android.text.Editable;
import android.widget.EditText;

import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

public class BookingModel {
    String numberofGuest;
    String BookingDate;
    String BookingTime;


    public BookingModel(){

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
