package com.example.sakhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalorieCounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        Button calorie = (Button) findViewById(R.id.button3);

        calorie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Period Tracker
                Intent i = new Intent(getApplicationContext(),CalorieChart.class);
                startActivity(i);
            }
        });
    }

    public void pcod(View view)
    {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lybrate.com/topic/pcod-diet-chart"));
        startActivity(browserIntent);
    }

    public void anemia(View view)
    {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lybrate.com/topic/iron-deficiency-anemia-diet"));
        startActivity(browserIntent);
    }
}