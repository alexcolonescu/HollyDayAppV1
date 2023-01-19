package com.alex.hollydayappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alex.hollydayappv1.ui.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FourActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recyclerview);


        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Hotel puric", "Istanbul", "300 Ron",R.drawable.pesteramare ));
        items.add(new Item("Hotel Munte", "Baile Herculane", "350 Ron",R.drawable.c1 ));
        items.add(new Item("Hotel roman", "Zakintos", "400 Ron",R.drawable.grecia ));
        items.add(new Item("Hotel oxford", "Sovata", "500 Ron",R.drawable.urban1 ));
        items.add(new Item("Hotel prigojin", "Vatra Dornei", "800 Ron",R.drawable.p1 ));
        items.add(new Item("Hotel Putin", "Tenerife", "330 Ron",R.drawable.lacrece1 ));
        items.add(new Item("Hotel puric", "Istanbul", "300 Ron",R.drawable.pesteramare ));
        items.add(new Item("Hotel Munte", "Baile Herculane", "350 Ron",R.drawable.c1 ));
        items.add(new Item("Hotel roman", "Zakintos", "400 Ron",R.drawable.grecia ));
        items.add(new Item("Hotel oxford", "Sovata", "500 Ron",R.drawable.urban1 ));
        items.add(new Item("Hotel prigojin", "Vatra Dornei", "800 Ron",R.drawable.p1 ));
        items.add(new Item("Hotel Putin", "Tenerife", "330 Ron",R.drawable.lacrece1 ));




        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items ));

    }

}