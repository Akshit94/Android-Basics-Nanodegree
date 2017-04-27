package com.example.jainsaab.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nowPlaying = (Button) findViewById(R.id.now_playing);
        Button shopOnline = (Button) findViewById(R.id.shop);
        Button playList = (Button) findViewById(R.id.play_list);

        nowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nowPlayingIntent = new Intent(getApplicationContext(),NowPlaying.class);
                startActivity(nowPlayingIntent);
            }
        });

        shopOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shopOnlineIntent = new Intent(getApplicationContext(),ShopOnline.class);
                startActivity(shopOnlineIntent);
            }
        });

        playList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playListIntent = new Intent(getApplicationContext(),PlayList.class);
                startActivity(playListIntent);
            }
        });
    }
}
