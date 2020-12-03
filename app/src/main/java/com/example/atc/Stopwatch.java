package com.example.atc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Stopwatch extends AppCompatActivity {

    TextView tvSplash,tvSubSplash;
    Button btnget;
    Animation atg,btnone,btntwo;
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        tvSplash = findViewById(R.id.tvSplash);
        tvSubSplash = findViewById(R.id.tvSplash1);
        btnget = findViewById(R.id.btnget);
        ivSplash= findViewById(R.id.ivSplash);

        atg = AnimationUtils.loadAnimation(this,R.anim.atg);
        btnone= AnimationUtils.loadAnimation(this,R.anim.btnone);
        btntwo = AnimationUtils.loadAnimation(this,R.anim.btntwo);

        ivSplash.startAnimation(atg);
        tvSplash.startAnimation(btnone);
        tvSubSplash.startAnimation(btnone);
        btnget.startAnimation(btntwo);

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Stopwatch.this, WatchActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });





    }
}