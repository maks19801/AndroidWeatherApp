package com.example.weatherl2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherl2.adapters.DayAdapter;
import com.example.weatherl2.models.DayForecast;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.ThreeHourForecastCallback;
import com.kwabenaberko.openweathermaplib.models.threehourforecast.ThreeHourForecast;
import com.kwabenaberko.openweathermaplib.models.threehourforecast.ThreeHourWeather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static boolean isFahrengheits = false;
    private String message = "Default is Celsius";
    private OpenWeatherMapHelper weatherHelper;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        weatherHelper = new OpenWeatherMapHelper(getString(R.string.OPEN_WEATHER_MAP_API_KEY));
        weatherHelper.setUnits(Units.METRIC);

    }
    private void updateWeather() {
        String cityName = ((TextView)findViewById(R.id.cityName)).getText().toString();
        getHoursWeather(cityName);
    }
    private void getHoursWeather(String city) {
            weatherHelper.getThreeHourForecastByCityName(city, new ThreeHourForecastCallback() {
            ArrayList<DayForecast> weathers = new ArrayList<>();
            @Override
            public void onSuccess(ThreeHourForecast threeHourForecast) {
                for(int i =0; i < threeHourForecast.getList().size()-1; i++) {
                    Long date = threeHourForecast.getList().get(i).getDt();
                    double tempMin = threeHourForecast.getList().get(i).getMain().getTempMin();
                    double tempMax = threeHourForecast.getList().get(i).getMain().getTempMax();
                    String description = threeHourForecast.getList().get(i).getWeatherArray().get(0).getDescription();
                    double windSpeed = threeHourForecast.getList().get(i).getWind().getSpeed();
                    double windDirection = threeHourForecast.getList().get(i).getWind().getDeg();

                    DayForecast dayForecast = new DayForecast(date, tempMin, tempMax, description, windSpeed, windDirection);
                    weathers.add(dayForecast);
                }
                setWeatherOnUi(weathers);
            }

            @Override
            public void onFailure(Throwable throwable) {
                doOnWeatherFailure(throwable);
            }
        });
    }
    private void doOnWeatherFailure(Throwable throwable) {
        ((TextView)findViewById(R.id.text)).setText(throwable.getMessage());
    }

    private void setWeatherOnUi(ArrayList<DayForecast> weathers) {
        String cityName = ((TextView)findViewById(R.id.cityName)).getText().toString();
        ((TextView)findViewById(R.id.text)).setText(cityName);
        DayAdapter dayAdapter = new DayAdapter(getApplicationContext(), weathers);
        ListView listView = findViewById(R.id.days);
        listView.setAdapter(dayAdapter);
    }
    public void goWeather(View view) {
        updateWeather();
    }


    public enum WeatherType {
        Current,
        Hourly,
        Daily
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                temperatureView();
                return true;
            case R.id.action_locations:
                changeLocation();
                return true;
            case R.id.action_color_style:
                changeStyle();
                return true;
            case R.id.action_something:
                Toast.makeText(MainActivity.this, "Reserved for future feature", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_appInfo:
                applicationInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void applicationInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Application Info");
        builder.setMessage("Weather Application \ncreated by Porkhovskyi Maksym\n 2020");
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void changeStyle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View alertView1 = getLayoutInflater().inflate(R.layout.toggle_button, null);
        builder.setView(alertView1);
        builder.setTitle("Dark Style");
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (buttonView.isChecked()) {
//                    setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar);
//                }
//            }
//        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void changeLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View alertView1 = getLayoutInflater().inflate(R.layout.locations_alert, null);
        builder.setView(alertView1);
        builder.setTitle("Choose Location");
//        EditText editText = (EditText) findViewById(R.id.editText);
//        editText.addTextChangedListener(new TextWatcher() {
//
//            public void afterTextChanged(Editable s) {
//            }
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                TextView textView = (TextView) findViewById(R.id.textView);
//                textView.setText(s);
//            }
//        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "Location changed", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void temperatureView(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose temperature view");

        builder.setSingleChoiceItems(
                new String[]{"Celsius", "Fahrenheits"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        switch (id) {
                            //--celsius chosen	-------------------------
                            case 0:
                                message = "Celsius";
                                isFahrengheits = false;
                                break;
                            //--	Fahrenheits	chosen--------------------------
                            case 1:
                                message = "Fahrenheits";
                                isFahrengheits = true;
                                break;
                            default:
                                message = "Default is Celsius";
                        }
                    }

                });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}