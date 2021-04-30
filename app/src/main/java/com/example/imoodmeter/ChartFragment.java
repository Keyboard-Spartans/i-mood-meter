package com.example.imoodmeter;

import android.graphics.Color;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ChartFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ChartFragment extends Fragment {
    LineChart lineChart;
    LineData lineData;
    List<Entry> entryList = new ArrayList<>();

    // TOD: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TOD: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public ChartFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ChartFragment.
//     */
    // TOD: Rename and change types and number of parameters
//    public static ChartFragment newInstance(String param1, String param2) {
//        ChartFragment fragment = new ChartFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_chart, container, false);

        lineChart = (LineChart) view.findViewById(R.id.lineChart);
        initializeChart(lineChart);

        List<MoodModel> moods = MoodController.getMoods();

        for(MoodModel mood: moods) {
            LocalDateTime moodTime = mood.getMoodTimeRecorded();
            BigDecimal xAxisVal = moodTime.getDayOfMonth() + (moodTime.getHour() *3600 + moodTime.getMinute()*60 + moodTime.getSecond())/86400;
            Log.i("xAxisVal:", String.toString(xAxisVal));

            entryList.add(new Entry(xAxisVal,mood.getMoodValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(entryList,"Mood");
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setFillAlpha(110);

        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setDrawGridBackground(false);
        Description labelDesc = new Description();
        labelDesc.setText("Date");
        lineChart.setDescription(labelDesc);

        lineChart.invalidate();
        return view;
    }

    public void initializeChart(LineChart lineChart) {
        XAxis xAxis =  lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxis =  lineChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setGranularity(1f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(5f);

        YAxis yAxisR =  lineChart.getAxisRight();
        yAxisR.setEnabled(false);
    }
}