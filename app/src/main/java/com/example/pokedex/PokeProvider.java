package com.example.pokedex;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PokeProvider extends ContentProvider {


    public static final String DBNAME = "PokeDB";
    public static final String TABLE_NAME = "PokemonEntries";
    public static final String COLUMN_1NAME = "NationalNumber";
    public static final String COLUMN_2NAME = "Name";
    public static final String COLUMN_3NAME = "Species";
    public static final String COLUMN_4NAME = "Gender";
    public static final String COLUMN_5NAME = "Height";
    public static final String COLUMN_6NAME = "Weight";
    public static final String COLUMN_7NAME = "Level";
    public static final String COLUMN_8NAME = "HP";
    public static final String COLUMN_9NAME = "Attack";
    public static final String COLUMN_10NAME = "Defense";
    public static final String AUTHORITY = "belmont.poketrackerdb";
    public static final Uri contentURI = Uri.parse("content://" + AUTHORITY + "/" + DBNAME);
    private MainDatabaseHelper SQLHelper;
    private static String CREATE_DB_QUERY = "CREATE TABLE " + TABLE_NAME +
            "( _ID INTEGER PRIMARY KEY," +
            COLUMN_1NAME + " INTEGER," +
            COLUMN_2NAME + " TEXT," +
            COLUMN_3NAME + " TEXT," +
            COLUMN_4NAME + " TEXT," +
            COLUMN_5NAME + " INTEGER," +
            COLUMN_6NAME + " INTEGER," +
            COLUMN_7NAME + " INTEGER," +
            COLUMN_8NAME + " INTEGER," +
            COLUMN_9NAME + " INTEGER," +
            COLUMN_10NAME + " INTEGER)";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_DB_QUERY);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

        }
    };

    public PokeProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return SQLHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long id = SQLHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        return Uri.withAppendedPath(contentURI,"" + id);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        return SQLHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}