package com.example.atc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class user_type extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);



        AlertDialog alertDialog1 = new AlertDialog.Builder(
                user_type.this).create();

        // Setting Dialog Title
        alertDialog1.setTitle("Alert Dialog");

        // Setting Dialog Message
        alertDialog1.setMessage("Please Select The Type Of User");

        // Showing Alert Message
        alertDialog1.show();


        Button buttonOne = findViewById(R.id.angry_btn);
        Button buttontwo = findViewById(R.id.angry_bt);
        Button buttonth = findViewById(R.id.angry_b);
        Button buttonfr = findViewById(R.id.angry);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), RetailerStartUpScreen.class);
                startActivity(activity2Intent);
            }
        });

        buttontwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), Coachreatail.class);
                startActivity(activity2Intent);
            }
        });


        buttonth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), RetailerStartUpScreen.class);
                startActivity(activity2Intent);
            }
        });


        buttonfr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), RetailerStartUpScreen.class);
                startActivity(activity2Intent);
            }
        });


    }


    }
