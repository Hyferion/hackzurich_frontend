package com.e.hackzurich_frontend;

import android.content.Context;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;


import com.e.hackzurich_frontend.Rooms.Room;
import com.e.hackzurich_frontend.Rooms.RoomAdapter;
import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.Rooms.RoomActivity;
import com.e.hackzurich_frontend.Rooms.RoomOverviewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JoinRoomActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;

    EditText editText;

    private EditText editRoom;
    private SharedPreferences sharedPreferences;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        queue = Volley.newRequestQueue(this);

        sharedPreferences = getSharedPreferences("united",
                Context.MODE_PRIVATE);

        editRoom = (EditText) findViewById(R.id.editRoomNumber);


        editRoom.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    //Todo: Join room request and wait for answer and ID
                    joinRoom();

                    return true;
                }
                return false;
            }
        });

    }


    public void joinRoom() {
        JSONObject payload = new JSONObject();
        try {
            payload.put("user", sharedPreferences.getInt("id", 0));
            payload.put("room", editRoom.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8000/addmember/", payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {

        };

        redirectToOverview();
        queue.add(jsonObjectRequest);
    }

    public void redirectToOverview() {
        Intent intent = new Intent(JoinRoomActivity.this, RoomOverviewActivity.class);
        startActivity(intent);
    }
}
