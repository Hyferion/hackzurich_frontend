package com.e.hackzurich_frontend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.Rooms.RoomOverviewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class NameActivity extends AppCompatActivity {


    private SharedPreferences myPreferences;
    private EditText editText;
    private TextView textView;
    private RequestQueue queue;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        queue = Volley.newRequestQueue(this);


        myPreferences = getSharedPreferences("united",
                Context.MODE_PRIVATE);

        editText = (EditText) findViewById(R.id.editUsername);
        textView = (TextView) findViewById(R.id.textView);


        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    editor = myPreferences.edit();
                    editor.putString("name", editText.getText().toString());
                    editor.commit();

                    createUser();


                    return true;
                }
                return false;
            }
        });
    }

    public void redirectToOverview() {
        Intent intent = new Intent(NameActivity.this, RoomOverviewActivity.class);
        startActivity(intent);
    }


    public void createUser() {
        JSONObject payload = new JSONObject();
        try {
            payload.put("name", editText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8000/user", payload, response -> {
            Log.d("TAG", "onCreate: " + response.toString());
            try {
                editor.putInt("id", response.getInt("id"));
                editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            redirectToOverview();
        }, error -> {
            Log.d("LOG", "createUser: " + error.toString());
        }) {
        };

        queue.add(jsonObjectRequest);
    }
}
