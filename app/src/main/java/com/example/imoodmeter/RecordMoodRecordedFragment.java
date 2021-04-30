package com.example.imoodmeter;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.imoodmeter.controller.MoodController;
import com.example.imoodmeter.utils.moodMeterUtils;

public class RecordMoodRecordedFragment extends Fragment implements View.OnClickListener{
    moodMeterUtils moodUtils = new moodMeterUtils();

    public RecordMoodRecordedFragment() {
        super(R.layout.record_mood_recorded);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button addNewMood = view.findViewById(R.id.add_another_mood_button);
        addNewMood.setOnClickListener(this);

        Button editMood = view.findViewById(R.id.edit_mood_button);
        editMood.setOnClickListener(this);

        // write appropriate mood on recap
        TextView moodRecap = view.findViewById(R.id.mood_recap);

        float moodVal = MoodController.getMoods().get(MoodController.getMoodsSize()-1).getMoodValue();
        String moodRecapStr = moodUtils.floatToRecapConverter(moodVal);
        moodRecap.setText(moodRecapStr);

        // Disclaimer page
        Button disclaimerButton = view.findViewById(R.id.disclaimerRecorded);
        disclaimerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_another_mood_button) {
            Intent intent = new Intent(this.getContext(), MainActivity.class);
            intent.putExtra("moodRecorded", false);
            startActivity(intent);
        }

        if (view.getId() == R.id.edit_mood_button) {
            Intent intent = new Intent(this.getContext(), MainActivity.class);
            intent.putExtra("moodEdit", true);
            startActivity(intent);
        }

        if (view.getId() == R.id.disclaimerRecorded) {
            Intent intent = new Intent(this.getContext(), DisclaimerActivity.class);
            startActivity(intent);
        }
    }
}