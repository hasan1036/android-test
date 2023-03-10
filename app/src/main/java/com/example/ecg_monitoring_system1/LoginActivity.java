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

public class LoginActivity extends AppCompatActivity {
    TextInputLayout t1,t2;
    ProgressBar bar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        t1 = findViewById(R.id.email_login);
        t2 = findViewById(R.id.pwd_login);
        bar = findViewById(R.id.progressBar3);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInhere(View view) {
        bar.setVisibility(View.VISIBLE);
        String email = t1.getEditText().getText().toString();
        String pasword = t2.getEditText().getText().toString();

        mAuth.signInWithEmailAndPassword(email,pasword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            bar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(LoginActivity.this,Dashboard.class);
                            intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                            intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                            startActivity(intent);

                        }else {
                            bar.setVisibility(View.INVISIBLE);
                            t1.getEditText().setText("");
                            t2.getEditText().setText("");

                            Toast.makeText(getApplicationContext(), "Invalid email/pass", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}