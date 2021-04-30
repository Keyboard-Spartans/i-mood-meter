package com.example.imoodmeter;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class CalendarFragment extends Fragment {

    CustomCalendar customCalendar;
    moodMeterUtils moodUtils = new moodMeterUtils();
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

        String[] arr = {"2021-04-10", "2021-04-11", "2021-04-15", "2021-04-16", "2021-04-25",
                "2021-04-1", "2021-04-5", "2021-04-13", "2021-04-9", "2021-04-27",
                "2021-03-10", "2021-03-11", "2021-03-15", "2021-03-16", "2021-03-25",
                "2021-03-1", "2021-03-5", "2021-03-13", "2021-03-9", "2021-03-27"};
        for (int i = 0; i < arr.length; i++) {
//            int eventCount = 3;
            int eventCount = new Random().nextInt(2) + 1;
            customCalendar.addAnEvent(arr[i], eventCount, getEventDataList(eventCount));
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();

        customCalendar.addAnEvent(dateFormat.format(today), 3, getMoodEvent(3));

        return view;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<EventData> getEventDataList(int count) {
        ArrayList<EventData> eventDataList = new ArrayList();

        String[] descriptionList = {"I aced my test", "I could not find my error on stack overflow", "My PS5 came in!!!!!", "I got the job today!",
                "This test is giving my anxiety", "I wonder when they will text me back!", "That is very interesting!"};
        for (int i = 0; i < count; i++) {
            EventData dateData = new EventData();
            ArrayList<dataAboutDate> dataAboutDates = new ArrayList();

            dateData.setSection(" ");
            dataAboutDate dataAboutDate = new dataAboutDate();

//            int index = new Random().nextInt(CalendarUtils.getEVENTS().length);

            dataAboutDate.setTitle(moodUtils.floatToMoodConverter(new Random().nextInt(5)));
            dataAboutDate.setSubject(descriptionList[new Random().nextInt(descriptionList.length)]);
            dataAboutDates.add(dataAboutDate);

            dateData.setData(dataAboutDates);
            eventDataList.add(dateData);
        }

        return eventDataList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<EventData> getMoodEvent(int count) {
        ArrayList<EventData> eventDataList = new ArrayList();
        List<MoodModel> moods = MoodController.getMoods();

        for (int i = 0; i < count ; i++) {
            EventData dateData = new EventData();
            ArrayList<dataAboutDate> dataAboutDates = new ArrayList();

            dateData.setSection(" ");
            dataAboutDate dataAboutDate = new dataAboutDate();

//            int index = new Random().nextInt(CalendarUtils.getEVENTS().length);

            dataAboutDate.setTitle(moodUtils.floatToMoodConverter(moods.get(moods.size()-i - 1).getMoodValue()));
            dataAboutDate.setSubject(moods.get(moods.size() - i -1).getMoodDescription());
            dataAboutDates.add(dataAboutDate);

            dateData.setData(dataAboutDates);
            eventDataList.add(dateData);
        }

        return eventDataList;
    }

}