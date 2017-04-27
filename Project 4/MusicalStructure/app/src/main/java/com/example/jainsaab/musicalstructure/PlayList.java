package com.example.jainsaab.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        Button nowPlaying = (Button) findViewById(R.id.now_playing);
        nowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nowPlayingIntent = new Intent(getApplicationContext(), NowPlaying.class);
                startActivity(nowPlayingIntent);
            }
        });

        Button listen_now = (Button) findViewById(R.id.listen_now);
        listen_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listeNowIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(listeNowIntent);
            }
        });

    }
}
