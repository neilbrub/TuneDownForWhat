package com.example.neil.tunedownforwhat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class TuningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning);
        setTitle("Tuner");

        final Switch toggleSwitch = findViewById(R.id.toggleSwitch);

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

    }
}
