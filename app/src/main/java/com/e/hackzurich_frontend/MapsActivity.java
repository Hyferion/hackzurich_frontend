package com.e.hackzurich_frontend;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    //Button routeBtn;

    private GoogleMap mMap;
    private Float lng;
    private Float lat;
    private String name;
    private String type;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        textView = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        lat = intent.getFloatExtra("lat",0);
        lng = intent.getFloatExtra("lng",0);
        name = intent.getStringExtra("name");
        type = intent.getStringExtra("type");

        textView.setText(name+" "+type);
        //Log.d("", "onMapReady: " + lat.toString() + lng.toString());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //routeBtn = (Button) findViewById(R.id.button7);
        //routeBtn.setOnClickListener(startListener);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Log.d("", "onMapReady: " + lat.toString() + lng.toString());
        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(location).title("Your target"));
        mMap.setMinZoomPreference(15);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    private View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
            startActivity(intent);
        }
    };

}
