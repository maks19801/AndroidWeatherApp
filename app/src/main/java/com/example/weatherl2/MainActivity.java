package com.example.weatherl2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.weatherl2.adapters.DayAdapter;
import com.example.weatherl2.models.DayForecast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<DayForecast> weathers = new ArrayList<DayForecast>(Arrays.asList(
            new DayForecast("Monday", "17", "23", "Windy", "3.6"),
            new DayForecast("Tuesday", "17", "23", "Windy", "3.6"),
            new DayForecast("Wednesday", "17", "23", "Windy", "3.6"),
            new DayForecast("Thursday", "17", "23", "Windy", "3.6"),
            new DayForecast("Friday", "17", "23", "Windy", "3.6"),
            new DayForecast("Saturday", "17", "23", "Windy", "3.6"),
            new DayForecast("Sunday", "17", "23", "Windy", "3.6")));

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        DayAdapter dayAdapter = new DayAdapter(getApplicationContext(), weathers);
        ListView listView = findViewById(R.id.days);
        listView.setAdapter(dayAdapter);
    }
}