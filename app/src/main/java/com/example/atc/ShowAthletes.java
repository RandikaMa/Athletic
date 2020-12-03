package com.example.atc;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowAthletes extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;
    ImageButton card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_athletes);

        recview=(RecyclerView)findViewById(R.id.recview);
        card1 = findViewById(R.id.card1);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modll> options = new FirebaseRecyclerOptions.Builder<modll>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("users"),modll.class)
                .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);



    }

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}