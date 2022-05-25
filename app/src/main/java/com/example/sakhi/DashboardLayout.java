package com.example.sakhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardLayout extends AppCompatActivity {

    String NameHolder;
    TextView Name;
    Button LogOUT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        Name = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);
        Intent intent = getIntent();

        // Dashboard Period Tracker Button
        Button btn_newsfeed = (Button) findViewById(R.id.btnprofile);
        btn_newsfeed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Period Tracker
                Intent i = new Intent(getApplicationContext(), PeriodTracker.class);
                startActivity(i);
            }
        });

        // Dashboard Calorie Counter button
        Button btnhi  = (Button) findViewById(R.id.btnhistory);
        btnhi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Period Tracker
                Intent i = new Intent(getApplicationContext(), CalorieCounter.class);
                startActivity(i);
            }
        });
//
//        // Dashboard Messages button
//        Button btn_messages = (Button) findViewById(R.id.btn_messages);
//
//        // Dashboard Places button
//
       Button btn_places = (Button) findViewById(R.id.btnfeedbak);

       // Button btn_places  = (Button) findViewById(R.id.btnhistory);
        btn_places.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Period Tracker
                Intent i = new Intent(getApplicationContext(),GooglePlacesActivity.class);
                startActivity(i);
            }
        });
        // Receiving User Email Send By MainActivity.
        NameHolder = intent.getStringExtra(Login.userName);

        // Setting up received email to TextView.
        Name.setText(Name.getText().toString()+ NameHolder);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(DashboardLayout.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }

        });
    }
    public void feed1(View view)
    {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfqCpjji6GWrFCIqyWntmlIHsoM9gz1lzYbGYi6ITf_BjEuEQ/viewform?usp=sf_link"));
        startActivity(browserIntent);
    }


}