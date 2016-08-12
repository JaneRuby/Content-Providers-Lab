package com.example.janerubygrissom.myapplication;

/**
 * Created by janerubygrissom on 8/12/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ProductsDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "ProductsDBHandler";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";

    public static final String TABLE_MARKIT = ProductsContract.Products.TABLE_MARKIT;
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = ProductsContract.Products.COLUMN_NAME;
    public static final String COLUMN_CHANGE = ProductsContract.Products.COLUMN_CHANGE;

    private static ProductsDBHelper mInstance;

    public static ProductsDBHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ProductsDBHelper(context);
        }
        return mInstance;
    }

    private ProductsDBHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "
                + TABLE_MARKIT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_CHANGE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKIT);
        onCreate(sqLiteDatabase);
    }

    public long addProduct(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long insertedRow = db.insert(TABLE_MARKIT, null, values);
        db.close();
        return insertedRow;
    }

    public Cursor findProduct(String selection, String[] selectionArgs, String sortOrder, String id) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;

        if (id == null)
            cursor = db.query(TABLE_MARKIT, null, selection, selectionArgs, null, null, sortOrder);
        else
            cursor = db.query(TABLE_MARKIT, null, COLUMN_ID + " = ?", new String[]{id}, null, null, sortOrder);

        return cursor;
    }

}
