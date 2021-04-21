package com.example.imoodmeter.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.imoodmeter.model.MemoryModel;
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

    //TODO: find a way to remove @RequiresApi
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void initialize() {
        if (singletonController != null) {
            return;
        }

        singletonController = new MoodController();

        // some dummy data
        LocalDateTime mood1DateTime = singletonController.LocalDateTimeConverter("2021-04-01 11:30");
        singletonController.moods.add(new MoodModel(25, "I failed my exam", mood1DateTime));

        LocalDateTime mood2DateTime = singletonController.LocalDateTimeConverter("2021-04-02 11:30");
        singletonController.moods.add(new MoodModel(50, "I talked to an old friend", mood2DateTime));

        LocalDateTime mood3DateTime = singletonController.LocalDateTimeConverter("2021-04-03 18:30");
        singletonController.moods.add(new MoodModel(25, "I procrastinated the whole day", mood3DateTime));

        LocalDateTime mood4DateTime = singletonController.LocalDateTimeConverter("2021-04-04 11:30");
        singletonController.moods.add(new MoodModel(100, "I got accepted for a new job", mood4DateTime));


    }

}
