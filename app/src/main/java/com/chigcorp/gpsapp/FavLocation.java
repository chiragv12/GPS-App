package com.chigcorp.gpsapp;

import android.location.Address;
import android.location.Location;

/**
 * Created by Chirag on 1/12/17.
 */
public class FavLocation {
    Address location;
    long elapsedTime;

    public FavLocation(Address loc, long time){
        location = loc;
        elapsedTime = time;
    }

    public long getElapsedTime(){
        return elapsedTime;
    }

    public Address getLocation(){
        return location;
    }

    public void setElapsedTime(long time){
        elapsedTime = time;
    }
}
