package com.example.jainsaab.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDBHelper helper = new HabitDBHelper(this);
        Habit habit = new Habit("Meditate", 1, 1);
        helper.insertHabits(habit);
        helper.queryHabits(1);
    }
}
