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

import java.util.ArrayList;

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
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(R.layout.day_forecast, null);
        }

        DayForecast p = getItem(position);

        if (p != null) {
            TextView day_of_the_week = v.findViewById(R.id.day_of_the_week);
            TextView tem_min = v.findViewById(R.id.tem_min);
            TextView tem_max = v.findViewById(R.id.tem_max);
            TextView status = v.findViewById(R.id.status);
            TextView wind = v.findViewById(R.id.wind);

            day_of_the_week.setText(p.day);
            if(MainActivity.isFahrengheits){
                double min = Double.parseDouble(p.tempMin) * 1.8 + 32;
                double max = Double.parseDouble(p.tempMax) * 1.8 + 32;
                tem_min.setText(Double.toString(min));
                tem_max.setText(Double.toString(max));
            } else{
                tem_min.setText(p.tempMin);
                tem_max.setText(p.tempMax);
            }
            status.setText(p.status);
            wind.setText(p.wind);
        }

        return v;
    }
}
