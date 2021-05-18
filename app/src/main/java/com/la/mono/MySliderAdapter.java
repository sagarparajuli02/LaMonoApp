package com.la.mono;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

import java.util.List;

public class MySliderAdapter extends RecyclerView.Adapter<MySliderAdapter.ViewHolder> {
    private List<MySliderList> mySliderLists;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager;
    Context context;

    public MySliderAdapter(Context context, List<MySliderList> mySliderLists, ViewPager2 viewPager) {
        this.mInflater = LayoutInflater.from(context);
        this.mySliderLists = mySliderLists;
        this.viewPager = viewPager;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.slider_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MySliderList ob= mySliderLists.get(position);
        Glide.with(context).load(ob.getImage_url()).into(holder.myimage);
        holder.myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clickked", Toast.LENGTH_SHORT).show();
            }
        });
//
    }

    @Override
    public int getItemCount() {
        return mySliderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView myimage;
        RelativeLayout relativeLayout;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myimage = itemView.findViewById(R.id.myimage);
            relativeLayout = itemView.findViewById(R.id.container);

        }
    }
}