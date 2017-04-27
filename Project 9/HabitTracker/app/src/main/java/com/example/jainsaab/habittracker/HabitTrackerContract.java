package com.example.jainsaab.habittracker;

import android.provider.BaseColumns;

public class HabitTrackerContract {

    public static final class HabitsEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String COL_HABIT_KEY = "habit_id";

        public static final String COL_HABIT_NAME = "habit";

        public static final String COL_HABIT_FREQ = "freq";

    }

}
