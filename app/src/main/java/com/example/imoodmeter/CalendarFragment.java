package com.example.imoodmeter;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imoodmeter.controller.MoodController;
import com.example.imoodmeter.model.MoodModel;
import com.example.imoodmeter.utils.moodMeterUtils;
import com.riontech.calendar.CustomCalendar;
import com.riontech.calendar.dao.EventData;
import com.riontech.calendar.dao.dataAboutDate;
import com.riontech.calendar.utils.CalendarUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CalendarFragment extends Fragment {

    CustomCalendar customCalendar;
    moodMeterUtils moodUtils = new moodMeterUtils();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CalendarFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        customCalendar = view.findViewById(R.id.customCalendar);

        List<MoodModel> moods = MoodController.getMoods();
        for (MoodModel mood: moods) {
            String curDate = mood.getMoodTimeRecorded().format(formatter);
            ArrayList<EventData> curData = getEventsOnDate(curDate);
            customCalendar.addAnEvent(curDate, curData.size(), curData);
        }


        return view;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<EventData> getEventsOnDate(String date) {
        ArrayList<EventData> eventDataList = new ArrayList();

        List<MoodModel> moods = MoodController.getMoods();

        for(MoodModel mood: moods) {
            String curDate = mood.getMoodTimeRecorded().format(formatter);
            if (curDate.equals(date)) {
                EventData dateData = new EventData();
                ArrayList<dataAboutDate> dataAboutDates = new ArrayList();

                dateData.setSection(" ");
                dataAboutDate dataAboutDate = new dataAboutDate();

                dataAboutDate.setTitle(moodUtils.floatToMoodConverter(mood.getMoodValue()));
                dataAboutDate.setSubject(mood.getMoodDescription());
                dataAboutDates.add(dataAboutDate);

                dateData.setData(dataAboutDates);
                eventDataList.add(dateData);
            }
        }

        return eventDataList;
    }

}