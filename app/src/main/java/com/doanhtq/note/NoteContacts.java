package com.doanhtq.note;

import android.net.Uri;

public class NoteContacts {
        public static final String NOTE_DB_NAME = "notes";
        public static final int NOTE_DB_VERSION = 2;

        public static final String COLUMN_ID = "note_id";
        public static final String NOTE_TABLE_NAME = "notes";
        public static final String COLUMN_TITLE_NOTE = "note_title";
        public static final String COLUMN_CONTENT_NOTE = "note_description";

        static final String SINGLE_NOTE_MIME_TYPE =
                "vnd.android.cursor.item/vnd.com.doanhtq.note.notes";
        static final String MULTIPLE_NOTES_MIME_TYPE =
                "vnd.android.cursor.dir/vnd.com.doanhtq.note.notes";

        public static final String AUTHORITY = "com.doanhtq.note.noteprovider";
        public static final String CONTENT_PATH =  "words";
        public static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
        public static final Uri CONTENT_URI = Uri.parse(URL);

}
