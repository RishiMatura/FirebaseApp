package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.credentials.ClearCredentialStateException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    private Button register;
    private EditText user_password;
    private EditText user_email;
    private FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register_button_2);
        user_password = findViewById(R.id.passwordField);
        user_email = findViewById(R.id.emailField);
        auth = FirebaseAuth.getInstance();   // Initiated firebase variable

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email = user_email.getText().toString();
                String text_password = user_password.getText().toString();
                if (TextUtils.isEmpty(text_password) || TextUtils.isEmpty(text_email))
                    Toast.makeText(registerActivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                else if (text_password.length()<5) {
                    Toast.makeText(registerActivity.this, "Password too Short !!", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser(text_email, text_password);
//                    startActivity(new Intent(registerActivity.this, loginActivity.class));

                }
            }
        });

    }

    private void registerUser(String textEmail, String textPassword) {
        auth.createUserWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(registerActivity.this ,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(registerActivity.this, "Registration Successful!!", Toast.LENGTH_SHORT).show();
//                    auth.signOut();
                }
                else Toast.makeText(registerActivity.this, "Registration Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}