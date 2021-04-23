package com.example.imoodmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.imoodmeter.utils.moodMeterUtils;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;


public class RecordMoodMainActivity extends AppCompatActivity implements View.OnClickListener{
    moodMeterUtils moodUtils = new moodMeterUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_mood_main);

        // Labels for mood values
        Slider slider = findViewById(R.id.mood_value);
        slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return moodUtils.floatToMoodConverter(value);
            }
        });

        // Link to add comment screen
        Button recordButton = (Button) findViewById(R.id.record_button);
        recordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // do something in response to button click
        if (view.getId() == R.id.record_button) {
            // store the value of slider
            Slider slider = findViewById(R.id.mood_value);
            float moodVal = slider.getValue();

            Log.w("MAIN_ACT", "moodVal: " + moodVal);

            Intent intent = new Intent(this, RecordMoodAddComment.class);
            intent.putExtra("moodValue", moodVal);
            startActivity(intent);
        }
    }
}