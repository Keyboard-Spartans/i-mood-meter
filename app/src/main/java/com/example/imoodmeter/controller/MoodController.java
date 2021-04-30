package com.example.imoodmeter.controller;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.imoodmeter.model.MoodModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoodController {
    private ArrayList<MoodModel> moods = new ArrayList<>();

    private static MoodController singletonController;

    private MoodController() {}

    public static int getMoodsSize() {
        return singletonController.moods.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<MoodModel> getMoods() {
        initialize();
        return Collections.unmodifiableList(singletonController.moods);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void addMood(MoodModel mood) {
        initialize();
        singletonController.moods.add(mood);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDateTime LocalDateTimeConverter(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateInput, formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void printMoods() {
        Log.i("MOODPRINT", "PRINTING MOOD");
        for (MoodModel mood: singletonController.moods) {
            Log.i("MoodEntry", mood.print());
        }
    }

    //TODO: find a way to remove @RequiresApi
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void initialize() {
        if (singletonController != null) {
            return;
        }

        singletonController = new MoodController();

        // some dummy data
        LocalDateTime mood1DateTime = singletonController.LocalDateTimeConverter("2021-04-25 11:30");
        singletonController.moods.add(new MoodModel(1, "I failed my exam", mood1DateTime));

        LocalDateTime mood2DateTime = singletonController.LocalDateTimeConverter("2021-04-26 11:30");
        singletonController.moods.add(new MoodModel(4, "I talked to an old friend", mood2DateTime));

        LocalDateTime mood3DateTime = singletonController.LocalDateTimeConverter("2021-04-27 18:30");
        singletonController.moods.add(new MoodModel(3, "I procrastinated the whole day", mood3DateTime));

        LocalDateTime mood4DateTime = singletonController.LocalDateTimeConverter("2021-04-28 11:30");
        singletonController.moods.add(new MoodModel(5, "I got accepted for a new job", mood4DateTime));


    }

}
