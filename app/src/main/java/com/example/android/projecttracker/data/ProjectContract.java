package com.example.android.projecttracker.data;

import android.provider.BaseColumns;

/**
 * Created by JOAO on 30-Apr-18.
 */

public class ProjectContract {

    public static final class ProjectEntry implements BaseColumns {

        //table
        public static final String TABLE_NAME = "projects";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PROJECT_NAME = "name";
        public static final String COLUMN_PROJECT_TYPE = "type";
        public static final String COLUMN_PROJECT_CLIENT = "client";
        public static final String COLUMN_PROJECT_PRICE = "price";

        public static final String TYPE_FRONTEND = "Web Front-End";
        public static final String TYPE_BACKEND = "Web Back-End";
        public static final String TYPE_ANDROIDAPP = "Android App";

    }
}
