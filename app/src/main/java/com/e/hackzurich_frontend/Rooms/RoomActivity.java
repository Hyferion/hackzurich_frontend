package com.e.hackzurich_frontend.Rooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


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

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    private Button shareBtn;

    private String id;

    private RequestQueue queue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;
    private ArrayList<Member> members = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        shareBtn = (Button) findViewById(R.id.button6);
        shareBtn.setOnClickListener(startListener);

        textView = findViewById(R.id.textView);


        queue = Volley.newRequestQueue(this);
        getMembers();


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new MemberAdapter(members, this);
        recyclerView.setAdapter(mAdapter);

    }

    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculate();
        }
    };


    public void calculate() {
        loadResult();
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
            Meetup meetup = gson.fromJson(response.toString(), Meetup.class);

            //TODO Proceed to map
        }, error -> {
            textView.setText("No Overlap found");
            Log.d("LOG", "createUser: " + error.toString());
        }) {
        };

        queue.add(jsonObjectRequest);
    }


    public void getMembers() {
        JSONObject payload = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2:8000/room/" + id, payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
            Gson gson = new Gson();
            JSONArray jsonArray = response.optJSONArray("members");
            for (int n = 0; n < jsonArray.length(); n++) {
                Member member = new Member();
                try {
                    member.setId(jsonArray.getInt(n));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                members.add(member);
                mAdapter.notifyDataSetChanged();
            }


        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {
        };

        queue.add(jsonObjectRequest);
    }


    public void loadResult() {
        recyclerView.setVisibility(View.INVISIBLE);
        textView.setText("LOADING");

    }
}
