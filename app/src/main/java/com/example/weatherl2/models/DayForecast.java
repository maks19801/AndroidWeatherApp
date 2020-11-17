package com.example.weatherl2.models;

public class DayForecast {
    public Long date;
    public double tempMin;
    public double tempMax;
    public String description;
    public double windSpeed;
    public double windDirection;

    public DayForecast(Long date, double tempMin, double tempMax, String description, double windSpeed, double windDirection) {
        this.date = date;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.description = description;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }
}
