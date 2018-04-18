package cs4605.asthmagame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHandler  extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 7;

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
    private static final String KEY_ANSWERED_CORRECTLY = "answeredCorrectly";


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
                + KEY_OPTION3 + " TEXT," + KEY_EXPLANATION + " TEXT,"
                + KEY_ANSWERED_CORRECTLY + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);
        loadQuizTable(db);

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
        String DROP_TABLE = "DROP TABLE " + TABLE_QUIZ;
        db.execSQL(DROP_TABLE);
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_QUIZ + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_QUESTION + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_ANSWER + " TEXT," + KEY_OPTION2 + " TEXT,"
                + KEY_OPTION3 + " TEXT," + KEY_EXPLANATION + " TEXT,"
                + KEY_ANSWERED_CORRECTLY + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);
        loadQuizTable(db);
    }

    private void deleteQuizTable(SQLiteDatabase db) {
        db.delete(TABLE_QUIZ,null,null);

    }

    private void loadQuizTable(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(KEY_QUESTION, "What is Asthma?");
        values.put(KEY_CATEGORY, "Etiology");
        values.put(KEY_ANSWER, "Asthma is a disease that makes it difficult to breathe");
        values.put(KEY_OPTION2, "Asthma is an allergy");
        values.put(KEY_OPTION3, "Asthma is a person");
        values.put(KEY_EXPLANATION, "Asthma is a disease that affects the lungs and narrows airways that make it difficult to breathe. Taking medicines regularly help to control Asthma.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "Is Asthma contagious (Can you catch Asthma)?");
        values.put(KEY_CATEGORY, "Etiology");
        values.put(KEY_ANSWER, "No, Asthma is not contagious.");
        values.put(KEY_OPTION2, "Yes it is.");
        values.put(KEY_OPTION3, "Sometimes contagious.");
        values.put(KEY_EXPLANATION, "Asthma is not contagious. Asthma is mostly caused by allergies to triggers like pollen, smoke, pet dander, dust mites, weather changes and mold.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "What triggers Asthma?");
        values.put(KEY_CATEGORY, "Etiology");
        values.put(KEY_ANSWER, "Allergies to certain things like cold air, smoke, mold, pollution etc. can trigger Asthma");
        values.put(KEY_OPTION2, "Vegetables and fruits");
        values.put(KEY_OPTION3, "Homework");
        values.put(KEY_EXPLANATION, "Asthma can be triggered by mold, pollen, dust mites, smoke, pets, perfumes, pollution or wood smoke. Sometimes heavy exercise, infections and cold weather can trigger Asthma");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "How can I avoid getting an Asthma attack (getting sick)?");
        values.put(KEY_CATEGORY, "Etiology");
        values.put(KEY_ANSWER, "I can manage my Asthma by avoiding triggers like smoke, mold, dust etc and by taking my medicines regularly.");
        values.put(KEY_OPTION2, "I can manage my Asthma by not taking my medicines");
        values.put(KEY_OPTION3, "There is no way to avoid it.");
        values.put(KEY_EXPLANATION, "You can avoid getting sick by avoiding triggers such as mold, pollen, dust, smoke etc. and by taking you medication daily and regularly");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "What are symptoms of an Asthma attack?");
        values.put(KEY_CATEGORY, "Symptoms");
        values.put(KEY_ANSWER, "I have a constant cough, chest tightness and pain");
        values.put(KEY_OPTION2, "I can breathe easily.");
        values.put(KEY_OPTION3, "No symptoms");
        values.put(KEY_EXPLANATION, "Continuous cough, chest tightness and chest pain are symptoms of an Asthma attack.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "Can my Asthma be cured?");
        values.put(KEY_CATEGORY, "Assessing Management");
        values.put(KEY_ANSWER, "Asthma cannot be cured, but avoiding triggers and taking medicines regularly will help to manage it");
        values.put(KEY_OPTION2, "Asthma can be cured.");
        values.put(KEY_OPTION3, "It will go away on its own like a cold");
        values.put(KEY_EXPLANATION, "Asthma cannot be cured but taking medication regularly and avoiding Asthma triggers will help to keep it in control.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "What is the rule of 2’s?");
        values.put(KEY_CATEGORY, "Assessing Management");
        values.put(KEY_ANSWER, "If you have symptoms more than twice a week, need your rescue inhaler more than twice a week, or you wake up from sleep twice a month, then your asthma isn't in control");
        values.put(KEY_OPTION2, "If you have symptoms more than twice a MONTH then your Asthma isn't in control");
        values.put(KEY_OPTION3, "");
        values.put(KEY_EXPLANATION, "The rule of 2’s helps to check if you are keeping your Asthma in control. If you have symptoms more than 2 times a week, or requiring use of your rescue inhaler more than then 2 times a week, or if you are waking up from sleep 2 times a month, then your asthma is not in control");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "What is long term control?");
        values.put(KEY_CATEGORY, "Treatment");
        values.put(KEY_ANSWER, "It is controller medicine/inhaler that I take everyday");
        values.put(KEY_OPTION2, "It is the rescue inhaler that I take when I have a flare up.");
        values.put(KEY_OPTION3, "");
        values.put(KEY_EXPLANATION, "Long term control is medication such as  your controller medicine/ inhaler that is taken EVERYDAY. Long term control is used if you have 2 or more flare ups in a week.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "What is short term control?");
        values.put(KEY_CATEGORY, "Treatment");
        values.put(KEY_ANSWER, "This is the medicine/rescue inhaler I use when I have an occasional flare up");
        values.put(KEY_OPTION2, "It is controller medicine/inhaler that I take everyday");
        values.put(KEY_OPTION3, "");
        values.put(KEY_EXPLANATION, "Short term control is when you use your rescue inhaler.  This medicine relaxes muscles in airway. This is only for the occasional flare up.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "Why should I use a spacer?");
        values.put(KEY_CATEGORY, "Treatment");
        values.put(KEY_ANSWER, "A spacer is required to get medicines to the lungs.");
        values.put(KEY_OPTION2, "A spacer is an astronaut");
        values.put(KEY_OPTION3, "");
        values.put(KEY_EXPLANATION, "A spacer is required to get medicines to the lungs.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "What are your Asthma triggers?");
        values.put(KEY_CATEGORY, "Managing Asthma");
        values.put(KEY_ANSWER, "Pollen, dust mites and pets");
        values.put(KEY_OPTION2, "Ice cream, candy");
        values.put(KEY_OPTION3, "");
        values.put(KEY_EXPLANATION, "Asthma can be triggered by mold, pollen, dust mites, smoke, pets, perfumes, pollution or wood smoke. Sometimes heavy exercise, infections and cold weather can trigger Asthma");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

        values.put(KEY_QUESTION, "When should I seek emergency help?");
        values.put(KEY_CATEGORY, "Managing Asthma");
        values.put(KEY_ANSWER, "When ribs are sucking in at your chest and above the collar bone or when you need your rescue inhaler more than every 4 hours. When you feel your inhaler isn't helping and you feel short of breath.");
        values.put(KEY_OPTION2, "Never");
        values.put(KEY_OPTION3, "");
        values.put(KEY_EXPLANATION, "When ribs are sucking in at your chest and above the collar bone, these are called retractions. This is an Emergency. When you are requiring use of your rescue inhaler more frequently than every 4 hours. When you feel your rescue inhaler is not helping and you continue to feel short of breath.");
        values.put(KEY_ANSWERED_CORRECTLY, "No");
        db.insert(TABLE_QUIZ, null, values);

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

    public QuizFacts getQuizFact() {

        String cat;
        String fact;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT "+ KEY_CATEGORY + ", " + KEY_EXPLANATION + " FROM " + TABLE_QUIZ + " ORDER BY RANDOM() LIMIT 1" ,  null);

        if (c != null && c.moveToFirst()) {
            //c.moveToFirst();
            cat = c.getString(0);
            fact = c.getString(1);
            c.close();

            return new QuizFacts(cat, fact);
        }
        c.close();
        return null;

    }

    public ArrayList<QuizQuestions> getQuizQuestions() {

        ArrayList<QuizQuestions> questionsForQuiz = new ArrayList<>();

        String category;
        String question;
        String answer;
        String option2;
        String option3;
        String explanation;
        String answeredCorrectly;

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT " + KEY_CATEGORY + ", " + KEY_QUESTION + ", " + KEY_ANSWER + ", "
                        + KEY_OPTION2 + ", " + KEY_OPTION3 + ", " + KEY_EXPLANATION + ", "
                        + KEY_ANSWERED_CORRECTLY + " FROM " + TABLE_QUIZ + " ORDER BY RANDOM()",
                null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                category = c.getString(0);
                question = c.getString(1);
                answer = c.getString(2);
                option2 = c.getString(3);
                option3 = c.getString(4);
                explanation = c.getString(5);
                answeredCorrectly = c.getString(6);
                if (answeredCorrectly.equals("No")) {
                    QuizQuestions q = new QuizQuestions(category, question, answer, option2,
                            option3, explanation, answeredCorrectly);
                    questionsForQuiz.add(q);
                }
            }
            c.close();
            return questionsForQuiz;
        }
        c.close();
        return null;
    }

    public void setQuestionResult(String answeredCorrectly, String question) {
        ContentValues data = new ContentValues();
        data.put(KEY_ANSWERED_CORRECTLY, answeredCorrectly);
        getReadableDatabase().update(TABLE_QUIZ, data, KEY_QUESTION + " =?" , new String[] {question});
    }
}
