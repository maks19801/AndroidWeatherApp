package com.example.weatherl2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherl2.R;
import com.example.weatherl2.models.DayForecast;
import com.example.weatherl2.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayAdapterViewHolder> {
    private ArrayList<DayForecast> list;


    public DayAdapter(ArrayList<DayForecast> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DayAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForDayForecast = R.layout.day_forecast;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForDayForecast, viewGroup,false);
        DayAdapterViewHolder viewHolder = new DayAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapterViewHolder holder, int position) {
        TextView dateTextView = holder.dateTextView;
        TextView minTempTextView = holder.minTempTextView;
        TextView maxTempTextView = holder.maxTempTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView windSpeedTextView = holder.windSpeedTextView;
        TextView windDirTextView = holder.windDirTextView;

        dateTextView.setText(String.valueOf(list.get(position).getDate()));
        minTempTextView.setText(String.valueOf(list.get(position).getTempMin()) );
        maxTempTextView.setText(String.valueOf(list.get(position).getTempMax()));
        descriptionTextView.setText(list.get(position).getDescription());
        windSpeedTextView.setText(String.valueOf(list.get(position).getWindSpeed()));
        windDirTextView.setText(String.valueOf(list.get(position).getWindDirection()));

    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }
    public void setWeatherData(ArrayList<DayForecast> myList){
        list = myList;
        notifyDataSetChanged();
    }
    public class DayAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView dateTextView;
        public TextView minTempTextView;
        public TextView maxTempTextView;
        public TextView descriptionTextView;
        public TextView windSpeedTextView;
        public TextView windDirTextView;
        public DayAdapterViewHolder(View view){
            super(view);
            dateTextView = view.findViewById(R.id.date);
            minTempTextView = view.findViewById(R.id.tem_min);
            maxTempTextView = view.findViewById(R.id.tem_max);
            descriptionTextView = view.findViewById(R.id.description);
            windSpeedTextView = view.findViewById(R.id.wind_speed);
            windDirTextView = view.findViewById(R.id.wind_direction);
        }
    }
}
