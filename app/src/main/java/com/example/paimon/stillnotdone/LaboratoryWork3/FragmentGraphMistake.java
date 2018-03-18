package com.example.paimon.stillnotdone.LaboratoryWork3;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paimon.stillnotdone.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.util.Arrays;

public class FragmentGraphMistake extends FragmentLaboratoryWorkThird {

    private static final double e= 2.718281828;
    private static final double a = 0;
    private static final double b = 2.0;
    private static final int n = 11;
    private static final int k = 20;
    private static double[] x_poli = new double[n];
    private static double[] y_poli = new double[n];
    private static double[] y_func_grid = new double[n];
    private static double[] buffer = new double[n];
    private static double[] x_func = new double[n*k];
    private static double[] y_func = new double[n*k];
    private static double[] delta = new double[n*k];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_mistake, container, false);
        GraphView graph =  view.findViewById(R.id.graph);

        double h = (b - a) / (n*k - 1);
        for(int i = 0; i < n*k; i++) {
            x_func[i] = a + h*i;
            y_func[i] = function(x_func[i]);
        }
        h = (b - a) / (n - 1);
        for(int i = 0; i < n; i++) {
            x_poli[i] = a + h * i;
            y_func_grid[i] = function(x_poli[i]);
        }

        for(int i = 0; i < n; i++) {y_poli[i] = getInterpolatedValue(x_poli[i]);}
        for(int i = 0; i < n*k; i++) {delta[i] = Math.abs(y_func[i] - getInterpolatedValue(x_func[i]))*1000000;}

        initGraph(graph, x_func, delta);
        return view;
    }


    public void initGraph(GraphView graph, double[] x_func, double[] delta){
        LineGraphSeries<DataPoint> seriesVariantFunction= new LineGraphSeries<>(data(x_func, delta));
        seriesVariantFunction.setColor(Color.BLUE);
        seriesVariantFunction.setTitle("Variant");

        graph.addSeries(seriesVariantFunction);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);



    }


}
