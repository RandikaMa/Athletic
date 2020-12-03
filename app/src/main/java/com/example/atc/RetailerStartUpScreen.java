package com.example.atc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class RetailerStartUpScreen extends AppCompatActivity {

    Button mLoginBtnn, MRegisterBtnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_start_up_screen);

        mLoginBtnn = findViewById(R.id.logi);
        MRegisterBtnn = findViewById(R.id.regi);

        mLoginBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoginBtnn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),login.class));
                    }
                });

            }

        });

        MRegisterBtnn.setOnClickListener(new View.OnClickListener()

    {
        @Override public void onClick (View v){

        MRegisterBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), register.class));
            }
        });

    }
    });


}


}