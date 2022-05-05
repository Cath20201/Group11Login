package dk.au.mad22spring.healthbuddy.group11.Animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import dk.au.mad22spring.healthbuddy.group11.activities.MainActivity;
import dk.au.mad22spring.healthbuddy.group11.R;
import dk.au.mad22spring.healthbuddy.group11.activities.SignInActivity;

public class Health_Fitness_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_fitness);

        TextView texthealthBuddy = findViewById(R.id.texthealthBuddy);
        texthealthBuddy.animate().translationX(1000).setDuration(1000).setStartDelay(3500);

        Thread thread = new Thread(){
            public void run(){
                try{
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Health_Fitness_activity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        thread.start();
    }

}