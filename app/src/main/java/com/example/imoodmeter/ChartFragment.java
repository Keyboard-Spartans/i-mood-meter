package com.example.imoodmeter;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imoodmeter.controller.MoodController;
import com.example.imoodmeter.model.MoodModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

        GraphView graph = view.findViewById(R.id.graphView);
        graphInitializer(graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(getDataPoint());
        graph.addSeries(series);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private DataPoint[] getDataPoint() {
        List<MoodModel> moods = MoodController.getMoods();

        DataPoint[] dp = new DataPoint[moods.size()];

        DataPoint oldDp = new DataPoint(convertToDateViaInstant(moods.get(MoodController.getMoodsSize()-1).getMoodTimeRecorded()), moods.get(MoodController.getMoodsSize()-1).getMoodValue());

        for (int i=0; i<moods.size(); i++) {

            DataPoint newDp = new DataPoint(convertToDateViaInstant(moods.get(i).getMoodTimeRecorded()), moods.get(i).getMoodValue());

            if (newDp == oldDp) {
                Log.e("SAME DATE!", "DETECTED A SAME DATE ON THE ENTRY");
            }

            dp[i] = newDp;

            oldDp = newDp;
        }
        return dp;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void graphInitializer(GraphView graph) {
        graph.getViewport().setMinX(convertToDateViaInstant(MoodController.getMoods().get(0).getMoodTimeRecorded()).getTime());
        graph.getViewport().setMaxX(convertToDateViaInstant(MoodController.getMoods().get(MoodController.getMoodsSize()-1).getMoodTimeRecorded()).getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setMinY(0d);
        graph.getViewport().setMaxY(0d);



        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
    }

}