package dk.au.mad22spring.healthbuddy.group11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class WeatherActivity extends AppCompatActivity {

    private RelativeLayout homeweatherRL;
    private RecyclerView weatherRV;
    private ProgressBar loadingPB;
    private TextView cityNameTV, tempTV, conditionTV;
    private TextInputEditText cityEdt;
    private ImageView backIV, iconIV, searchIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        homeweatherRL = findViewById(R.id.RLWeatherHome);
        weatherRV = findViewById(R.id.RVWeather);
        loadingPB = findViewById(R.id.PBLoading);
        cityNameTV = findViewById(R.id.TVCityname);
        tempTV = findViewById(R.id.TVTemp);
        conditionTV = findViewById(R.id.TVCondition);
        cityEdt = findViewById(R.id.EdtCity);
        backIV = findViewById(R.id.IVBackG);
        iconIV = findViewById(R.id.IVIcon);
        searchIV = findViewById(R.id.IVSearch);

    }
}