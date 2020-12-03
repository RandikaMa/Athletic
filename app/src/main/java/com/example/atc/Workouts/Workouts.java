package com.example.atc.Workouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atc.R;
import com.example.atc.WorkoutGenerator;

public class Workouts extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workouts);
        button = (Button)findViewById(R.id.create_my_workout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsideActivity4();
            }
        });
    }
    public void openInsideActivity4()
    {
        Intent intent = new Intent(this, WorkoutGenerator.class);
        startActivity(intent);
    }
}
