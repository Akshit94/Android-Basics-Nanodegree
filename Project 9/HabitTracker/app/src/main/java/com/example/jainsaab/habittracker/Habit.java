package com.example.jainsaab.habittracker;

public class Habit {

    private String mHabitName;
    private int mHabitFreq;
    private int mHabitKey;

    Habit(String habitName, int habitFreq , int habitKey) {
        mHabitName = habitName;
        mHabitFreq = habitFreq;
        mHabitKey = habitKey;
    }

    public String getHabitName() {
        return mHabitName;
    }

    public int getHabitFreq() {
        return mHabitFreq;
    }

    public int getHabitKey() {
        return mHabitKey;
    }
}
