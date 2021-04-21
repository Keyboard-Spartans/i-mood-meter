package com.example.imoodmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.imoodmeter.utils.moodMeterUtils;

public class RecordMoodRecorded extends AppCompatActivity implements View.OnClickListener{
    moodMeterUtils moodUtils = new moodMeterUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_mood_recorded);

        Button addNewMood = findViewById(R.id.add_another_mood_button);
        addNewMood.setOnClickListener(this);

        // write appropriate mood on recap
        TextView moodRecap = findViewById(R.id.mood_recap);
        Bundle extras = getIntent().getExtras();

        float moodVal = extras.getFloat("moodValue");
        String moodRecapStr = moodUtils.floatToRecapConverter(moodVal);
        moodRecap.setText(moodRecapStr);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_another_mood_button) {
            Intent intent = new Intent(this, RecordMoodMainActivity.class);
            startActivity(intent);
        }
    }
}