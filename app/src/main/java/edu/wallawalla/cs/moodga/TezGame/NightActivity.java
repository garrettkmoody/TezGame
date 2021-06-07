package edu.wallawalla.cs.moodga.TezGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class NightActivity extends AppCompatActivity {

    private TextView dayTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);
        dayTV = findViewById(R.id.dayTV);
        Integer day;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                day = 0;
            } else {
                day = extras.getInt("day");
            }
        } else {
            day = (Integer) savedInstanceState.getSerializable("day");
        }
        dayTV.setText("Day " + day);
        (new Handler()).postDelayed(this::finisher, 1000);

    }

    private void finisher() {
        this.finish();
    }
}