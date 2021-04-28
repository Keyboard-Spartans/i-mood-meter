package com.example.imoodmeter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.imoodmeter.utils.moodMeterUtils;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;


public class RecordMoodMainFragment extends Fragment implements View.OnClickListener{
    moodMeterUtils moodUtils = new moodMeterUtils();

    public RecordMoodMainFragment() {
        super(R.layout.record_mood_main);
    }

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

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                Button recordButton = view.findViewById(R.id.record_button);
                recordButton.setText(moodUtils.floatToRecordButtonConverter(value));
            }
        });

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
            intent.putExtra("moodValue", moodVal);
            startActivity(intent);
        }
    }
}