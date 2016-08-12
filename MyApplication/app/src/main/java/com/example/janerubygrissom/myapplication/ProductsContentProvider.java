package com.example.janerubygrissom.myapplication;

/**
 * Created by janerubygrissom on 8/12/16.
 */

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

public class ProductsContentProvider extends ContentProvider {
    private ProductsDBHelper mProductsDBHandler;

    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(ProductsContract.AUTHORITY, ProductsDBHelper.TABLE_MARKIT, PRODUCTS);
        sURIMatcher.addURI(ProductsContract.AUTHORITY, ProductsDBHelper.TABLE_MARKIT + "/#", PRODUCTS_ID);
    }

    @Override
    public boolean onCreate() {
        mProductsDBHandler = ProductsDBHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case PRODUCTS:
                return ProductsContract.Products.CONTENT_TYPE;
            case PRODUCTS_ID:
                return ProductsContract.Products.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int uriType = sURIMatcher.match(uri);

        long id = 0;
        switch (uriType) {
            case PRODUCTS:
                id = mProductsDBHandler.addProduct(contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(ProductsContract.Products.CONTENT_URI, id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {

        return 0;

    }



    @Override


    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        return 0;

    }


}
