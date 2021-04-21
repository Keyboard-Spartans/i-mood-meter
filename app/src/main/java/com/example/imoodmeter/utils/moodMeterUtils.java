package com.example.imoodmeter.utils;

import android.util.Log;

public class moodMeterUtils {
    private static final String TAG = "moodUtils";
    public moodMeterUtils() {return;};

    public String floatToRecapConverter(float value) {
        String moodStr = floatToMoodConverter(value).toLowerCase();
        return String.format("You're feeling %s today", moodStr);
    }

    public String floatToEditConverter(float value) {
        Log.i(TAG, "value: " + value);
        String moodStr = floatToMoodConverter(value).toLowerCase();
        return String.format("I feel %s because...", moodStr);
    }

    public String floatToQuestionConverter(float value) {
        Log.i(TAG, "value: " + value);
        String moodStr = floatToMoodConverter(value).toLowerCase();
        return String.format("Why do you feel %s today?", moodStr);
    }

    public String floatToMoodConverter(float value) {
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
