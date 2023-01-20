package com.alex.hollydayappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alex.hollydayappv1.ui.UsersItem;
import com.alex.hollydayappv1.ui.UsersRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ThirdActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<UsersItem> holidayArrayList;
    UsersRecyclerAdapter adapter;

    Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).hide();


        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        holidayArrayList = new ArrayList<>();
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogAdd viewDialogAdd = new ViewDialogAdd();
                viewDialogAdd.showDialog(ThirdActivity.this);

            }
        });

        readData();

    }

    private void readData() {

        databaseReference.child("Holiday").orderByChild("HolidayName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                holidayArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UsersItem holidays = dataSnapshot.getValue(UsersItem.class);
                    holidayArrayList.add(holidays);
                }
                adapter = new UsersRecyclerAdapter(ThirdActivity.this,holidayArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public class ViewDialogAdd{
        public void showDialog(Context context){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE );
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_user);

            EditText textHoliday = dialog.findViewById(R.id.textHoliday);
            EditText textDestination = dialog.findViewById(R.id.textDestination);
            EditText textCountry = dialog.findViewById(R.id.textCountry);

            Button buttonAdd = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonAdd.setText("ADD");


            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = "holiday" + new Date().getTime();
                    String holiday = textHoliday.getText().toString();
                    String destination = textDestination.getText().toString();
                    String country = textCountry.getText().toString();

                    if(holiday.isEmpty() || destination.isEmpty() || country.isEmpty()){
                        Toast.makeText(context, "Please Enter All data...",Toast.LENGTH_SHORT).show();
                    }else{
                        databaseReference.child("Holiday").child(id).setValue(new UsersItem(id,holiday,destination,country));
                        Toast.makeText(context, "Add done!!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }

}