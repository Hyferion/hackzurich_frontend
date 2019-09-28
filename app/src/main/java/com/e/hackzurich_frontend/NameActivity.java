package com.e.hackzurich_frontend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.hackzurich_frontend.Rooms.RoomOverviewActivity;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class NameActivity extends AppCompatActivity {
    public SharedPreferences myPreferences;


    private EditText editText;
    private TextView textView;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        queue = Volley.newRequestQueue(this);


        final SharedPreferences myPreferences = getSharedPreferences("united",
                Context.MODE_PRIVATE);

        editText = (EditText) findViewById(R.id.editUsername);
        textView = (TextView) findViewById(R.id.textView);


        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    SharedPreferences.Editor ed = myPreferences.edit();
                    ed.putString("name", editText.getText().toString());
                    ed.commit();

                    // TODO: send username, get user id



                    redirectToOverview();

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
}
