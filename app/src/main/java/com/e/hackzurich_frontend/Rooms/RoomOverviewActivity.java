package com.e.hackzurich_frontend.Rooms;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.e.hackzurich_frontend.R;

import java.util.ArrayList;

public class RoomOverviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Room room;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_overview);

        actionBar = getSupportActionBar();
        bottomNavigationView = findViewById(R.id.navigationView);

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





}
