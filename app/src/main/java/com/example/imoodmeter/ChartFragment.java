package com.example.imoodmeter;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_chart, container, false);
        lineChart = view.findViewById(R.id.lineChart);
        entryList.add(new Entry(10,20));
        entryList.add(new Entry(5,10));
        entryList.add(new Entry(7,31));
        entryList.add(new Entry(3,14));
        LineDataSet lineDataSet = new LineDataSet(entryList,"Mood");
        lineDataSet.setColors(getResources().getColor(R.color.theme_green));
        lineDataSet.setFillAlpha(110);
        lineDataSet.setLineWidth((float) 400);
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setVisibleXRangeMaximum(10);
        lineChart.invalidate();
//        return inflater.inflate(R.layout.fragment_chart, container, false);
        return view;
    }

}