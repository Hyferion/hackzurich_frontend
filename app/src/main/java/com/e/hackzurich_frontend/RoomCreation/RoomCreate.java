package com.e.hackzurich_frontend.RoomCreation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.JoinRoomActivity;
import com.e.hackzurich_frontend.R;
import com.e.hackzurich_frontend.Rooms.RoomActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomCreate extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);


        queue = Volley.newRequestQueue(this);
        createRoom();
    }


    public void createRoom(){
        JSONObject payload = new JSONObject();
        try {
            payload.put("name", "test");
            payload.put("owner", 1);
            payload.put("members", new JSONArray(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8000/room", payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
            try {
                redirectToRoom((String) response.get("identifier"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {
        };

        queue.add(jsonObjectRequest);
    }


    public void redirectToRoom(String id){
        Intent intent = new Intent(RoomCreate.this, RoomActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
