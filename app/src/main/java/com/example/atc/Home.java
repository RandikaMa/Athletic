package com.example.atc;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.atc.Workouts.Workouts;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    StorageReference storageReference;
    FirebaseAuth fAuth;

    CircleImageView profile_im;

    CardView card1,card2,card3,card4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById((R.id.drawer_layout));
        navigationView = findViewById((R.id.nav_view));
        toolbar = findViewById((R.id.toolbar));
        profile_im = findViewById(R.id.imageView9);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();



        setSupportActionBar(toolbar);


        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.mm).setVisible(true);
        menu.findItem(R.id.me).setVisible(true);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);




        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //navigationView.setCheckedItem(R.id.nav_home);


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ShowAthletes.class);
                startActivity(intent);
            }
        });


        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ShowCoachers.class);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ShowDoctors.class);
                startActivity(intent);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, EventApp.class);
                startActivity(intent);
            }
        });


    }

        public void onBackPressed() {

            if(drawerLayout.isDrawerOpen(GravityCompat.START)){

                drawerLayout.closeDrawer(GravityCompat.START);


            }else{

                super.onBackPressed();

            }


        }


    @Override
    public boolean onNavigationItemSelected( MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nu:
                Intent intent = new Intent(Home.this, UserProfile.class);
                startActivity(intent);
                break;

            case R.id.nav_home:
                Intent inten = new Intent(Home.this, Find_friends.class);
                startActivity(inten);
                break;



            case R.id.me:
                Intent intenttt = new Intent(Home.this, Workouts.class);
                startActivity(intenttt);
                break;

            case R.id.men:
                Intent intentt = new Intent(Home.this, PermissionsActivity.class);
                startActivity(intentt);
                break;





            case R.id.mm:
                Intent intentttt = new Intent(Home.this, VideoActivity.class);
                startActivity(intentttt);
                break;

            case R.id.Final:
                Intent intentttt1 = new Intent(Home.this, Stopwatch.class);
                startActivity(intentttt1);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
           return true;
        
    }

}