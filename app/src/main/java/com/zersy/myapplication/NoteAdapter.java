package com.zersy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends FirestoreRecyclerAdapter<note, NoteAdapter.NoteHolder> {
    private Context context;


    public NoteAdapter(@NonNull FirestoreRecyclerOptions<note> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder noteHolder, int i, @NonNull final note note) {
        noteHolder.title.setText(note.getTitle());
        noteHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context=view.getContext();
                MainActivity.title= note.getTitle();
                MainActivity.KEY = note.getKEY();
                view.getContext().startActivity(new Intent(context, Quiz.class));

            }
        });
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView title;
        CardView card;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            card = itemView.findViewById(R.id.card);
        }
    }


}
