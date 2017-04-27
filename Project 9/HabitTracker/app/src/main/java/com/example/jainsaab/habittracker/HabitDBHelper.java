package com.example.jainsaab.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "habits.db";


    public HabitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitTrackerContract.HabitsEntry.TABLE_NAME + " (" +
                HabitTrackerContract.HabitsEntry.COL_HABIT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitTrackerContract.HabitsEntry.COL_HABIT_NAME + " TEXT UNIQUE NOT NULL, " +
                HabitTrackerContract.HabitsEntry.COL_HABIT_FREQ + " INTEGER NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HabitTrackerContract.HabitsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    void insertHabits(Habit habit) {
        //Create a Database Connection
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitTrackerContract.HabitsEntry.COL_HABIT_KEY, habit.getHabitKey());
        values.put(HabitTrackerContract.HabitsEntry.COL_HABIT_NAME, habit.getHabitName());
        values.put(HabitTrackerContract.HabitsEntry.COL_HABIT_FREQ, habit.getHabitFreq());
        // Inserting Row
        db.insert(HabitTrackerContract.HabitsEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public Cursor queryHabits(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(HabitTrackerContract.HabitsEntry.TABLE_NAME,
                new String[]{HabitTrackerContract.HabitsEntry.COL_HABIT_KEY,
                        HabitTrackerContract.HabitsEntry.COL_HABIT_NAME,
                        HabitTrackerContract.HabitsEntry.COL_HABIT_FREQ},
                HabitTrackerContract.HabitsEntry.COL_HABIT_KEY + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }
}
