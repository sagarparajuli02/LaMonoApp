package com.la.mono;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;


public class BookingFragment extends Fragment implements DatePickerListener {


    EditText bookingNumber;
    Button conformBooking;
    String bookingNumberString;

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
        // find the picker
        HorizontalPicker picker = view.findViewById(R.id.datePicker);
        // initialize it and attach a listener
        picker
                .setListener(this)
                .setDays(14)
                .setOffset(0)

                .init();
        bookingNumber=view.findViewById(R.id.bookingNumber);

        conformBooking=view.findViewById(R.id.conformBookingButton);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("booking");

BookingModel bookingModel=new BookingModel();

        conformBooking.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                 bookingNumberString=bookingNumber.getText().toString().trim();


                 if(TextUtils.isEmpty(bookingNumberString)){
                    bookingNumber.setError("Enter Number of Guest");
                }
                 else {
                    bookingModel.setNumberofGuest(bookingNumberString);
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
        Toast.makeText(getContext(), "Selected", Toast.LENGTH_SHORT).show();

    }
}
//done