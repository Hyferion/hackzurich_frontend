package com.e.hackzurich_frontend.Rooms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.JoinRoomActivity;
import com.e.hackzurich_frontend.MainActivity;
import com.e.hackzurich_frontend.NameActivity;
import com.e.hackzurich_frontend.R;
import com.e.hackzurich_frontend.RoomCreation.RoomCreate;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private SharedPreferences.Editor editor;
    private RequestQueue queue;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_overview);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        queue = Volley.newRequestQueue(this);

        sharedPreferences = getSharedPreferences("united",
                Context.MODE_PRIVATE);

        //editor = getSharedPreferences("united", 0).edit();
        //editor.clear();
        //editor.commit();

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

        getRooms();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new RoomAdapter(rooms, this);
        recyclerView.setAdapter(mAdapter);

        getUserInstance();

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        updatePosition(location);
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });


    }




    public void checkIfNameIsSetAndRedirect() {

        if (!sharedPreferences.contains("name")) {
            Intent intent = new Intent(RoomOverviewActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


    public void getRooms() {
        JSONObject payload = new JSONObject();
        int id = sharedPreferences.getInt("id", 0);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://34.65.124.86:8000/room?user=" + Integer.toString(id), payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
            Gson gson = new Gson();

            JSONArray jsonArray = response.optJSONArray("results");

            for (int n = 0; n < jsonArray.length(); n++) {
                try {
                    JSONObject object = jsonArray.getJSONObject(n);
                    Room room = gson.fromJson(object.toString(), Room.class);
                    rooms.add(room);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            mAdapter.notifyDataSetChanged();
        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {
        };

        queue.add(jsonObjectRequest);
    }

    public void getUserInstance() {
        JSONObject payload = new JSONObject();
        int id = sharedPreferences.getInt("id", 0);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://34.65.124.86:8000/userinstance/" + Integer.toString(id), payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());

        }, error -> {
            createUserInstance();
            Log.d("LOG", "createUser: " + error.toString());
        }) {
        };
        queue.add(jsonObjectRequest);
    }

    public void createUserInstance() {
        JSONObject payload = new JSONObject();
        try {
            payload.put("userid", sharedPreferences.getInt("id", 0));
            payload.put("lat", 0);
            payload.put("lng", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://34.65.124.86:8000/userinstance/", payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {

        };
        queue.add(jsonObjectRequest);

    }

    public void updatePosition(Location location) {
        int id = sharedPreferences.getInt("id", 0);
        JSONObject payload = new JSONObject();
        try {
            payload.put("userid", sharedPreferences.getInt("id", 0));
            payload.put("lat", location.getLatitude());
            payload.put("lng", location.getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, "http://34.65.124.86:8000/userinstance/" + Integer.toString(id), payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {

        };
        queue.add(jsonObjectRequest);

    }


}
