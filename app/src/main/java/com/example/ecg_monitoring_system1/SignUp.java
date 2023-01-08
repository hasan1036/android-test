package com.example.ecg_monitoring_system1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    TextInputLayout t1,t2;
    ProgressBar bar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        t1 = findViewById(R.id.email);
        t2 = findViewById(R.id.pwd);
        bar = findViewById(R.id.progressBar2);
    }
    public void signuphere(View view) {

        bar.setVisibility(View.VISIBLE);
        String email = t1.getEditText().getText().toString();
        String password = t2.getEditText().getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            bar.setVisibility(View.INVISIBLE);
                            t1.getEditText().setText("");
                            t2.getEditText().setText("");

                            Toast.makeText(getApplicationContext(), "Register Successfull", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            bar.setVisibility(View.INVISIBLE);
                            t1.getEditText().setText("");
                            t2.getEditText().setText("");
                            Toast.makeText(getApplicationContext(), "Process Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

    public void gotoLogin(View view) {

        startActivity(new Intent(SignUp.this,LoginActivity.class));

    }
}