package com.example.janerubygrissom.myapplication;

/**
 * Created by janerubygrissom on 8/12/16.
 */
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

class ProductsContract {
        public static final String AUTHORITY = "com.example.janerubygrissom.myapplication.ProductsContentProvider";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

                public static final class Products implements BaseColumns {
                public static final String TABLE_MARKIT = "markit";
                public static final String COLUMN_NAME = "name";
                public static final String COLUMN_CHANGE = "change";

                       public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, "markit");

                        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                                + "/com.example.janerubygrissom.myapplication";

                        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                                + "/com.example.janerubygrissom.myapplication";
            }
    }
