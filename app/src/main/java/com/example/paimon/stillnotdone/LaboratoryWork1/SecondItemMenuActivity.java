package com.example.paimon.stillnotdone.LaboratoryWork1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

public class SecondItemMenuActivity extends Activity {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_item_menu_activity);

        radioButtonTask = (RadioButton) findViewById(R.id.radioButtonTask);
        radioButtonBlockSchema = (RadioButton) findViewById(R.id.radioButtonBlockSchema);
        image = (ImageView) findViewById(R.id.secondImageView);
        editTextD = (EditText) findViewById(R.id.menuFirstItemEditTextD);
        editTextH = (EditText) findViewById(R.id.menuFirstItemEditTextH);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        answer = (TextView) findViewById(R.id.answerSecond);
        AnswerButton = findViewById(R.id.answerButton);
        linearLayoutOfEditText = findViewById(R.id.setOfEditText);

        image.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         if (image.getDrawable().getConstantState().equals(image.getContext().getDrawable(R.drawable.block_schema2).getConstantState())) {
                                             if (radioGroup.getVisibility() == View.VISIBLE) {
                                                 Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                                 image.startAnimation(animation);
                                                 radioGroup.setVisibility(View.INVISIBLE);
                                                 linearLayoutOfEditText.setVisibility(View.INVISIBLE);
                                                 AnswerButton.setVisibility(View.INVISIBLE);

                                             } else {
                                                 Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_to_small);
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
    }

    public void calculateOnClick(View view){
        if (editTextD.getText().toString().isEmpty() ||
                editTextH.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill al fields!", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), "Division by zero!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}