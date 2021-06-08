package com.la.mono;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class PopularItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_item_details);

        String itemName = getIntent().getStringExtra("itemName");
        String itemUrl = getIntent().getStringExtra("imageUrl");
        String itemDescription = getIntent().getStringExtra("itemDescription");
        String itemPrice = getIntent().getStringExtra("itemPrice");


        ImageView imageView=findViewById(R.id.image_url);
        TextView itemTitle=findViewById(R.id.itemTitle);
        TextView itemDecsription=findViewById(R.id.itemDescription);
        TextView itemPrices=findViewById(R.id.itemPrice);


        Glide.with(this).load(itemUrl).into(imageView);
        itemDecsription.setText(itemDescription);
        itemTitle.setText(itemName);
        itemPrices.setText(itemPrice);

    }
}