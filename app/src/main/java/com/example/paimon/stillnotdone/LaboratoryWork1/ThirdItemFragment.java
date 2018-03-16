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
public class ThirdItemFragment extends Fragment {

    ImageView image;
    RadioGroup radioGroup;
    RadioButton radioButtonTask;
    RadioButton radioButtonBlockSchema;
    EditText editTextP;
    EditText editTextN;
    TextView answer;
    LinearLayout linearLayoutOfEditText;
    Button AnswerButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third_item, container, false);

        radioButtonTask = view.findViewById(R.id.radioButtonTask);
        radioButtonBlockSchema = view.findViewById(R.id.radioButtonBlockSchema);
        image = view.findViewById(R.id.firstImage);
        editTextP = view.findViewById(R.id.menuFirstItemEditTextD);
        editTextN = view.findViewById(R.id.menuFirstItemEditTextH);
        radioGroup = view.findViewById(R.id.radioGroup);
        answer = view.findViewById(R.id.answerThird);
        linearLayoutOfEditText = view.findViewById(R.id.setOfEditText);
        AnswerButton = view.findViewById(R.id.answerButton);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image.getDrawable().getConstantState().equals(image.getContext().getDrawable(R.drawable.block_schema3).getConstantState())) {
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButtonTask :
                        image.setImageResource(R.drawable.task3);
                        break;
                    case R.id.radioButtonBlockSchema :
                        image.setImageResource(R.drawable.block_schema3);
                        break;
                    default:
                        break;
                }
            }
        });

        AnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int N = Integer.parseInt(editTextN.getText().toString());
                int P = Integer.parseInt(editTextP.getText().toString());
                double sum = 0;
                double tmp;
                try {
                    for (int j = 0; j < P; j++) {
                        for (int i = 1; i < N; i++) {
                            if (j * i == 0) {
                                i++;
                            } else {
                                tmp = (j + i) / j * i;
                                sum = sum + tmp;
                            }
                        }
                        sum += j;

                    }
                    answer.setVisibility(View.VISIBLE);
                    answer.setText("" + sum);
                }catch (ArithmeticException e){
                    Toast.makeText(getActivity().getApplicationContext(), "Division by zero!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
