package com.example.atc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class VideoActivity extends AppCompatActivity {


    public RecyclerView mRecyclerView2;
    FirebaseDatabase mFirebaseDatabase2;
    DatabaseReference mref2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//       getSupportActionBar().setTitle("Videos");


        mRecyclerView2 = findViewById(R.id.video_recycler_view);
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase2 = FirebaseDatabase.getInstance();
        mref2 = mFirebaseDatabase2.getReference("video");

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Model2> options =
                new FirebaseRecyclerOptions.Builder<Model2>()
                        .setQuery(mref2,Model2.class)
                        .build();

        FirebaseRecyclerAdapter<Model2,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model2, ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model2 model) {

                        holder.setDetails2(getApplication(),model.getName(),model.getUrl());
                    }

                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.row,parent,false);


                        return new ViewHolder(view);
                    }
                };

        firebaseRecyclerAdapter.startListening();
        mRecyclerView2.setAdapter(firebaseRecyclerAdapter);





    }



}