package com.e.hackzurich_frontend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.e.hackzurich_frontend.Rooms.RoomActivity;

public class JoinRoomActivity extends AppCompatActivity {
    EditText editText;

    private EditText editRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        editRoom = (EditText) findViewById(R.id.editRoomNumber);

        editRoom.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    //Todo: Join room request and wait for answer and ID

                    // Start room activity
                    Intent intent = new Intent(JoinRoomActivity.this, RoomActivity.class);
                    intent.putExtra("id", editRoom.getText().toString());
                    startActivity(intent);


                    return true;
                }
                return false;
            }
        });

    }

}
