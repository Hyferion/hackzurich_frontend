package com.e.hackzurich_frontend.RoomCreation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.JoinRoomActivity;
import com.e.hackzurich_frontend.R;
import com.e.hackzurich_frontend.Rooms.RoomActivity;
import com.e.hackzurich_frontend.Rooms.RoomOverviewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoomCreate extends AppCompatActivity {

    private RequestQueue queue;
    private Button btn;
    private EditText roomName;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        sharedPreferences = getSharedPreferences("united",
                Context.MODE_PRIVATE);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Eat");
        arrayList.add("Drink");
        arrayList.add("Cinema");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        roomName = findViewById(R.id.editText2);
        btn = findViewById(R.id.button4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRoom();
            }
        });

        queue = Volley.newRequestQueue(this);
    }


    public void createRoom() {
        JSONObject payload = new JSONObject();
        int id = sharedPreferences.getInt("id", 0);

        try {
            payload.put("name", roomName.getText().toString());
            payload.put("owner", id);
            payload.put("members", new JSONArray(id));
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


    public void redirectToRoom(String id) {
        Intent intent = new Intent(RoomCreate.this, RoomOverviewActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
