package com.alex.hollydayappv1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, email, password, phone;
    String userName, userEmail, userPassword, userPhone;
    Button push;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        // find id
        name = findViewById(R.id.userName);
        email = findViewById(R.id.uEmail);
        password = findViewById(R.id.uPassword);
        phone = findViewById(R.id.uContact);
        push = findViewById(R.id.registerBtn);


        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = name.getText().toString().trim();
                userEmail = email.getText().toString().trim();
                userPassword= password.getText().toString().trim();
                userPhone = phone.getText().toString().trim();

//                if(!(userName.equals("") && userEmail.equals("") && userPassword.equals("") && userPhone.equals(""))){
//                    name.setError("username is empty");
//                    Toast.makeText(RegistrationActivity.this,"Please fill all fields", Toast.LENGTH_SHORT).show();
//                }

                    if(userName.isEmpty()){
                        name.setError("Username is require");
                    }
                if(userEmail.isEmpty()){
                    email.setError("Email is require");
                }
                if(userName.isEmpty()){
                    password.setError("Password is require");
                }
                if(userName.isEmpty()){
                    phone.setError("Phone is require");
                }else{
                    authentificationCheck();
                }

            }
        });


    }

    private void authentificationCheck() {

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(RegistrationActivity.this, "Created Successful", Toast.LENGTH_SHORT).show();

                    String currentUserID = mAuth.getCurrentUser().getUid();

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("name", userName);
                    userdataMap.put("email", userEmail);
                    userdataMap.put("phone", userPhone);

                    databaseReference.child("users").child(currentUserID).updateChildren(userdataMap);

                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}