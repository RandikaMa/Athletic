package com.example.atc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.atc.Workouts.Workouts;

public class WorkoutGenerator extends AppCompatActivity {

    public static int generated = 0;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        // ignore orientation/keyboard change
        super.onConfigurationChanged(newConfig);
    }

    private Matrix matrix = new Matrix();
    private ScaleGestureDetector SGD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workout_generator);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);



        final EditText agetext = findViewById(R.id.age);
        final EditText heighttext = findViewById(R.id.height);
        final EditText weighttext = findViewById(R.id.weight);
        final EditText assesstext = findViewById(R.id.selfassess);
        final EditText generatorlist = findViewById(R.id.GeneratorList);

        Button Generate = findViewById(R.id.Generate);





        Generate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InputMethodManager imm =
                                (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(weighttext.getWindowToken(), 0);

                        Integer age = null;
                        Integer weight = null;
                        Integer height = null;
                        Integer skillassess = null;
                        Integer spinn = null;
                        Integer spinn1 = null;
                        Integer spinn2 = null;


                        String nullerror = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b><i><font color=\"#FF0000\">You did not enter a valid<br>" +
                                "&nbsp&nbsp&nbsp&nbsp&nbsp value for one or<br>&nbsp&nbsp&nbsp&nbsp&nbsp more fields.</font></i></b>";
                        try {
                            age = Integer.valueOf(agetext.getText().toString());
                            weight = Integer.valueOf(weighttext.getText().toString());
                            height = Integer.valueOf(heighttext.getText().toString());
                            skillassess = Integer.valueOf(assesstext.getText().toString());
                            spinn = Integer.valueOf(spinner.getSelectedItem().toString());
                            spinn1 = Integer.valueOf(spinner1.getSelectedItem().toString());
                            spinn2 = Integer.valueOf(spinner2.getSelectedItem().toString());

                        }
                        catch (NumberFormatException e) {
                            generatorlist.setText(Html.fromHtml(nullerror));
                            age = 0;
                            weight = 0;
                            height = 0;
                            skillassess = 0;
                            spinn= 0;
                            spinn1= 0;
                            spinn2= 0;

                        }
                        int skillage = 0;
                        int skillbmi = 0;


                        if (age > 0 && age < 10) {

                            skillage = 1;

                        }

                        if (age >= 10 && age < 12) {

                            skillage = 3;

                        }

                        if (age >= 12 && age < 14) {

                            skillage = 5;

                        }

                        if (age == 14) {

                            skillage = 7;

                        }

                        if (age >= 15 && age < 17) {

                            skillage = 8;

                        }

                        if (age >= 17 && age < 20) {

                            skillage = 9;

                        }

                        if (age >= 20 && age < 27) {

                            skillage = 10;

                        }

                        if (age >= 27 && age < 30) {

                            skillage = 9;

                        }

                        if (age >= 30 && age < 35) {

                            skillage = 8;

                        }

                        if (age >= 35 && age < 45) {

                            skillage = 7;

                        }

                        if (age >= 45 && age < 50) {

                            skillage = 6;

                        }

                        if (age >= 50 && age < 60) {

                            skillage = 5;

                        }

                        if (age >= 60 && age < 70) {

                            skillage = 3;

                        }

                        if (age >= 70) {

                            skillage = 1;

                        }

                        //bmi formula

                        double heightsq = height*height;
                        double whratio = weight/heightsq;

                        double bmi = 703*whratio;

                        //end bmi formula

                        if (bmi == 0  || bmi < 0) {
                            skillbmi = 0;
                        }

                        if (bmi <= 16 ) {
                            skillbmi = 1;
                        }

                        if (bmi > 16 && bmi < 17 ) {
                            skillbmi = 2;
                        }

                        if (bmi >= 17 && bmi < 18.5) {
                            skillbmi = 4;
                        }

                        if (bmi >= 18.5 && bmi < 19) {
                            skillbmi = 6;
                        }

                        if (bmi >= 19 && bmi < 20) {
                            skillbmi = 8;
                        }

                        if (bmi >= 20 && bmi < 24) {
                            skillbmi = 10;
                        }

                        if (bmi >= 24 && bmi < 25) {
                            skillbmi = 8;
                        }

                        if (bmi >= 25 && bmi < 27) {
                            skillbmi = 5;
                        }

                        if (bmi >= 27 && bmi < 30) {
                            skillbmi = 3;
                        }

                        if (bmi >= 30) {
                            skillbmi = 1;
                        }

                        double skillageweighted = skillage*3.5;
                        double skillbmiweighted = skillbmi*2.5;
                        double skillassessweighted = skillassess*4;

                        if (skillage == 1) {

                            skillageweighted = 6.5;
                            skillbmiweighted = skillbmi*1;
                            skillassessweighted = skillassess*2.5;

                        }

                        if (skillbmi == 1) {
                            skillageweighted = skillage*1;
                            skillbmiweighted = 6.5;
                            skillassessweighted = skillassess*2.5;
                        }

                        if (skillbmi == 1 && skillage == 1) {
                            skillageweighted = 3.75;
                            skillbmiweighted = 3.75;
                            skillassessweighted = skillassess*2.5;
                        }

                        double skillsum = skillageweighted+skillbmiweighted+skillassessweighted;
                        double skilllvlpre = skillsum/10;
                        Integer skilllvlfinal = (int)Math.round(skilllvlpre);

                        if (height == 0 || skillage == 0 || skillbmi == 0 || skillassess == 0 || weight == 0 || skillassess > 10) {
                            skilllvlfinal = 0;
                        }

                        double rbmi = bmi * 100;
                        double rbmif = (int)Math.round(rbmi);
                        double rbmifinal = rbmif/100;

                        String pbmi = "&nbsp&nbsp Your BMI is : "+String.valueOf(rbmifinal)+"<br><br>";

                        String w1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String w2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String w3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 3</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String w4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 3</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String w5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10):5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 50M X 2</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String w6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";




                        String w7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br><&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";




                        String w8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 1<br><br>&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String w9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";




                        String w10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";




                        String a1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String a2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String a3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 3</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String a4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 3</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String a5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10):5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 50M X 2</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String a6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String a7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br><&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String a8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 1<br><br>&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String a9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String a10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String b1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String b2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String b3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 7<br><br>&nbsp&nbsp Squat X 7</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 3</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String b4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 7<br><br>&nbsp&nbsp Squat X 7</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 7<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 3</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String b5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 7<br><br>&nbsp&nbsp Squat X 7</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 7<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10):5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 50M X 2</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 2<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String b6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 7<br><br>&nbsp&nbsp Squat X 7</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 7<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";




                        String b7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 7<br><br>&nbsp&nbsp Squat X 7</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br><&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 3<br><br>&nbsp&nbsp 40M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";




                        String b8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 7<br><br>&nbsp&nbsp Squat X 8</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 50M X 1<br><br>&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";



                        String b9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 10<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 10<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 10 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 2<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";




                        String b10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 10<br><br>&nbsp&nbsp Squat X 10</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>";






                        String c1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String c2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String c3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String c4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String c5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String c6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String c7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String c8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String c9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String c10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String d1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String d2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String d3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String d4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String d5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String d6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String d7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String d8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String d9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String d10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String e1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String e2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String e3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String e4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String e5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String e6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String e7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String e8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String e9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String e10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";







                        String f1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String f2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String f3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String f4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String f5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String f6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String f7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String f8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String f9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String f10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String g1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String g2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String g3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String g4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String g5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String g6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String g7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String g8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String g9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String g10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";







                        String h1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String h2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String h3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String h4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String h5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String h6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String h7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String h8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String h9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String h10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";







                        String i1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String i2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String i3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String i4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String i5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String i6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String i7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String i8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String i9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String i10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String j1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String j2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String j3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String j4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String j5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String j6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String j7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String j8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String j9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String j10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";








                        String jj1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String jj2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String jj3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String jj4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String jj5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String jj6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String jj7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String jj8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String jj9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String jj10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";







                        String k1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String k2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String k3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String k4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String k5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String k6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String k7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String k8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String k9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String k10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String l1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String l2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String l3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String l4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String l5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String l6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String l7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String l8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String l9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String l10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String m1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String m2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String m3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String m4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String m5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String m6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String m7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String m8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String m9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String m10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String n1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String n2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String n3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String n4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String n5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String n6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String n7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String n8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String n9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String n10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String o1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String o2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String o3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String o4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String o5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String o6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String o7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String o8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String o9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String o10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String p1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String p2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String p3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String p4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String p5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String p6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String p7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String p8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String p9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String p10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String q1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String q2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String q3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String q4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String q5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String q6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String q7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String q8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String q9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String q10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String r1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String r2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String r3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String r4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String r5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String r6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String r7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String r8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String r9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String r10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String s1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String s2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String s3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String s4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String s5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String s6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String s7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String s8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String s9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String s10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String t1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String t2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String t3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String t4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String t5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String t6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String t7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String t8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String t9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String t10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String u1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String u2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String u3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String u4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String u5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String u6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String u7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String u8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String u9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String u10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String v1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String v2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String v3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String v4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String v5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String v6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String v7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String v8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String v9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String v10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String x1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String x2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String x3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String x4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String x5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String x6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String x7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String x8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String x9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String x10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";






                        String y1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String y2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String y3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String y4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String y5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String y6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String y7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String y8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String y9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String y10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String z1 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">"+ "&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font> " +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50 M X 2<br><br>&nbsp&nbsp 25M X 2</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 1<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String z2 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp 25M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 20M X 2<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 2<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String z3 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 4<br><br>&nbsp&nbsp Squat X 4</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 3<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 2<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 100M X 1<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 3<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String z4 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 4</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 50M X 2<br><br>Rest 6 Min</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 50M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Squat X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp 60M X 1<br><br>&nbsp&nbsp 40M X 1</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 1<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>"+
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String z5 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 4<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 5<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String z6 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 5<br><br>&nbsp&nbsp Squat X 5</font><br><br><font color=\"#2D7A0F\">" + "&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 5</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 6<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String z7 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 7<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";





                        String z8 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 3<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 8<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";



                        String z9 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font><br><br><font color=\"#190EE8\">&nbsp&nbsp 15M X 4<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 9<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";




                        String z10 = "&nbsp&nbsp<u>&nbsp&nbsp Monday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 15 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 6<br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp 10M X 6</font>" +
                                "<&nbsp&nbsp Tuesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 10 min Run<br><br>&nbsp&nbsp Jumping Jacks X 12<br><br>&nbsp&nbsp Walking Knee Hugs 10M X 3</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp Arm Cycles X 15 X 2<br><br>&nbsp&nbsp Side Shuffles<br><br>&nbsp&nbsp Rest 5 Min</font><br><br><font color=\"#190EE8\">&nbsp&nbsp Backpedaling 10M X 3<br><br>&nbsp&nbsp Lunges 10M X 2<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Wednesday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp Push-Up X 6<br><br>&nbsp&nbsp Squat X 6</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 5<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 50M X 3</font><br><br>&nbsp&nbsp Rest 5 Min<br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Thursday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 20 min Run<br><br>&nbsp&nbsp 100M X 2<br><br>&nbsp&nbsp Rest 6 Min</font><br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Friday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp 60M X 4<br><br>&nbsp&nbsp 40M X 2</font><br><br><font color=\"#2D7A0F\">&nbsp&nbsp 20M X 3<br><br>&nbsp&nbsp Rest 10 Min<br><br>&nbsp&nbsp 10 Min Jogging</font><br><br>&nbsp&nbsp Warm Down</font>" +
                                "<br><br><br>&nbsp&nbsp<u>&nbsp&nbsp Saturday</u> <br><br> &nbsp&nbsp Your ability level is (1-10): 10<br><br><font color=\"#FF0000\">&nbsp&nbsp 30 min Run<br><br>&nbsp&nbsp Warm Down</font>";







                        if (skilllvlfinal == 0) {
                            generatorlist.setText(Html.fromHtml(nullerror));
                        }

                        if (skillage==1 && skilllvlfinal == 1 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + w1));
                        }else if(skillage==1 && skilllvlfinal == 1 && spinn == 75 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + a1));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 1 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b1));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j1));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y1));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z1));
                        }



                        if (skillage==1 && skilllvlfinal == 2 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w2));
                        }else if(skillage==1 && skilllvlfinal == 2 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a2));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 2 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b2));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 2 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j2));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y2));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z2));
                        }




                        if (skillage==1 && skilllvlfinal == 3 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w3));
                        }else if(skillage==1 && skilllvlfinal == 3 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a3));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 3 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b3));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 3 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j3));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y3));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z3));
                        }





                        if (skillage==1 && skilllvlfinal == 4 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w4));
                        }else if(skillage==1 && skilllvlfinal == 4 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a4));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 4 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b4));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 4 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j4));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y4));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z4));
                        }





                        if (skillage==1 && skilllvlfinal == 5 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w5));
                        }else if(skillage==1 && skilllvlfinal == 5 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a5));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 5 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b5));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 5 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j5));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y5));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z5));
                        }




                        if (skillage==1 && skilllvlfinal == 6 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w6));
                        }else if(skillage==1 && skilllvlfinal == 6 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a6));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 6 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b6));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 6 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j6));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y6));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z6));
                        }




                        if (skillage==1 && skilllvlfinal == 7 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w7));
                        }else if(skillage==1 && skilllvlfinal == 7 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a7));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 7 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b7));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 7 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j7));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y7));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z7));
                        }




                        if (skillage==1 && skilllvlfinal == 8 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + w8));
                        }else if(skillage==1 && skilllvlfinal == 8 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a8));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 8 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b8));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 8 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj8));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + j8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y8));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z8));
                        }





                        if (skillage==1 && skilllvlfinal == 9 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w9));
                        }else if(skillage==1 && skilllvlfinal == 9 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a9));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 9 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b9));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 9 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j9));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y9));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z9));
                        }





                        if (skillage==1 && skilllvlfinal == 10 && spinn == 75 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi +w10));
                        }else if(skillage==1 && skilllvlfinal == 10 && spinn == 75 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + a10));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 10 && spinn == 100  && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + b10));
                        }else if((skillage==3 || skillage==5) && skilllvlfinal == 10 && spinn == 100 && spinn1 == 1 && spinn2 == 7){
                            generatorlist.setText(Html.fromHtml(pbmi + c10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5){
                            generatorlist.setText(Html.fromHtml(pbmi + d10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + e10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + f10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + g10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + h10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + i10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + j10));
                        }else if(skillage==8 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + jj10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + k10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 100 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + l10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + m10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 200 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + n10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + o10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 400 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + p10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + q10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 800 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + r10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + s10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 1500 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + t10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + u10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 3000 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + v10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 10000 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + x10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 5) {
                            generatorlist.setText(Html.fromHtml(pbmi + y10));
                        }else if(skillage==9 && skilllvlfinal == 1 && spinn == 110 && spinn1 == 1 && spinn2 == 7) {
                            generatorlist.setText(Html.fromHtml(pbmi + z10));
                        }





                        generated = skilllvlfinal + 1;
                    }
                });
//(End) Workout Generator Algorithm

        //Button to go to Exercise Library



       

        //(End) Button
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        SGD.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("event", "done" + " " + matrix);
            return true;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpTo(this, new Intent(this,
                       Workouts.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}