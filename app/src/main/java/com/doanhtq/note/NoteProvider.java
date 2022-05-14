package com.doanhtq.note;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class NoteProvider extends ContentProvider {
    static final int NOTES = 1;
    static final int NOTE_ID = 2;

    static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(NoteContacts.AUTHORITY, NoteContacts.CONTENT_PATH, NOTES);
        sUriMatcher.addURI(NoteContacts.AUTHORITY, NoteContacts.CONTENT_PATH + "/#", NOTE_ID );
    }

    private SQLiteDatabase mSqLiteDatabase;

    public NoteProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int count = 0;
        switch (sUriMatcher.match(uri)){
            case NOTES:
                // Truong hop xoa toan bo notes
                count = mSqLiteDatabase.delete(NoteContacts.NOTE_TABLE_NAME, selection, selectionArgs);
                break;

            case NOTE_ID:
                // Truong hop xoa 1 note
                String id = uri.getPathSegments().get(1);
                count = mSqLiteDatabase.delete(NoteContacts.NOTE_TABLE_NAME, NoteContacts.COLUMN_ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        // Notify cho cac thanh phan lang nghe
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (sUriMatcher.match(uri)){
            case NOTES:
                return NoteContacts.MULTIPLE_NOTES_MIME_TYPE;
            case NOTE_ID:
                return NoteContacts.SINGLE_NOTE_MIME_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long rowID = mSqLiteDatabase.insert(NoteContacts.NOTE_TABLE_NAME, null, values);
        if(rowID > 0){
            Uri newNoteUri = ContentUris.withAppendedId(NoteContacts.CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(newNoteUri,null);
            return newNoteUri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Context context = getContext();

        NoteOpenHelper noteOpenHelper = new NoteOpenHelper(context);
        mSqLiteDatabase = noteOpenHelper.getWritableDatabase();

        return mSqLiteDatabase == null ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(NoteContacts.NOTE_TABLE_NAME);
        switch (sUriMatcher.match(uri)){
            case NOTES:
                break;
            case NOTE_ID:
                qb.appendWhere(NoteContacts.COLUMN_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        Cursor cursor = qb.query(mSqLiteDatabase, projection, selection,selectionArgs,null,null, sortOrder);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.

        throw new UnsupportedOperationException("Not yet implemented");

    }
}