package com.example.paimon.stillnotdone.LaboratoryWork1;

import android.app.Activity;
import android.icu.text.UnicodeSetSpanner;
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


public class ThirdItemMenuActivity extends Activity {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_item_menu_activity);

        radioButtonTask = (RadioButton) findViewById(R.id.radioButtonTask);
        radioButtonBlockSchema = (RadioButton) findViewById(R.id.radioButtonBlockSchema);
        image = (ImageView) findViewById(R.id.firstImage);
        editTextP = (EditText) findViewById(R.id.menuFirstItemEditTextD);
        editTextN = (EditText) findViewById(R.id.menuFirstItemEditTextH);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        answer = (TextView) findViewById(R.id.answerThird);
        linearLayoutOfEditText = findViewById(R.id.setOfEditText);
        AnswerButton = findViewById(R.id.answerButton);

        image.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         if (image.getDrawable().getConstantState().equals(image.getContext().getDrawable(R.drawable.block_schema3).getConstantState())) {
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
    }
    public void calculateOnClick(View view){
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
            Toast.makeText(getApplicationContext(), "Division by zero!", Toast.LENGTH_SHORT).show();
        }
    }

}
