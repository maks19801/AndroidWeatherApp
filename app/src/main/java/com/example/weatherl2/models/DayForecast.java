package com.example.weatherl2.models;

public class DayForecast {
    public String day;
    public String tempMin;
    public String tempMax;
    public String status;
    public String wind;

    public DayForecast(String day, String tempMin, String tempMax, String status, String wind) {
        this.day = day;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.status = status;
        this.wind = wind;
    }
}
