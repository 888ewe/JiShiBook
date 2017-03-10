package com.chenmo.jizhangbook;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * 作者：沉默
 * 日期：2017/3/9
 * QQ:823925783
 */

public class ChartActivity extends Activity {
    private LineChartView chart;
    //TreeMap自动排序
    private Map<String, Integer> table = new TreeMap<>();

    private LineChartData chartData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charts_view);
        chart = (LineChartView) findViewById(R.id.chart);
        List<CostBean> costBeanList = (List<CostBean>) getIntent().getSerializableExtra("cost_list");
        generateValues(costBeanList);
        generateData();
    }

    //图表数据
    private void generateData() {
        List<Line> lines = new ArrayList<>();
        List<PointValue> values = new ArrayList<>();
        int indexX = 0;
        for (Integer value : table.values()) {
            values.add(new PointValue(indexX, value));
            indexX++;
        }
        Line line = new Line(values);
        line.setColor(ChartUtils.COLORS[0]);
        line.setShape(ValueShape.CIRCLE);
        line.setPointColor(ChartUtils.COLORS[1]);
        lines.add(line);
        chartData = new LineChartData(lines);


        chartData.setAxisXBottom(new Axis().setHasLines(true).setTextColor(Color.BLACK).setName("月份").setHasTiltedLabels(true).setMaxLabelChars(4));
        chartData.setAxisYLeft(new Axis().setHasLines(true).setName("支出").setTextColor(Color.BLACK).setMaxLabelChars(5));

        chartData.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setValueSelectionEnabled(false);
        chart.setLineChartData(chartData);


    }

    private void generateValues(List<CostBean> costBeanList) {
        if (costBeanList != null) {
            for (int i = 0; i < costBeanList.size(); i++) {
                CostBean costBean = costBeanList.get(i);
                String costDate = costBean.costDate;
                int costMoney = Integer.parseInt(costBean.costMoney);
                if (!table.containsKey(costDate)) {
                    table.put(costDate, costMoney);
                } else {//同一日期进行累加
                    int originMoney = table.get(costDate);
                    table.put(costDate, originMoney + costMoney);
                }
            }
        }
    }
}
