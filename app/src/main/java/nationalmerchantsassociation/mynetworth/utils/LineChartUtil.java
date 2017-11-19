package nationalmerchantsassociation.mynetworth.utils;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import nationalmerchantsassociation.mynetworth.R;

/**
 * Created by jbrannen on 11/17/17.
 */

public class LineChartUtil {

    private LineChart lineChart;
    private Context context;

    public void initLineChart(LineChart lineChart, Context context) {
        this.context = context;
        this.lineChart = lineChart;
        LineData data = getData(30, 100);
        // add some transparency to the color with "& 0x90FFFFFF"
        setupLineChart(lineChart, data, context.getResources().getColor(R.color.colorPrimary), context);
    }

    public void udateDataset(List<Integer> values){
        lineChart.setData(getData(values));
        lineChart.animateX(500);
    }

    private void setupLineChart(LineChart chart, LineData data, int color, Context context) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleColorHole(color);
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setPinchZoom(false);
        chart.setBackgroundColor(Color.TRANSPARENT);
        chart.setViewPortOffsets(PixelDpConversionUtil.pxFromDp(context.getApplicationContext(), 12f), 0,
                PixelDpConversionUtil.pxFromDp(context.getApplicationContext(), 12f), 0);
        chart.setData(data);
        Legend l = chart.getLegend();
        l.setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setSpaceTop(40);
        chart.getAxisLeft().setSpaceBottom(40);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        chart.animateX(500);
    }

    private LineData getData(List<Integer> values) {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < values.size(); i++) {
            yVals.add(new Entry(i, values.get(i)));
        }
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        set1.setLineWidth(2f);
        set1.setCircleRadius(1f);
        set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set1.setDrawCircleHole(false);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);
        set1.setDrawHorizontalHighlightIndicator(false);
        set1.setDrawVerticalHighlightIndicator(true);
        set1.setHighLightColor(context.getColor(R.color.colorAccent));
        set1.setHighlightLineWidth(3f);
        LineData data = new LineData(set1);
        return data;
    }

    private LineData getData(int count, float range) {

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");

        set1.setLineWidth(4f);
        set1.setCircleRadius(2f);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setDrawCircleHole(false);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);

        // create a data object with the datasets
        LineData data = new LineData(set1);

        return data;
    }
}
