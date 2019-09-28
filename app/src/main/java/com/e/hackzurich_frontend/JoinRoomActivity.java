package com.e.hackzurich_frontend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.e.hackzurich_frontend.Rooms.Room;
import com.e.hackzurich_frontend.Rooms.RoomActivity;
import com.e.hackzurich_frontend.Rooms.RoomAdapter;

import java.util.ArrayList;

public class JoinRoomActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;

    EditText editText;

    final EditText editRoom = (EditText) findViewById(R.id.editUsername);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new RoomAdapter(rooms, this);
        recyclerView.setAdapter(mAdapter);


        editRoom.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press


                    // Start room activity
                    Intent intent = new Intent(JoinRoomActivity.this, RoomActivity.class);
                    startActivity(intent);


                    return true;
                }
                return false;
            }
        });

    }

}
