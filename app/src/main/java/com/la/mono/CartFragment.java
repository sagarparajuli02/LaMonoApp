package com.la.mono;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.CONTEXT_RESTRICTED;
import static android.content.Context.MODE_PRIVATE;


public class CartFragment extends Fragment {


    ArrayList<User> mExampleList;
    private RecyclerView mRecyclerView;
    private CartAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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

        mRecyclerView = view.findViewById(R.id.recyclerviewCart);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CartAdapter(mExampleList);
        mRecyclerView.setAdapter(mAdapter);
        loadData();
        mAdapter.notifyDataSetChanged();


        return view;
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasklist", null);
        Toast.makeText(getContext(), json, Toast.LENGTH_SHORT).show();
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mAdapter = new CartAdapter(mExampleList);
        }


    }
}