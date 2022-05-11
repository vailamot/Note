package com.doanhtq.note.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.doanhtq.note.Note;
import com.doanhtq.note.NoteAdapter;
import com.doanhtq.note.NoteOpenHelper;
import com.doanhtq.note.R;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NoteAdapter mNoteAdapter;
    NoteOpenHelper mNoteOpenHelper;

    private final LinkedList<Note> mNoteList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteOpenHelper = new NoteOpenHelper(this);
        mNoteList.addAll(mNoteOpenHelper.getAllNotes());

//        for (int i = 1; i < 20; i++) {
//            mNoteList.addLast(new Note(i,"Title" + i, "Description" + i));
//            mNoteList.addLast(new Note(i, "Title " + i, "Description " + i));
//        }

        mRecyclerView = findViewById(R.id.list_note);
        mNoteAdapter = new NoteAdapter(this, mNoteList);
        mRecyclerView.setAdapter(mNoteAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void startEditNoteActivity(View view) {
        Intent intent = new Intent(this,EditNoteActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            mNoteList.clear();
            mNoteList.addAll(mNoteOpenHelper.getAllNotes());
            mNoteAdapter.notifyDataSetChanged();
        }
    }
}