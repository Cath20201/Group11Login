package dk.au.mad22spring.healthbuddy.group11.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dk.au.mad22spring.healthbuddy.group11.databinding.ActivityWeatherBinding;
import dk.au.mad22spring.healthbuddy.group11.utilities.PreferenceManager;

public class WeatherActivity extends AppCompatActivity {
    
    private ActivityWeatherBinding weatherBinding;
    private @NonNull ActivityWeatherBinding preferenceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = ActivityWeatherBinding.inflate(getLayoutInflater());
        weatherBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(weatherBinding.getRoot());
        setListeners();
    }

    private void setListeners() {
    }
}
