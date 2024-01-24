package com.example.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button logout_button, add_button;
    FirebaseAuth auth;
    private FirebaseDatabase db;
    private EditText user_name, user_marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout_button = findViewById(R.id.main_logout);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        add_button = findViewById(R.id.main_addDetails);
        user_name = findViewById(R.id.main_name_field);
        user_marks = findViewById(R.id.main_Marks_field);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(MainActivity.this, "Logout Successful !!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });
       add_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String userName_text = user_name.getText().toString();
               String userMarks_text = user_marks.getText().toString();
               if(userMarks_text.isEmpty() || userName_text.isEmpty()){
                   Toast.makeText(MainActivity.this, "Empty Details", Toast.LENGTH_SHORT).show();
               }
               else {
                   db.getReference().child("Users").push().child(userName_text).child("Marks").setValue(userMarks_text);
                   Toast.makeText(MainActivity.this, "Push Successful !!!", Toast.LENGTH_SHORT).show();
               }
           }
       });


    }
}