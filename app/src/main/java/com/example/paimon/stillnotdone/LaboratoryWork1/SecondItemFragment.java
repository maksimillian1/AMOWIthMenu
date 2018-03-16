package com.example.paimon.stillnotdone.LaboratoryWork1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paimon.stillnotdone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondItemFragment extends Fragment {

    ImageView image;
    RadioGroup radioGroup;
    RadioButton radioButtonTask;
    RadioButton radioButtonBlockSchema;
    EditText editTextD;
    EditText editTextH;
    TextView answer;
    Button AnswerButton;
    LinearLayout linearLayoutOfEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_item_layout, container, false);

        radioButtonTask = view.findViewById(R.id.radioButtonTask);
        radioButtonBlockSchema = view.findViewById(R.id.radioButtonBlockSchema);
        image = view.findViewById(R.id.secondImageView);
        editTextD = view.findViewById(R.id.menuFirstItemEditTextD);
        editTextH = view.findViewById(R.id.menuFirstItemEditTextH);
        radioGroup = view.findViewById(R.id.radioGroup);
        answer = view.findViewById(R.id.answerSecond);
        AnswerButton = view.findViewById(R.id.answerButton);
        linearLayoutOfEditText = view.findViewById(R.id.setOfEditText);


       // Image scaling animation.
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image.getDrawable().getConstantState().equals(image.getContext().getDrawable(R.drawable.block_schema2).getConstantState())) {
                    if (radioGroup.getVisibility() == View.VISIBLE) {
                        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom);
                        image.startAnimation(animation);
                        radioGroup.setVisibility(View.INVISIBLE);
                        linearLayoutOfEditText.setVisibility(View.INVISIBLE);
                        AnswerButton.setVisibility(View.INVISIBLE);

                    } else {
                        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom_to_small);
                        image.startAnimation(animation);
                        radioGroup.setVisibility(View.VISIBLE);
                        linearLayoutOfEditText.setVisibility(View.VISIBLE);
                        AnswerButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


        //  Image switch by radio group.
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButtonTask :
                        image.setImageResource(R.drawable.task2);
                        break;
                    case R.id.radioButtonBlockSchema :
                        image.setImageResource(R.drawable.block_schema2);
                        break;
                    default:
                        break;
                }
            }
        });


        AnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextD.getText().toString().isEmpty() ||
                        editTextH.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(), "Fill al fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        answer.setVisibility(View.VISIBLE);
                        int d = Integer.parseInt(editTextD.getText().toString());
                        int h = Integer.parseInt(editTextH.getText().toString());
                        if (Integer.parseInt(editTextD.getText().toString()) > 0) {
                            double result = 3.14 * d * h + Math.sqrt(3.14 + 23 * d);
                            answer.setText("" + result);

                        } else {
                            double result = Math.sqrt(3.14 * Math.abs(d)) / 129 * h;
                            answer.setText("" + result);
                        }
                    }catch (ArithmeticException e){
                        Toast.makeText(getActivity().getApplicationContext(), "Division by zero!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return view;
    }

}
