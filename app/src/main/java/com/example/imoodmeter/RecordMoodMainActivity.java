package com.example.imoodmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.util.Locale;

public class RecordMoodMainActivity extends AppCompatActivity implements View.OnClickListener{

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
                return floatToMoodConverter(value);
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
            Intent intent = new Intent(this, RecordMoodAddComment.class);
            startActivity(intent);
        }
    }

    private String floatToMoodConverter(float value) {
        switch ((int)value){
            case 0:
                return "Angry";
            case 1:
                return "Sad";
            case 2:
                return "Neutral";
            case 3:
                return "Content";
            case 4:
                return "Happy";
            case 5:
                return "Excited";
        }
        return "INVALID";
    }
}