package com.la.mono;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.DateTime;


public class BookingFragment extends Fragment implements DatePickerListener {


    EditText bookingNumber;
    TimePicker timePicker;
    Button conformBooking;
    String bookingNumberString;
    String datePicker,timePickerString;



    public BookingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_booking, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // find the picker
        HorizontalPicker picker = view.findViewById(R.id.datePicker);
         timePicker= view.findViewById(R.id.timePicker);
        // initialize it and attach a listener
        picker
                .setListener(this)
                .setDays(14)
                .setOffset(3)

                .init();
        bookingNumber=view.findViewById(R.id.bookingNumber);


        conformBooking=view.findViewById(R.id.conformBookingButton);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("booking");

BookingModel bookingModel=new BookingModel();

        conformBooking.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                 bookingNumberString=bookingNumber.getText().toString().trim();
                int hour = timePicker.getHour();
                int min = timePicker.getMinute();
                String time=  hour +":"+ min;


                 if(TextUtils.isEmpty(bookingNumberString)){
                    bookingNumber.setError("Enter Number of Guest");
                }
               

                 else if ( Integer.valueOf(bookingNumber.getText().toString().trim())>10){
                     bookingNumber.setError("Booking Should be Less then 10");

                }
                 else {
                    bookingModel.setNumberofGuest(bookingNumberString);
                   bookingModel.setBookingDate(datePicker.toString());
                   bookingModel.setBookingTime(time);
                   bookingModel.setEmail(user.getEmail());
                   bookingModel.setName(user.getDisplayName());
                    myRef.push().setValue(bookingModel);

                     AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                     builder1.setMessage("Booking Success");

                     builder1.setPositiveButton(
                             "Okay",
                             new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {
                                     bookingNumber.getText().clear();
                                     dialog.cancel();
                                 }
                             });

                     AlertDialog alert11 = builder1.create();
                     alert11.show();
                }



            }
        });






        return view;
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        Toast.makeText(getContext(), dateSelected.toString(), Toast.LENGTH_SHORT).show();

        datePicker=dateSelected.toString();


    }
}
//done