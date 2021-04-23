package com.example.imoodmeter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.imoodmeter.controller.MoodController;
import com.example.imoodmeter.model.MoodModel;
import com.example.imoodmeter.utils.moodMeterUtils;

import java.util.ArrayList;

public class RecordMoodRecorded extends AppCompatActivity implements View.OnClickListener{
    moodMeterUtils moodUtils = new moodMeterUtils();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_mood_recorded);

        Button addNewMood = findViewById(R.id.add_another_mood_button);
        addNewMood.setOnClickListener(this);

        // write appropriate mood on recap
        TextView moodRecap = findViewById(R.id.mood_recap);

        float moodVal = MoodController.getMoods().get(MoodController.getMoodsSize()-1).getMoodValue();
        Log.i("MOOD_RECORDED LAST ELEM", Float.toString(moodVal));
        String moodRecapStr = moodUtils.floatToRecapConverter(moodVal);
        moodRecap.setText(moodRecapStr);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_another_mood_button) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("moodRecorded", false);
            startActivity(intent);
        }
    }
}