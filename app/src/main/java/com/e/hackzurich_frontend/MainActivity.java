package com.e.hackzurich_frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startBtn = (Button) findViewById(R.id.button);

        startBtn.setOnClickListener(startListener);
    }


    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Check if already logged in & Check if already has an user id
            Intent intent = new Intent(MainActivity.this, NameActivity.class);
        }
    };



}
