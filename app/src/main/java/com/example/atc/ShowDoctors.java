package com.example.atc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowDoctors extends AppCompatActivity {

    RecyclerView recview;
    myadapterd adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coachers);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modlld> options = new FirebaseRecyclerOptions.Builder<modlld>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors"),modlld.class)
                .build();

        adapter = new myadapterd(options);
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