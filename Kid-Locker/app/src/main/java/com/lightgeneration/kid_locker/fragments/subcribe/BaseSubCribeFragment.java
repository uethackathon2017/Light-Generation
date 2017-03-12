package com.lightgeneration.kid_locker.fragments.subcribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.custom.MyMarkerView;
import com.lightgeneration.kid_locker.fragments.BaseFragment;
import com.lightgeneration.kid_locker.utils.LockKidApplication;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ngoc Sang on 3/12/2017.
 */

public class BaseSubCribeFragment extends BaseFragment implements OnChartValueSelectedListener{
    protected TextView tvTitle;
    protected LineChart chart;
    protected String title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       contentView=inflater.inflate(R.layout.base_subribe_fragment,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        tvTitle=(TextView)contentView.findViewById(R.id.tv_title_chart);
        chart=(LineChart)contentView.findViewById(R.id.chart);
    }
    public void setTitle(String title)
    {
       this.title=title;

    }

    @Override
    protected void init() {
        super.init();
        tvTitle.setText(title);
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);

        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);


        chart.setPinchZoom(true);

        MyMarkerView mv = new MyMarkerView(LockKidApplication.getAppContext(), R.layout.custom_marker_view);
        mv.setChartView(chart);
        chart.setMarker(mv);

        XAxis xl = chart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        xl.setAxisMinimum(0f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setInverted(true);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        setData(25,50);
        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        chart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
    private void setData(int count, float range)
    {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float xVal = (float) (Math.random() * range);
            float yVal = (float) (Math.random() * range);
            entries.add(new Entry(xVal, yVal));
        }

        Collections.sort(entries, new EntryXComparator());

        LineDataSet set1 = new LineDataSet(entries, "DataSet 1");

        set1.setLineWidth(1.5f);
        set1.setCircleRadius(4f);
        LineData data = new LineData(set1);

        chart.setData(data);
    }
}
