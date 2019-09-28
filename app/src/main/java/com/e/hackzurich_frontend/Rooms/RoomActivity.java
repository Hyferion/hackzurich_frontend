package com.e.hackzurich_frontend.Rooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomActivity extends AppCompatActivity {

    private String id;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);

        setTitle(roomName);

        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Log.d("TAG", "onCreate: " + id.toString());

        calculate();
    }


    public void calculate() {
        JSONObject payload = new JSONObject();
        try {
            payload.put("room", id);
            payload.put("category", "restaurant");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8000/submitmeetup", payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
            Gson gson = new Gson();
            Room room = gson.fromJson(response.toString(), Room.class);
        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {
        };

        queue.add(jsonObjectRequest);
    }
}
