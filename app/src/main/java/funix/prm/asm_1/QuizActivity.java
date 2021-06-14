package funix.prm.asm_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class QuizActivity extends Activity {
    RadioGroup q1_answer;
    EditText q3_answer;
    EditText q4_answer;
    RadioGroup q5_answer;
    EditText q6_answer;
    EditText q8_answer;
    RadioGroup q9_answer;
    EditText q10_answer;
    CountDownTimer timer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String userName = getIntent().getStringExtra("USER_NAME");
        TextView displayUser = findViewById(R.id.userName);
        displayUser.setText(userName);

        TextView countDown = findViewById(R.id.countdown);
        timer = new CountDownTimer(300000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText(millisUntilFinished/60000 + ":" + millisUntilFinished % 60000 / 1000);
            }

            @Override
            public void onFinish() {
                countDown.setText("Time over!");
                //Tự động chấm điểm
                submitAnswer();
            }
        }.start();

        q1_answer = findViewById(R.id.q1_answer);
        q3_answer = findViewById(R.id.q3_answer);
        q4_answer = findViewById(R.id.q4_answer);
        q5_answer = findViewById(R.id.q5_answer);
        q6_answer = findViewById(R.id.q6_answer);
        q8_answer = findViewById(R.id.q8_answer);
        q9_answer = findViewById(R.id.q9_answer);
        q10_answer = findViewById(R.id.q10_answer);

        Button submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                submitAnswer();
            }
        });

        Button restart = findViewById(R.id.restart_button);
        restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                restart();
            }
        });
    }

    public void submitAnswer(){
        //Correct answer
        int score = 0;
        //1
        if (q1_answer.getCheckedRadioButtonId() == R.id.q1_c){
            score++;
        }
        //2
        CheckBox q2Answer1 = findViewById(R.id.q2_a);
        CheckBox q2Answer2 = findViewById(R.id.q2_c);
        if (q2Answer1.isChecked() && q2Answer2.isChecked()){
            score++;
        }
        //3
        if (q3_answer.getText().toString().equalsIgnoreCase("Vulcanizing")){
            score++;
        }
        //4
        if (q4_answer.getText().toString().equalsIgnoreCase("Gravity")){
            score++;
        }
        //5
        if (q5_answer.getCheckedRadioButtonId() == R.id.q5_b){
            score++;
        }
        //6
        String q6Input = q6_answer.getText().toString();
        if (q6Input.equalsIgnoreCase("Cloud") ||
        q6Input.equalsIgnoreCase("Clouds")){
            score++;
        }
        //7
        CheckBox q7Answer1 = findViewById(R.id.q7_c);
        CheckBox q7Answer2 = findViewById(R.id.q7_d);
        if (q7Answer1.isChecked() && q7Answer2.isChecked()){
            score++;
        }
        //8
        if (q8_answer.getText().toString().equalsIgnoreCase("Wrist")){
            score++;
        }
        //9
        if (q9_answer.getCheckedRadioButtonId() == R.id.q9_b){
            score++;
        }
        //10
        if (q10_answer.getText().toString().equalsIgnoreCase("Smelting")){
            score++;
        }

        Toast.makeText(getApplicationContext(), "Your score is: " + score, Toast.LENGTH_LONG).show();

        timer.cancel();

    }

    public void restart() {
        finish();
        startActivity(this.getIntent());
    }
}
