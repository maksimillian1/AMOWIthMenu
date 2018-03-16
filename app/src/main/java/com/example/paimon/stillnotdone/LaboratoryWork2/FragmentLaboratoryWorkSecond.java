package com.example.paimon.stillnotdone.LaboratoryWork2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paimon.stillnotdone.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLaboratoryWorkSecond extends Fragment {
    TextView listOfNum;
    EditText newNum;
    TextView forAllSpeeds;
    Button clearField;
    Button startSorting;
    Button addToField;
    private int[] list = {};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_laboratory_work_second, container, false);
        int[] lengths = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};
        long[] sortSpeeds = new long[10];
        for (int i = 0; i <lengths.length ; i++) {
            sortSpeeds[i] = speedTest(lengths[i]);
        }

        forAllSpeeds = view.findViewById(R.id.textViewForAllSpeeds);
        String speeds = "";
        for (int i = 0; i <10 ; i++) {
            speeds+=i+1+" Speed - "+sortSpeeds[i]+"\n";
        }
        forAllSpeeds.setText(speeds);
        listOfNum = view.findViewById(R.id.listOfNum);
        newNum = view.findViewById(R.id.editTextGet);
        listOfNum.setText(fromListToString(list));
        clearField = view.findViewById(R.id.clearField);
        startSorting = view.findViewById(R.id.sort);
        addToField = view.findViewById(R.id.addNumber);
        GraphView graph =  view.findViewById(R.id.graph);
        initGraph(graph, sortSpeeds);


        startSorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] arr = fromStrToList(listOfNum.getText().toString());
                int temp, j;
                for(int i = 0; i < arr.length - 1; i++){
                    if (arr[i] > arr[i + 1]) {
                        temp = arr[i + 1];
                        arr[i + 1] = arr[i];
                        j = i;
                        while (j > 0 && temp < arr[j - 1]) {
                            arr[j] = arr[j - 1];
                            j--;
                        }
                        arr[j] = temp;
                    }
                }
                listOfNum.setText(fromListToString(arr));
            }
        });


        clearField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfNum.setText("");
            }
        });


        addToField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newNum.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(), "Field is empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    int[] oldList = fromStrToList(listOfNum.getText().toString());
                    int[] newList = new int[oldList.length+1];
                    int newNumber = Integer.parseInt(newNum.getText().toString());
                    if(oldList.length == 0){
                        listOfNum.setText(""+newNumber);
                    }
                    else{
                        for (int i = 0; i <newList.length ; i++) {
                            if(i==oldList.length) {
                                newList[i] = newNumber;
                            }
                            else{
                                newList[i] = oldList[i];
                            }
                        }
                        listOfNum.setText(fromListToString(newList));
                    }
                }
                newNum.setText("");
            }
        });


        return view;
    }

    public void initGraph(GraphView graph, long[] sortSpeeds){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, sortSpeeds[0]),
                new DataPoint(2, sortSpeeds[1]),
                new DataPoint(3, sortSpeeds[2]),
                new DataPoint(4, sortSpeeds[3]),
                new DataPoint(5, sortSpeeds[4]),
                new DataPoint(6, sortSpeeds[5]),
                new DataPoint(7, sortSpeeds[6]),
                new DataPoint(8, sortSpeeds[7]),
                new DataPoint(9, sortSpeeds[8]),
                new DataPoint(10, sortSpeeds[9]),
        });

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(12.0);
        graph.setTitle("Графік складності ");
        series.setAnimated(true);
        series.setDrawDataPoints(true);
        series.setTitle("x^2");
        graph.addSeries(series);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


    }


    public static String fromListToString(int[] list){
        String sb = "";
        if(list.length == 0){
            return "";
        }
        else{
            for(int i: list) {
                if(i == list[list.length-1]) {
                    sb += i;
                }
                else{
                    sb+=i;
                    sb+=",";
                }
            }
            return sb;
        }
    }

    public static int[] fromStrToList(String str){
        if(str ==""){
            int[] list = {};
            return list;
        }
        else {
            String[] strList = str.split(",");
            int[] list = new int[strList.length];
            for (int i = 0; i < strList.length; i++) {
                list[i] = Integer.parseInt(strList[i]);
            }
            return list;
        }

    }
    public long speedTest(int count){
        long startTime = System.currentTimeMillis();
        insertSortForGraph(createRandArray(count));
        long stopTime = System.currentTimeMillis();
        long kek = stopTime - startTime;
        System.out.println(count+" - "+ kek);
        return (stopTime - startTime);
    }

    public int[] createRandArray(int count){
        Random r = new Random();
        int[] yourArray = new int[count];
        for (int i = 0; i <yourArray.length ; i++) {
            yourArray[i]= r.nextInt();
        }
        return yourArray;
    }



    public int[] insertSortForGraph(int[] notSortedArray) {
        int temp, j;
        for(int i = 0; i < notSortedArray.length - 1; i++){
            if (notSortedArray[i] > notSortedArray[i + 1]) {
                temp = notSortedArray[i + 1];
                notSortedArray[i + 1] = notSortedArray[i];
                j = i;
                while (j > 0 && temp < notSortedArray[j - 1]) {
                    notSortedArray[j] = notSortedArray[j - 1];
                    j--;
                }
                notSortedArray[j] = temp;
            }
        }
        return notSortedArray;
    }

}
