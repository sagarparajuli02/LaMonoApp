package com.la.mono;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    List<Movie> movieList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    ViewPager2 viewPager;
    List<MySliderList>mySliderLists;
    MySliderAdapter adapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 3;
    LinearLayout indicatorlay;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);

        movieList = new ArrayList<>();
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager =new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getContext(),movieList);
        recyclerView.setAdapter(recyclerAdapter);
        some();

        viewPager = view.findViewById(R.id.viewPager);

        getdata();
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        //NUM_PAGES =onBordingLists.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);



        return view;
    }



    private void some() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Movie>> call = apiService.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieList = response.body();
                recyclerAdapter.setMovieList(movieList);
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getdata() {
        Call<List<MySliderList>>call= MyRetrofit.getInstance().getMyApi().getonbordingdata();
        call.enqueue(new Callback<List<MySliderList>>() {
            @Override
            public void onResponse(Call<List<MySliderList>> call, Response<List<MySliderList>> response) {
                mySliderLists=response.body();
                adapter=new MySliderAdapter(getContext(),mySliderLists,viewPager);
                viewPager.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<MySliderList>> call, Throwable t) {
                Toast.makeText(getContext(), "Internal Error Occurs !!", Toast.LENGTH_SHORT).show();

            }
        });
    }




}