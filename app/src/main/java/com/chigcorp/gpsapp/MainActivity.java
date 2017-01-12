package com.chigcorp.gpsapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener{
    List<Address> list;
    Geocoder geocoder;
    TextView longitude;
    TextView latitude;
    TextView address;
    Location previousLocation;
    TextView dist;
    TextView time;
    TextView favLoc;
    ArrayList<FavLocation> locList;
    float distSum;
    long initialTime;
    long currentTime;

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
        address = (TextView)findViewById(R.id.textView_address);
        dist = (TextView)findViewById(R.id.textView_dist);
        time = (TextView)findViewById(R.id.textView_time);
        favLoc = (TextView)findViewById(R.id.textView_favloc);
        geocoder = new Geocoder(this, Locale.US);

        initialTime = System.currentTimeMillis();



        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, MainActivity.this);
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
        if(previousLocation != null && (SystemClock.elapsedRealtime() >= initialTime + 10000)) {
            float distance = location.distanceTo(previousLocation);
            distSum += distance;
            dist.setText("Distance: "+(int)(distSum));
        }
        longitude.setText(Double.toString(location.getLongitude()));
        latitude.setText(Double.toString(location.getLatitude()));
        try {
            list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            address.setText(list.get(0).getAddressLine(0));
        }catch (IOException e){
        }
        catch (Exception e){
            e.printStackTrace();
        }

        previousLocation = location;
        currentTime = System.currentTimeMillis();
        time.setText("Time: " + ((currentTime - initialTime)/1000));



    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }
}
