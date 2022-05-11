package com.doanhtq.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NoteAdapter mNoteAdapter;

    private final LinkedList<Note> mNoteList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 1; i < 20; i++) {
//            mNoteList.addLast(new Note(i,"Title" + i, "Description" + i));
            mNoteList.addLast(new Note(i, "Title " + i, "Description " + i));
        }

        mRecyclerView = findViewById(R.id.list_note);
        mNoteAdapter = new NoteAdapter(this, mNoteList);
        mRecyclerView.setAdapter(mNoteAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}