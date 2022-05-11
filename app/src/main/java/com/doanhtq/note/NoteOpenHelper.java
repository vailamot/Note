package com.doanhtq.note;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class NoteOpenHelper extends SQLiteOpenHelper {
    public static final String NOTE_DB_NAME = "notes";
    public static final int NOTE_DB_VERSION = 1;

    public static final String COLUMN_ID = "note_id";
    public static final String NOTE_TABLE_NAME = "notes";
    public static final String COLUMN_TITLE_NOTE = "note_title";
    public static final String COLUMN_DESCRIPTION_NOTE = "note_description";

    private String mQueryCreateTable = "CREATE TABLE " + NOTE_TABLE_NAME + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE_NOTE + " VARCHAR (255) NOT NULL, " +
            COLUMN_DESCRIPTION_NOTE + " VARCHAR (255) NOT NULL)";
    private String mQueryDeleteTable = "DROP TABLE IF EXISTS " + NOTE_DB_NAME;
    private String mQueryGetAllNotes = "SELECT " + COLUMN_ID + ", " + COLUMN_TITLE_NOTE + ", " +
            COLUMN_DESCRIPTION_NOTE + " FROM " + NOTE_DB_NAME;
    private String mQueryGetNoteByID = mQueryGetAllNotes + "WHERE " + COLUMN_ID + " = ?";
    private String mQueryInsertNote = "INSERT INTO " + NOTE_TABLE_NAME + " (" +
            COLUMN_TITLE_NOTE + "," + COLUMN_DESCRIPTION_NOTE + ")" + "VALUES (?,?)";


    public NoteOpenHelper(@Nullable Context context) {
        super(context, NOTE_DB_NAME, null, NOTE_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(mQueryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(mQueryDeleteTable);
        onCreate(db);
    }

    // Lay tat ca cac note
    public LinkedList<Note> getAllNotes(){
        LinkedList<Note> mNoteList = new LinkedList<>();
        // Lay du lieu ra mCursor
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery(mQueryGetAllNotes, null);
        // Tra du lieu vao mNoteList
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            int noteID = mCursor.getInt(0);
            String noteTitle = mCursor.getString(1);
            String noteDescription = mCursor.getString(2);
            mNoteList.add(new Note(noteID, noteTitle, noteDescription));
            mCursor.moveToNext();
        }
        mCursor.close();
        return mNoteList;
    }

    public Note getNoteByID(int noteID){
        Note mNote = null;
        // Lay du lieu ra mCursor
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery(mQueryGetNoteByID , new String[]{noteID + ""});
        // Tra du lieu vao mNote
        if(mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            String noteTitle = mCursor.getString(1);
            String noteDescription = mCursor.getString(2);
            mNote = new Note(noteID, noteTitle, noteDescription);
        }
        mCursor.close();
        return mNote;
    }

    public void insertNote(Note mNote) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(mQueryInsertNote, new String[]{mNote.noteTitle, mNote.noteDescription});
    }





}
