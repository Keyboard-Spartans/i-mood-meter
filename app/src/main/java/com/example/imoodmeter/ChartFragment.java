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
import com.github.mikephil.charting.components.Legend;
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

    public ChartFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_chart, container, false);

        lineChart = view.findViewById(R.id.lineChart);

        initializeChart(lineChart);
        List<Entry> entryList = getEntryList();

        LineDataSet lineDataSet = new LineDataSet(entryList,"0: Angry 1:Sad 2:Okay 3:Content 4:Happy 5:Excited");
        lineDataSet.setFillAlpha(110);
        lineDataSet.setColors(getResources().getColor(R.color.theme_green));
        lineDataSet.setLineWidth((float) 800);
        lineDataSet.setValueTextSize(14f);
        lineChart.setBackgroundColor(getResources().getColor(R.color.light_grey));


        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setDrawGridBackground(false);
        Description labelDesc = new Description();

        labelDesc.setTextSize(16f);
        labelDesc.setText("April");
        lineChart.setDescription(labelDesc);

        lineChart.setDrawBorders(true);
        lineChart.setBorderWidth(2);
        lineChart.setBorderColor(getResources().getColor(R.color.light_grey));


        lineChart.invalidate();
        return view;
    }

    public void initializeChart(LineChart lineChart) {
        XAxis xAxis =  lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);

        YAxis yAxis =  lineChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setGranularity(1f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(5f);
        yAxis.setTextSize(10f);


        YAxis yAxisR =  lineChart.getAxisRight();
        yAxisR.setEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Entry> getEntryList() {
        List<Entry> entryList = new ArrayList<>();

        List<MoodModel> moods = MoodController.getMoods();
        float prevDay, curDay;
        float cumVal = 0f;
        float cumDays = 1f;

        for(int i = 1; i < moods.size(); i++) {
            prevDay = moods.get(i-1).getMoodTimeRecorded().getDayOfMonth();
            curDay = moods.get(i).getMoodTimeRecorded().getDayOfMonth();

            cumVal += moods.get(i-1).getMoodValue();

            // if not last element
            if ( i != moods.size()-1){
                if (curDay == prevDay) {
                    cumDays ++;

                } else {
                    entryList.add(new Entry(prevDay,cumVal/cumDays));

                    cumVal = 0f;
                    cumDays = 1f;
                }
            }
            // if dealing with last element
            else{
                if (curDay == prevDay) {
                    cumDays ++;
                    cumVal += moods.get(i).getMoodValue();
                    entryList.add(new Entry(prevDay,cumVal/cumDays));

                } else {
                    entryList.add(new Entry(prevDay,cumVal/cumDays));
                    entryList.add(new Entry(curDay, moods.get(i).getMoodValue()));
                    cumVal = 0f;
                    cumDays = 1f;
                }
            }
        }

        return entryList;
    }
}
