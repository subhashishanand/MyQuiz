package com.zersy.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Addquiz extends AppCompatActivity {

    Button done;
    TextView addTextView;
    EditText quizName, option_1, option_2, option_3, option_4;
    //RadioButton correct_1,correct_2,correct_3,correct_4;
    MultiAutoCompleteTextView question;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    String randomKey;

    Boolean donePressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquiz);
        done = findViewById(R.id.button_done);
        addTextView = findViewById(R.id.add_new_question);
        quizName = findViewById(R.id.quiz_name);
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radio_group);


        option_1 = findViewById(R.id.option_1);
        option_2 = findViewById(R.id.option_2);
        option_3 = findViewById(R.id.option_3);
        option_4 = findViewById(R.id.option_4);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Quiz");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        randomKey = UUID.randomUUID().toString().substring(1,5);

        addTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donePressed= false;
                save();
                Toast.makeText(Addquiz.this, "Question added please fill new one", Toast.LENGTH_SHORT).show();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donePressed = true;
                save();
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void checkButton(View view) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
        Toast.makeText(Addquiz.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

    }

    public void save() {
        if (quizName.getText() == null || question.getText() == null || option_1.getText() == null || option_2.getText() == null || option_3 == null || option_4 == null) {
            Toast.makeText(Addquiz.this, "Fill all arguments", Toast.LENGTH_SHORT).show();
        } else {
            String quiz = quizName.getText().toString();
            String quest = question.getText().toString();
            String option1 = option_1.getText().toString();
            String option2 = option_2.getText().toString();
            String option3 = option_3.getText().toString();
            String option4 = option_4.getText().toString();
            quizName.setEnabled(false);
            int radioid = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioid);
            String correctoption = radioButton.getText().toString();

            String[] option = {option1, option2, option3, option4};

            String[] splita = correctoption.split(" ");

            String correct = option[Integer.parseInt(splita[1]) - 1];

            Map<String, Object> note = new HashMap<>();
            note.put("title", quiz);
            note.put("KEY", randomKey);
            note.put("QUESTION", quest);
            note.put("OPTION_1", option1);
            note.put("OPTION_2", option2);
            note.put("OPTION_3", option3);
            note.put("OPTION_4", option4);
            note.put("CORRECT", correct);
            if(donePressed){
                note.put("PRIORITY", "1");
            }

            db.collection("Quizes").document().set(note, SetOptions.merge());
            question.setText(null);
            option_1.setText(null);
            option_2.setText(null);
            option_3.setText(null);
            option_4.setText(null);
        }
    }

}
