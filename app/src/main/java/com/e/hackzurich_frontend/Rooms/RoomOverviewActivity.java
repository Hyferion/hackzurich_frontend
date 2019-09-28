package com.e.hackzurich_frontend.Rooms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.e.hackzurich_frontend.JoinRoomActivity;
import com.e.hackzurich_frontend.MainActivity;
import com.e.hackzurich_frontend.NameActivity;
import com.e.hackzurich_frontend.R;
import com.e.hackzurich_frontend.RoomCreation.RoomCreate;

import java.util.ArrayList;

public class RoomOverviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Room room;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_overview);


        sharedPreferences = getSharedPreferences("united",
                Context.MODE_PRIVATE);

        checkIfNameIsSetAndRedirect();

        actionBar = getSupportActionBar();
        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.navigation_create) {
                    Intent intent = new Intent(RoomOverviewActivity.this, RoomCreate.class);
                    startActivity(intent);
                } else if (id == R.id.navigation_join) {
                    Intent intent = new Intent(RoomOverviewActivity.this, JoinRoomActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        room = new Room();
        room.setIdentifier("ase21");
        room.setName("room");
        room.setOwner("Silas");

        rooms.add(room);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new RoomAdapter(rooms, this);
        recyclerView.setAdapter(mAdapter);


    }


    public void checkIfNameIsSetAndRedirect() {
        if (!sharedPreferences.contains("name")) {
            Intent intent = new Intent(RoomOverviewActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


}
