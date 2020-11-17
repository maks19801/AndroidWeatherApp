package com.example.weatherl2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.weatherl2.R;
import com.example.weatherl2.models.DayForecast;
import com.example.weatherl2.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayAdapter extends ArrayAdapter<DayForecast> {
    private ArrayList<DayForecast> list;
    private Context mContext;

    public DayAdapter(Context context, ArrayList<DayForecast> list) {
        super(context, 0 , list);
        mContext = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.day_forecast, null);
        }

        DayForecast p = getItem(position);

        if (p != null) {

            TextView tem_min = v.findViewById(R.id.tem_min);
            TextView tem_max = v.findViewById(R.id.tem_max);
            TextView status = v.findViewById(R.id.status);
            TextView wind_speed = v.findViewById(R.id.wind_speed);
            TextView wind_direction = v.findViewById(R.id.wind_direction);

            Date dateObject = new Date(p.getDate()*1000);
            TextView date = v.findViewById(R.id.format_date);
            String formattedDate = formatDate(dateObject);
            date.setText(formattedDate);
            if(MainActivity.isFahrengheits){
                double min = (p.tempMin) * 1.8 + 32;
                double max = (p.tempMax) * 1.8 + 32;
                tem_min.setText(Double.toString(min));
                tem_max.setText(Double.toString(max));
            } else{
                tem_min.setText(Double.toString(p.tempMin));
                tem_max.setText(Double.toString(p.tempMax));
            }
            status.setText(p.getDescription());
            wind_speed.setText(Double.toString(p.getWindSpeed()));
            wind_direction.setText(Double.toString(p.getWindDirection()));
        }

        return v;
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, LLL dd, yyyy HH:mm");
        return dateFormat.format(dateObject);
    }
}
