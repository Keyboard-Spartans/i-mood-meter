package com.example.imoodmeter;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.imoodmeter.controller.MoodController;
import com.example.imoodmeter.model.MoodModel;
import com.example.imoodmeter.utils.moodMeterUtils;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import java.util.List;


public class RecordMoodMainFragment extends Fragment implements View.OnClickListener{
    moodMeterUtils moodUtils = new moodMeterUtils();

    public RecordMoodMainFragment() {
        super(R.layout.record_mood_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Labels for mood values
        Slider slider = view.findViewById(R.id.mood_value);
        slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return moodUtils.floatToMoodConverter(value);
            }
        });

        // fetch value if we're editing
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null) {
            boolean editingMood = extras.getBoolean("moodEdit");
            if (editingMood == true) {
                List<MoodModel> moods = MoodController.getMoods();
                MoodModel latestMood = moods.get(moods.size()-1);
                slider.setValue(latestMood.getMoodValue());

                Button recordButton = view.findViewById(R.id.record_button);
                recordButton.setText(moodUtils.floatToRecordButtonConverter(latestMood.getMoodValue()));
            }
        }

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                Button recordButton = view.findViewById(R.id.record_button);
                recordButton.setText(moodUtils.floatToRecordButtonConverter(value));
            }
        });

        // Disclaimer page
        Button disclaimerButton = view.findViewById(R.id.disclaimer);
        disclaimerButton.setOnClickListener(this);

        // Link to add comment screen
        Button recordButton = view.findViewById(R.id.record_button);
        recordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // do something in response to button click
        if (view.getId() == R.id.record_button) {
            // store the value of slider
            Slider slider = getActivity().findViewById(R.id.mood_value);
            float moodVal = slider.getValue();

            Log.w("MAIN_ACT", "moodVal: " + moodVal);

            Intent intent = new Intent(this.getContext(), RecordMoodAddComment.class);

            // set value if we're editing
            Bundle extras = getActivity().getIntent().getExtras();
            if(extras != null) {
                boolean editingMood = extras.getBoolean("moodEdit");
                if (editingMood == true) {
                    intent.putExtra("moodEdit", true);
                }
            }

            intent.putExtra("moodValue", moodVal);
            startActivity(intent);
        }
        if (view.getId() == R.id.disclaimer) {
            Intent intent = new Intent(this.getContext(), DisclaimerActivity.class);
            startActivity(intent);
        }
    }
}