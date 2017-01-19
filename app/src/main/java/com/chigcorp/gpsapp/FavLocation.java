package com.chigcorp.gpsapp;

import android.location.Address;
import android.location.Location;

/**
 * Created by Chirag on 1/12/17.
 */
public class FavLocation {
    Location location;
    long elapsedTime;

    public FavLocation(){

    }

    public FavLocation(Location loc, long time){
        location = loc;
        elapsedTime = time;
    }

    public long getElapsedTime(){
        return elapsedTime;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location loc){
        location = loc;
    }

    public void setElapsedTime(long time){
        elapsedTime = time;
    }
}
