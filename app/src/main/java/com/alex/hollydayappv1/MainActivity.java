package com.alex.hollydayappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText Email, Password;

    String email, password;
    Button btn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        mAuth = FirebaseAuth.getInstance();

//        myRef.setValue("Hello, World!");
//
        tv = findViewById(R.id.switch1);
        Email = findViewById(R.id.u_email);
        Password = findViewById(R.id.u_password);
        btn = findViewById(R.id.loginBtn);



        tv.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(intent);
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = Email.getText().toString().trim();
                password = Password.getText().toString().trim();

                if (email.isEmpty()) {
                    Email.setError("Email is require");
                } else if (password.isEmpty()) {

                    Password.setError("Password is require");
                } else {
                    authenticationUser(email, password);
                }

            }
        });


    }

    private void authenticationUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
                        startActivity(intent);
//
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    String user1= user.getUid();
                }else{
                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}