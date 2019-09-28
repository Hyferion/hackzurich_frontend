package com.e.hackzurich_frontend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.e.hackzurich_frontend.Rooms.RoomActivity;
import com.e.hackzurich_frontend.Rooms.RoomOverviewActivity;

import org.w3c.dom.Text;

public class NameActivity extends AppCompatActivity {
    public SharedPreferences myPreferences;



    Button nextBtn;


    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        final SharedPreferences myPreferences = getSharedPreferences("mypreference",
                Context.MODE_PRIVATE);

        final EditText editText = (EditText) findViewById(R.id.editUsername);

        nextBtn = (Button) findViewById(R.id.buttonUser);
        nextBtn.setOnClickListener(startListener);

        //final TextView textView = (TextView) findViewById(R.id.textView);



        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    SharedPreferences.Editor ed = myPreferences.edit();
                    ed.putString("Name", editText.getText().toString());
                    ed.commit();

                    // send username, get user id

                    Intent intent = new Intent(NameActivity.this, RoomActivity.class);
                    startActivity(intent);


                    return true;
                }
                return false;
            }
        });
    }
    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences myPreferences = getSharedPreferences("mypreference",
                    Context.MODE_PRIVATE);

            final EditText editText = (EditText) findViewById(R.id.editUsername);
            SharedPreferences.Editor ed = myPreferences.edit();
            ed.putString("Name", editText.getText().toString());
            ed.commit();

            // send username, get user id

            Intent intent = new Intent(NameActivity.this, RoomOverviewActivity.class);
            startActivity(intent);

        }
    };


}
