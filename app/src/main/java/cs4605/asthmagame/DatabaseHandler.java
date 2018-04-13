package cs4605.asthmagame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHandler  extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "asthma_game";

    // Table Names
    private static final String TABLE_SETTINGS = "game_settings";
    private static final String TABLE_HISTORY = "game_history";
    private static final String TABLE_QUIZ = "game_quiz";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "canisterDate";
    private static final String KEY_CANISTER = "canisterCount";
    private static final String KEY_EXTRA = "canistersExtraLeft";
    private static final String KEY_CATEGORY = "questionCategory";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTION2 = "option2";
    private static final String KEY_OPTION3 = "option3";
    private static final String KEY_EXPLANATION = "answerExplanation";


    /**
     * DatabaseHandler constructor
     * @param context the activity the database is handling
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate constructor that creates the database tables for the passed database
     * @param db the activity the database is handling
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_SETTINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT,"
                + KEY_CANISTER + " INTEGER," + KEY_EXTRA + " INTEGER" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT,"
                + KEY_CANISTER + " INTEGER" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_QUIZ + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_QUESTION + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_ANSWER + " TEXT," + KEY_OPTION2 + " TEXT,"
                + KEY_OPTION3 + " TEXT," + KEY_EXPLANATION + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

    }

    private void createNewDb(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * onUpgrade constructor drops then recreates the database tables if the database version is
     * newer
     * @param db the activity the database is handling
     * @param oldVersion the old database version number
     * @param newVersion the new database version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCanister (Canister canister) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int extraCount = 1;
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, sdf.format(canister.get_loginDate()));
        values.put(KEY_CANISTER, canister.get_canisterCount());

        // Inserting Row
        db.insert(TABLE_HISTORY, null, values);

        getReadableDatabase().execSQL("DELETE FROM " + TABLE_SETTINGS + "" );
        values.put(KEY_EXTRA, extraCount);
        db.insert(TABLE_SETTINGS, null, values);
        db.close(); // Closing database connection
    }

    public String getSettingDate () {
        String lastCanisterDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT "+ KEY_DATE + " FROM " + TABLE_SETTINGS + "" ,  null);
        if (c != null && c.moveToFirst()) {
            //c.moveToFirst();
            lastCanisterDate = c.getString(0);
            c.close();
            return lastCanisterDate;
        }
        c.close();
        return null;
    }

}
