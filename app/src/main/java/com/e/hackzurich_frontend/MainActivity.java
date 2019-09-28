package com.e.hackzurich_frontend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    private SharedPreferences myPreferences;

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


            SharedPreferences myPreferences = getSharedPreferences("mypreference",
                    Context.MODE_PRIVATE);

            //myPreferences.edit().clear().commit();


            if(myPreferences.contains("Name")){
                // start Room activity
                Intent intent = new Intent(MainActivity.this, RoomActivity.class);
                startActivity(intent);
            }else{
                // start Name activity
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                startActivity(intent);
            }
        }
    };




}
