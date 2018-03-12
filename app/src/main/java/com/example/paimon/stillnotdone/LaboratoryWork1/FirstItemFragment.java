package com.example.paimon.stillnotdone.LaboratoryWork1;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class FirstItemFragment extends Fragment {
    ImageView image;
    RadioGroup radioGroup;
    RadioButton radioButtonTask;
    RadioButton radioButtonBlockSchema;
    EditText editTextA;
    EditText editTextB;
    EditText editTextC;
    EditText editTextS;
    TextView answer;
    Button AnswerButton;
    LinearLayout linearLayoutOfEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_item_layout, container, false);


        radioButtonTask = (RadioButton) view.findViewById(R.id.radioButtonTask);
        radioButtonBlockSchema = (RadioButton) view.findViewById(R.id.radioButtonBlockSchema);
        linearLayoutOfEditText = view.findViewById(R.id.setOfEditText);
        AnswerButton = view.findViewById(R.id.AnswerButton);
        image = (ImageView) view.findViewById(R.id.firstImageView);




        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getDrawable().getConstantState().equals(image.getContext().getDrawable(R.drawable.block_schema1).getConstantState())){
                    if (radioGroup.getVisibility() == View.VISIBLE) {
                        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom);
                        image.startAnimation(animation);
                        radioGroup.setVisibility(View.INVISIBLE);
                        linearLayoutOfEditText.setVisibility(View.INVISIBLE);
                        AnswerButton.setVisibility(View.INVISIBLE);

                    }
                    else {
                        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom_to_small);
                        image.startAnimation(animation);
                        radioGroup.setVisibility(View.VISIBLE);
                        linearLayoutOfEditText.setVisibility(View.VISIBLE);
                        AnswerButton.setVisibility(View.VISIBLE);
                    }
                }

            }
        });



        AnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextA.getText().toString().isEmpty() ||
                        editTextB.getText().toString().isEmpty() ||
                        editTextC.getText().toString().isEmpty() ||
                        editTextS.getText().toString().isEmpty() ){
                    Toast.makeText(getActivity().getApplicationContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        int a = Integer.parseInt(editTextA.getText().toString());
                        int b = Integer.parseInt(editTextB.getText().toString());
                        int c = Integer.parseInt(editTextC.getText().toString());
                        int s = Integer.parseInt(editTextS.getText().toString());
                        double result = Math.pow(((a+b)/(a-b))+((c+s)/(c-s)), 2);
                        answer.setText("your result is: "+result);
                        answer.setVisibility(View.VISIBLE);
                    }
                    catch (ArithmeticException e){
                        Toast.makeText(getActivity().getApplicationContext(), "Division by zero!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });





        editTextA = (EditText) view.findViewById(R.id.menuFirstItemEditTextA);
        editTextB = (EditText) view.findViewById(R.id.menuFirstItemEditTextB);
        editTextC = (EditText) view.findViewById(R.id.menuFirstItemEditTextC);
        editTextS = (EditText) view.findViewById(R.id.menuFirstItemEditTextS);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        answer = (TextView) view.findViewById(R.id.answerFirst);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButtonTask :
                        image.setImageResource(R.drawable.task1);
                        break;
                    case R.id.radioButtonBlockSchema :
                        image.setImageResource(R.drawable.block_schema1);
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }




}
