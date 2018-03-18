package com.example.paimon.stillnotdone.LaboratoryWork3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.paimon.stillnotdone.R;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Arrays;

public class FragmentLaboratoryWorkThird extends Fragment {

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

    Button func;
    Button mistake;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laboratory_work3, container, false);


        func = view.findViewById(R.id.func);
        func.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentGraphFunctions();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.kekkek, fragment).commit();
            }
        });
        mistake = view.findViewById(R.id.mistake);
        mistake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentGraphMistake();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.kekkek, fragment).commit();
            }
        });


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

        for(int i = 0; i < n*k; i++) {delta[i] = Math.abs(y_func[i] - getInterpolatedValue(x_func[i]));}


        return view;
    }

    public DataPoint[] data(double[] x_func, double[] y_func){
        int n=x_func.length;     //to find out the no. of data-points
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(x_func[i], y_func[i]);
            values[i] = v;
        }
        return values;
    }

    public double function(double x){return Math.pow(e,x) - 2*Math.sin(x);}

    public static double getInterpolatedValue(double xx) {
        buffer = Arrays.copyOf(y_func_grid, n);

        for(int i = 0; i < n - 1; i++)
            for(int j = i + 1; j < n; j++)
                buffer[j] = ((xx - x_poli[i])*buffer[j] - (xx - x_poli[j])*buffer[i])/(x_poli[j] - x_poli[i]);

        return buffer[n - 1];
    }

}
