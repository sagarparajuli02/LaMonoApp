package com.la.mono;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.CONTEXT_RESTRICTED;
import static android.content.Context.MODE_PRIVATE;


public class CartFragment extends Fragment {

    CourseModal modal;
    public static  Button conformOrder;
    private RecyclerView courseRV;
TimePicker timePicker;
    // variable for our adapter class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;
    DatabaseReference myRef;
    FirebaseDatabase database;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        courseRV = view.findViewById(R.id.idRVCourses);
         conformOrder= view.findViewById(R.id.conformOrder);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        timePicker= view.findViewById(R.id.timePicker);
TextView pickUpText=view.findViewById(R.id.pickUpText);
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference().child("testOrder").child(user.getDisplayName());

conformOrder.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {


        conformOrder();


    }
});


        loadData();
        buildRecyclerView();


        return view;
    }



    public void conformOrder()  {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
User user= new User();
        String json = sharedPreferences.getString("courses", null);
        
        if (json==null){


            Toast.makeText(getContext(), "Your Cart is Empty.", Toast.LENGTH_SHORT).show();
        }
        int hour = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
        }
        int min = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            min = timePicker.getMinute();
        }
        String time=  hour +":"+ min;

if(json!=null){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                String itemName = jsonArray.getJSONObject(i).getString("itemName");
                String itemPrice=jsonArray.getJSONObject(i).getString("itemPrice");
                user.setItemName(itemName);
                user.setItemPrice(itemPrice);
                user.setOderPickUpTime(time);
                myRef.push().setValue(user);

                editor.clear();
                editor.commit();
                conformOrder.setText("Order Conformed Successfully");


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }}

    private void buildRecyclerView() {
        // initializing our adapter class.
        adapter = new CourseAdapter(courseModalArrayList, getContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        courseRV.setHasFixedSize(true);

        courseRV.setLayoutManager(manager);

        courseRV.setAdapter(adapter);
    }


    private void loadData() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sharedPreferences.getString("courses", null);



        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<CourseModal>>() {}.getType();

        courseModalArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (courseModalArrayList == null) {

            courseModalArrayList = new ArrayList<>();
        }
    }
}