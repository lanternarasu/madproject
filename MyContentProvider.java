package com.example.genshin_damage_calc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }
    private DatabaseHelper databaseHelper;
    private final static String AUTHORITY = "com.example.genshin_damage_calc.provider";
    private final static String TABLE = "character_stats";
    private final static Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+TABLE);
    public static final int CHARACTER_STATS = 1;
    public static final int WEAPONS = 2;
    private static final UriMatcher sURIMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, TABLE, CHARACTER_STATS);
        sURIMatcher.addURI(AUTHORITY, TABLE + "/#",
                WEAPONS);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = databaseHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case CHARACTER_STATS:
                rowsDeleted = sqlDB.delete(databaseHelper.TABLE,
                        selection,
                        selectionArgs);
                break;
            case WEAPONS:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted =
                            sqlDB.delete(databaseHelper.TABLE,
                                    databaseHelper.COLUMN_ID + "=" + id,
                                    null);
                } else {
                    rowsDeleted =
                            sqlDB.delete(databaseHelper.TABLE,
                                    databaseHelper.COLUMN_ID + "=" + id
                                            + " and " + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " +
                        uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = databaseHelper.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case CHARACTER_STATS:
                id = sqlDB.insert(databaseHelper.TABLE,
                        null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "
                        + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(CHARACTER_STATS + "/" + id);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        if (database!=null)
        {
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(databaseHelper.TABLE);
        int uriType = sURIMatcher.match(uri);
        switch (uriType)
        {
            case CHARACTER_STATS:
                sqLiteQueryBuilder.appendWhere(databaseHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            case WEAPONS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }
        Cursor cursor = sqLiteQueryBuilder.query(databaseHelper.getReadableDatabase(),projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = databaseHelper.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case CHARACTER_STATS:
                rowsUpdated =
                        sqlDB.update(databaseHelper.TABLE,
                                values,
                                selection,
                                selectionArgs);
                break;
            case WEAPONS:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =
                            sqlDB.update(databaseHelper.TABLE,
                                    values,
                                    databaseHelper.COLUMN_ID + "=" + id,
                                    null);
                } else {
                    rowsUpdated =
                            sqlDB.update(databaseHelper.TABLE,
                                    values,
                                    databaseHelper.COLUMN_ID + "=" + id
                                            + " and "
                                            + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "
                        + uri);
        }
        getContext().getContentResolver().notifyChange(uri,
                null);
        return rowsUpdated;
    }
}