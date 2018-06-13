package com.example.neil.tunedownforwhat;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.txusballesteros.widgets.FitChart;

public class TuningActivity extends AppCompatActivity {

    private int MAX_SEEK = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning);
        setTitle("Tuner");

        // Set controls
        final Switch toggleSwitch = findViewById(R.id.toggleSwitch);
        final SeekBar slider = findViewById(R.id.seekBar);
        final TextView progressDisplay = findViewById(R.id.textBox);
        final FitChart chart = findViewById(R.id.fitChart);

        // Configure controls
        slider.setMax(MAX_SEEK);

        // Set listeners
        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton b, boolean isChecked){
                if(isChecked) {
                    Intent viewGeneratorIntent = new Intent(b.getContext(),
                            FrequencyGeneratorActivity.class);
                    startActivity(viewGeneratorIntent);
                }
            }
        });

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar slider, int newValue, boolean fromUser) {
                progressDisplay.setText(Integer.toString(newValue));
                setOnChart(chart, slider.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar slider) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar slider) {
            }
        });

    }

    private void setOnChart(FitChart chart, int inputVal) {

        double median = 4.5;
        double percentStep = 25.0 / median;

        double indicator = (inputVal % 10) - median;
        double percentProgress = 50.0 + (indicator * percentStep);
        double offBy = Math.abs(percentProgress - 50.0);

        int color;
        if (offBy < 1 * percentStep) color = Color.parseColor("#00cc00");
        else if (offBy < 2 * percentStep) color = Color.parseColor("#bfff00");
        else if (offBy < 3 * percentStep) color = Color.parseColor("#ffbf00");
        else if (offBy < 4 * percentStep) color = Color.parseColor("#ff8000");
        else color = Color.parseColor("#ff4000");

        chart.setValue((float) percentProgress, color);
    }
}
