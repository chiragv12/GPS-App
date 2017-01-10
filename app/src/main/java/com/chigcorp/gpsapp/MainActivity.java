package com.chigcorp.gpsapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener{
    TextView longitude;
    TextView latitude;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longitude = (TextView)findViewById(R.id.textView_long);
        latitude = (TextView)findViewById(R.id.textView_lat);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
            }
            catch(SecurityException e){
                e.printStackTrace();
            }


    }


    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onLocationChanged(Location location) {
        longitude.setText(Double.toString(location.getLongitude()));
        latitude.setText(Double.toString(location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }
}
