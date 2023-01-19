package com.alex.hollydayappv1;

import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    TextView nameView, destinationView, priceView;
   // CheckBox favoriteView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        nameView = itemView.findViewById(R.id.name);
        destinationView = itemView.findViewById(R.id.destination);
        priceView = itemView.findViewById(R.id.price);
        //favoriteView = itemView.findViewById(R.id.favorite);
    }
}
