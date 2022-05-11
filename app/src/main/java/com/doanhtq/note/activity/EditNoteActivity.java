package com.doanhtq.note.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.doanhtq.note.Note;
import com.doanhtq.note.NoteOpenHelper;
import com.doanhtq.note.R;

public class EditNoteActivity extends AppCompatActivity {
    NoteOpenHelper mNoteOpenHelper;
    Note mNote;
    EditText mNoteTitle, mNoteDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        mNoteOpenHelper = new NoteOpenHelper(this);
        mNote = new Note();
        mNoteTitle = findViewById(R.id.et_note_title);
        mNoteDescription = findViewById(R.id.et_note_description);
    }

    public void addNote(View view) {
        mNote.setNoteTitle(mNoteTitle.getText().toString());
        mNote.setNoteDescription((mNoteDescription.getText().toString()));
        mNoteOpenHelper.insertNote(mNote);
        finish();
    }
}