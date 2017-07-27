package com.smarttab.google_map;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Input_Location extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final float DEFAULTZOOM =15 ;
    private GoogleMap mMap;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input__location);

        Button btn=(Button)findViewById(R.id.btn_find);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btn.setOnClickListener(this);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View v) {
        EditText editText=(EditText)findViewById(R.id.et_location);
        String Location=editText.getText().toString();

        Geocoder gc=new Geocoder(this);
        try {
            List<Address> list=gc.getFromLocationName(Location,1);
            Address address=list.get(0);
            String locality=address.getLocality();
            Toast.makeText(this, "Locality:-->"+locality, Toast.LENGTH_SHORT).show();
            double lat=address.getLatitude();
            double lon=address.getLongitude();

            gotoLocation(lat,lon,DEFAULTZOOM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gotoLocation(double lat, double lon, float defaultzoom) {

        LatLng latLng=new LatLng(lat,lon);
        CameraUpdate update=CameraUpdateFactory.newLatLngZoom(latLng,defaultzoom);
        mMap.moveCamera(update);

        if(marker!=null)
        {
            marker.remove();
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        marker=mMap.addMarker(markerOptions);

    }
}
