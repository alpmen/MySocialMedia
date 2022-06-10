package com.orhunalpyamen.mysocialmedia;

import android.provider.BaseColumns;

public class TablesInfo {



    public static final class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";

        public static final String COLUMN_ID = "note_id";
        public static final String COLUMN_MAIL = "note_mail";
        public static final String COLUMN_NOTE = "note_note";
    }

}
