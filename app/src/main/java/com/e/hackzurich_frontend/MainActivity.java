package com.e.hackzurich_frontend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.e.hackzurich_frontend.Rooms.RoomActivity;
import com.e.hackzurich_frontend.Rooms.RoomOverviewActivity;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        this.getSupportActionBar().hide();



        startBtn = (Button) findViewById(R.id.button    );
        startBtn.setOnClickListener(startListener);
    }


    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this, NameActivity.class);
            startActivity(intent);
        }
    };





}
