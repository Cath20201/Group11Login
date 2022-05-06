package dk.au.mad22spring.healthbuddy.group11.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;





import dk.au.mad22spring.healthbuddy.group11.R;
import dk.au.mad22spring.healthbuddy.group11.databinding.ActivityMainBinding;
import dk.au.mad22spring.healthbuddy.group11.utilities.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(getApplicationContext());

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        setListeners();



    }

    private void setListeners() {
        mainBinding.dachWeather.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), WeatherActivity.class)));
    }
}