package com.zersy.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class Quiz extends AppCompatActivity {

    TextView title, countdownText, total;

    public static Map<String, Integer> score = new HashMap<>();
    ;

    private CountDownTimer countDownTimer;
    private long timeInMilliseconds = 120000;  //2 minutes

    public static int correct;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference quizRef = db.collection("Quizes");
    private QuizAapter quizAapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        title = findViewById(R.id.quiz_title);
        countdownText = findViewById(R.id.countdown_timer);
        title.setText(MainActivity.title);
        total = findViewById(R.id.total);
        correct = 0;
        setUpRecylerView();
        starttimer();

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                correct();
            }
        });

    }

    private void setUpRecylerView() {
        Query query = quizRef.whereEqualTo("KEY", MainActivity.KEY);

        FirestoreRecyclerOptions<IndivisualQuiz> options = new FirestoreRecyclerOptions.Builder<IndivisualQuiz>()
                .setQuery(query, IndivisualQuiz.class).build();

        quizAapter = new QuizAapter(options);

        RecyclerView recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(quizAapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        quizAapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        quizAapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        quizAapter.startListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        score.clear();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        quizAapter.stopListening();
    }

    public void starttimer() {
        countDownTimer = new CountDownTimer(timeInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeInMilliseconds = l;
                updatetimer();
            }

            @Override
            public void onFinish() {
                correct();

            }

            private void updatetimer() {
                int minutes = (int) timeInMilliseconds / 60000;
                int seconds = (int) (timeInMilliseconds % 60000) / 1000;

                String timeLeft;
                timeLeft = "" + minutes;
                timeLeft += ":";
                if (seconds < 10) {
                    timeLeft += 0;
                }
                timeLeft += seconds;

                countdownText.setText(timeLeft);
            }

        }.start();
    }

    public void correct() {
        int i = 0;
        for (Map.Entry<String, Integer> entry : score.entrySet()) {
            i += entry.getValue();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
        builder.setMessage("Correct: " + i).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                score.clear();
                finish();
            }
        }).setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
