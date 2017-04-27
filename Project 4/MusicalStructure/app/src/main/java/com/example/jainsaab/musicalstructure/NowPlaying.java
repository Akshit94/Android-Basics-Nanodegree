package com.example.jainsaab.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        Button listen_now = (Button) findViewById(R.id.listen_now);
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button shuffle = (Button) findViewById(R.id.shuffle);
        Button next = (Button) findViewById(R.id.next);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will play the song", Toast.LENGTH_SHORT).show();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will pause the song", Toast.LENGTH_SHORT).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will play the next song in the playlist", Toast.LENGTH_SHORT).show();
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will shuffle the songs", Toast.LENGTH_SHORT).show();
            }
        });

        listen_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listeNowIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(listeNowIntent);
            }
        });
    }
}
