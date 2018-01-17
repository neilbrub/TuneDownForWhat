package com.example.neil.tunedownforwhat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.karlotoy.perfectune.instance.PerfectTune;

public class FrequencyGeneratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency_generator);
        setTitle("Frequency Generator");

        // Switch to return to Tuning Activity
        final Switch toggleSwitch = findViewById(R.id.toggleSwitch2);

        // Since used same switch to arrive from Tuning Activity, current state should be activated:
        toggleSwitch.setChecked(true);

        // Tone activation buttons:
        final ToggleButton bE3 = findViewById(R.id.ButtonE3);
        final ToggleButton bA3 = findViewById(R.id.ButtonA3);
        final ToggleButton bD4 = findViewById(R.id.ButtonD4);
        final ToggleButton bG4 = findViewById(R.id.ButtonG4);
        final ToggleButton bB4 = findViewById(R.id.ButtonB4);
        final ToggleButton bE5 = findViewById(R.id.ButtonE5);

        // Let user know what the buttons do:
        bE3.setText("Start E3");
        bE3.setTextOn("Stop");
        bE3.setTextOff("Start E3");
        bA3.setText("Start A3");
        bA3.setTextOn("Stop");
        bA3.setTextOff("Start A3");
        bD4.setText("Start D4");
        bD4.setTextOn("Stop");
        bD4.setTextOff("Start D4");
        bG4.setText("Start G4");
        bG4.setTextOn("Stop");
        bG4.setTextOff("Start G4");
        bB4.setText("Start B4");
        bB4.setTextOn("Stop");
        bB4.setTextOff("Start B4");
        bE5.setText("Start E5");
        bE5.setTextOn("Stop");
        bE5.setTextOff("Start E5");

        // Using PerfectTune objects to generate tones
        // (https://github.com/karlotoy/perfectTune)
        final PerfectTune tuneE3 = new PerfectTune();
        final PerfectTune tuneA3 = new PerfectTune();
        final PerfectTune tuneD4 = new PerfectTune();
        final PerfectTune tuneG4 = new PerfectTune();
        final PerfectTune tuneB4 = new PerfectTune();
        final PerfectTune tuneE5 = new PerfectTune();

        // Set frequencies for each channel
        tuneE3.setTuneFreq(164.81);
        tuneA3.setTuneFreq(220.00);
        tuneD4.setTuneFreq(293.66);
        tuneG4.setTuneFreq(392.00);
        tuneB4.setTuneFreq(493.88);
        tuneE5.setTuneFreq(659.26);

        // Set listener on switch to change activity:
        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton b, boolean isChecked) {
                if(!isChecked){
                    Intent viewGeneratorIntent = new Intent(b.getContext(), TuningActivity.class);

                    // Clear any playing tones before leaving:
                    clear_active_buttons(0, tuneE3, tuneA3, tuneD4, tuneG4, tuneB4, tuneE5);

                    startActivity(viewGeneratorIntent);
                }
            }
        });

        // Set onClick listeners for buttons
        bE3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Deactivate other pressed buttons:
                clear_active_buttons(1, tuneE3, tuneA3, tuneD4, tuneG4, tuneB4, tuneE5);

                if(bE3.isChecked()){
                    tuneE3.playTune();
                }
                else{
                    tuneE3.stopTune();
                }
            }
        });

        bA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_active_buttons(2, tuneE3, tuneA3, tuneD4, tuneG4, tuneB4, tuneE5);

                if(bA3.isChecked()){
                    tuneA3.playTune();
                }
                else{
                    tuneA3.stopTune();
                }
            }

        });

        bD4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_active_buttons(3, tuneE3, tuneA3, tuneD4, tuneG4, tuneB4, tuneE5);

                if(bD4.isChecked()){
                    tuneD4.playTune();
                }
                else{
                    tuneD4.stopTune();
                }
            }
        });

        bG4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_active_buttons(4, tuneE3, tuneA3, tuneD4, tuneG4, tuneB4, tuneE5);

                if(bG4.isChecked()){
                    tuneG4.playTune();
                }
                else{
                    tuneG4.stopTune();
                }
            }
        });

        bB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_active_buttons(5, tuneE3, tuneA3, tuneD4, tuneG4, tuneB4, tuneE5);

                if(bB4.isChecked()){
                    tuneB4.playTune();
                }
                else{
                    tuneB4.stopTune();
                }
            }

        });

        bE5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_active_buttons(6, tuneE3, tuneA3, tuneD4, tuneG4, tuneB4, tuneE5);

                if(bE5.isChecked()){
                    tuneE5.playTune();
                }
                else{
                    tuneE5.stopTune();
                }
            }
        });

    }


    // Allows only one tone to play at a time (also used to turn off all tones):
    private void clear_active_buttons(int button_pressed, PerfectTune tuneE3, PerfectTune tuneA3,
                                      PerfectTune tuneD4, PerfectTune tuneG4, PerfectTune tuneB4,
                                      PerfectTune tuneE5) {

        // Set all toggle buttons to 'off' state unless just pressed;

        ToggleButton bE3 = findViewById(R.id.ButtonE3);
        ToggleButton bA3 = findViewById(R.id.ButtonA3);
        ToggleButton bD4 = findViewById(R.id.ButtonD4);
        ToggleButton bG4 = findViewById(R.id.ButtonG4);
        ToggleButton bB4 = findViewById(R.id.ButtonB4);
        ToggleButton bE5 = findViewById(R.id.ButtonE5);

        if(button_pressed != 1) {
            if (bE3.isChecked()) {
                bE3.setChecked(false);
                tuneE3.stopTune();
            }
        }

        if(button_pressed != 2) {
            if (bA3.isChecked()) {
                bA3.setChecked(false);
                tuneA3.stopTune();
            }
        }

        if(button_pressed != 3) {
            if (bD4.isChecked()) {
                bD4.setChecked(false);
                tuneD4.stopTune();
            }
        }

        if(button_pressed != 4) {
            if (bG4.isChecked()) {
                bG4.setChecked(false);
                tuneG4.stopTune();
            }
        }

        if(button_pressed != 5) {
            if (bB4.isChecked()) {
                bB4.setChecked(false);
                tuneB4.stopTune();
            }
        }

        if(button_pressed != 6) {
            if (bE5.isChecked()) {
                bE5.setChecked(false);
                tuneE5.stopTune();
            }
        }
    }
}
