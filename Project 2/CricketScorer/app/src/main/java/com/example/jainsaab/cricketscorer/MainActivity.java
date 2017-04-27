package com.example.jainsaab.cricketscorer;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int wicketsTeamA = 0;
    int wicketsTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void displayForTeamA() {
        TextView scoreA = (TextView) findViewById(R.id.team_a_score);
        TextView wicketsA = (TextView) findViewById(R.id.team_a_wickets);
        scoreA.setText(String.valueOf(scoreTeamA));
        wicketsA.setText(String.valueOf(wicketsTeamA));
    }

    void displayForTeamB() {
        TextView scoreB = (TextView) findViewById(R.id.team_b_score);
        TextView wicketsB = (TextView) findViewById(R.id.team_b_wickets);
        scoreB.setText(String.valueOf(scoreTeamB));
        wicketsB.setText(String.valueOf(wicketsTeamB));
    }

    public void singleA(View view) {
        scoreTeamA += 1;
        displayForTeamA();
    }

    public void doubleA(View view) {
        scoreTeamA += 2;
        displayForTeamA();
    }

    public void threeA(View view) {
        scoreTeamA += 3;
        displayForTeamA();
    }

    public void fourA(View view) {
        scoreTeamA += 4;
        displayForTeamA();
    }

    public void fiveA(View view) {
        scoreTeamA += 5;
        displayForTeamA();
    }

    public void sixA(View view) {
        scoreTeamA += 6;
        displayForTeamA();
    }

    public void wideA(View view) {
        scoreTeamA += 1;
        displayForTeamA();
    }

    public void noBallA(View view) {
        scoreTeamA += 1;
        displayForTeamA();
    }

    public void outA(View view) {
        wicketsTeamA += 1;
        displayForTeamA();
    }

    public void singleB(View view) {
        scoreTeamB += 1;
        displayForTeamB();
    }

    public void doubleB(View view) {
        scoreTeamB += 2;
        displayForTeamB();
    }

    public void threeB(View view) {
        scoreTeamB += 3;
        displayForTeamB();
    }

    public void fourB(View view) {
        scoreTeamB += 4;
        displayForTeamB();
    }

    public void fiveB(View view) {
        scoreTeamB += 5;
        displayForTeamB();
    }

    public void sixB(View view) {
        scoreTeamB += 6;
        displayForTeamB();
    }

    public void wideB(View view) {
        scoreTeamB += 1;
        displayForTeamB();
    }

    public void noBallB(View view) {
        scoreTeamB += 1;
        displayForTeamB();
    }

    public void outB(View view) {
        wicketsTeamB += 1;
        displayForTeamB();
    }

    public void reset(View view) {
        scoreTeamA = 0;
        wicketsTeamA = 0;
        displayForTeamA();
        scoreTeamB = 0;
        wicketsTeamB = 0;
        displayForTeamB();
    }

    public void finalWin(View view){
        if(scoreTeamA > scoreTeamB)
            showDialog(0);
        else if(scoreTeamA < scoreTeamB)
            showDialog(1);
        else
            showDialog(2);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Final!");
        switch (id){
            case 0:
                builder.setMessage("WooHoo!! Team A wins.");
                return builder.create();
            case 1:
                builder.setMessage("WooHoo!! Team B wins.");
                return builder.create();
            case 2:
                builder.setMessage("Awww! Match Drawn.");
                return builder.create();
            default:
                break;
        }
        return super.onCreateDialog(id);
    }
}
