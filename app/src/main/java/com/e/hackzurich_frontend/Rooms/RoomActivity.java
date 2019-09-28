package com.e.hackzurich_frontend.Rooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;


import com.e.hackzurich_frontend.R;

public class RoomActivity extends AppCompatActivity {

    String roomName = "Coffee Meet Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);

        setTitle(roomName);


        //Intent intent = getIntent();
        //String id = intent.getStringExtra("id");

        //Log.d("TAG", "onCreate: " + id.toString());
    }
}
