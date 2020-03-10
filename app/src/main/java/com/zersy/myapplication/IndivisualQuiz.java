package com.zersy.myapplication;

import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import java.net.PortUnreachableException;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IndivisualQuiz {
    private String QUESTION;
    private String OPTION_1;
    private String OPTION_2;
    private String OPTION_3;
    private String OPTION_4;
    private String CORRECT;

    public IndivisualQuiz(){
        //empty
    }

    public IndivisualQuiz(String QUESTION, String OPTION_1,String OPTION_2,String OPTION_3,String OPTION_4, String CORRECT){
        this.QUESTION = QUESTION;
        this.OPTION_1=OPTION_1;
        this.OPTION_2 = OPTION_2;
        this.OPTION_3 = OPTION_3;
        this.OPTION_4 = OPTION_4;
        this.CORRECT = CORRECT;

    }

    public String getQUESTION() {
        return QUESTION;
    }

    public String getOPTION_1() {
        return OPTION_1;
    }

    public String getOPTION_2() {
        return OPTION_2;
    }

    public String getOPTION_3() {
        return OPTION_3;
    }

    public String getOPTION_4() {
        return OPTION_4;
    }

    public String getCORRECT() {
        return CORRECT;
    }
}
