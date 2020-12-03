package com.example.atc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowCoachers extends AppCompatActivity {

    RecyclerView recview;
    myadapterc adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coachers);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modllc> options = new FirebaseRecyclerOptions.Builder<modllc>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("coachers"),modllc.class)
                .build();

        adapter = new myadapterc(options);
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