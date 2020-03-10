package com.zersy.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizAapter extends FirestoreRecyclerAdapter<IndivisualQuiz, QuizAapter.quizHolder> {

    public QuizAapter(@NonNull FirestoreRecyclerOptions<IndivisualQuiz> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final quizHolder quizHolder, int i, @NonNull final IndivisualQuiz indivisualQuiz) {
        quizHolder.QuestionTextView.setText(indivisualQuiz.getQUESTION());
        quizHolder.button_1.setText(indivisualQuiz.getOPTION_1());
        quizHolder.button_2.setText(indivisualQuiz.getOPTION_2());
        quizHolder.button_3.setText(indivisualQuiz.getOPTION_3());
        quizHolder.button_4.setText(indivisualQuiz.getOPTION_4());
        quizHolder.correct = indivisualQuiz.getCORRECT();
        quizHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.button_1) {
                    if (indivisualQuiz.getOPTION_1().equals(indivisualQuiz.getCORRECT())) {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 1);
                    } else {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 0);
                    }
                } else if (i == R.id.button_2) {
                    if (indivisualQuiz.getOPTION_2().equals(indivisualQuiz.getCORRECT())) {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 1);
                    } else {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 0);
                    }
                } else if (i == R.id.button_3) {
                    if (indivisualQuiz.getOPTION_3().equals(indivisualQuiz.getCORRECT())) {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 1);
                    } else {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 0);
                    }
                } else if (i == R.id.button_4) {
                    if (indivisualQuiz.getOPTION_4().equals(indivisualQuiz.getCORRECT())) {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 1);
                    } else {
                        Quiz.score.put(indivisualQuiz.getQUESTION(), 0);
                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public quizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        return new quizHolder(v);
    }

    class quizHolder extends RecyclerView.ViewHolder {
        TextView QuestionTextView;
        RadioGroup radioGroup;
        RadioButton button_1, button_2, button_3, button_4;
        String correct;


        public quizHolder(@NonNull View itemView) {
            super(itemView);
            QuestionTextView = itemView.findViewById(R.id.question);
            button_2 = itemView.findViewById(R.id.button_2);
            button_3 = itemView.findViewById(R.id.button_3);
            button_4 = itemView.findViewById(R.id.button_4);
            button_1 = itemView.findViewById(R.id.button_1);
            radioGroup = itemView.findViewById(R.id.radio_group);

        }
    }
}
