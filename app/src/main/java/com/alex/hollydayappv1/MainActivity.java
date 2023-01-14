package com.alex.hollydayappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.switch1);

        tv.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
            startActivity(intent);
        });


    }
}