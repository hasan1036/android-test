package com.example.ecg_monitoring_system1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int screen =  2500;
    ImageView imageView;
    TextView textView1,textView2;
    Animation up,down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.imageview);
        textView1 =  findViewById(R.id.textview);
        textView2 =  findViewById(R.id.textview2);

        up = AnimationUtils.loadAnimation(this,R.anim.up);
        down = AnimationUtils.loadAnimation(this,R.anim.down);

        imageView.setAnimation(up);
        textView1.setAnimation(down);
        textView2.setAnimation(down);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,SignUp.class);
                startActivity(intent);
                finish();

            }
        },screen);

    }
}