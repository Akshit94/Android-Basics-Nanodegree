package com.example.jainsaab.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShopOnline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_online);

        Button listen_now = (Button) findViewById(R.id.listen_now);
        listen_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listeNowIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(listeNowIntent);
            }
        });

        Button purchase = (Button) findViewById(R.id.purchase);

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Will take the user to GooglePlayStore for purchasing the song", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
