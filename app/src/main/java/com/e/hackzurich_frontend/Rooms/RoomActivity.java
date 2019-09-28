package com.e.hackzurich_frontend.Rooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.MainActivity;
import com.e.hackzurich_frontend.NameActivity;
import com.e.hackzurich_frontend.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomActivity extends AppCompatActivity {

    Button shareBtn;

    private String id;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);





        shareBtn = (Button) findViewById(R.id.button6);
        shareBtn.setOnClickListener(startListener);

        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        calculate();
    }

    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Room Id");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, id);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
    };


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
