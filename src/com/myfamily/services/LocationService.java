package com.myfamily.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends IntentService {
   boolean gotLocation;
   String from = null;
   Location loc;
   
   public LocationService() {
      super("LocationService");
   }

   @Override
   public void onCreate() {
      // TODO Auto-generated method stub
      super.onCreate();
      Toast.makeText(getApplicationContext(), "Starting location service!", Toast.LENGTH_LONG).show();
      
      final LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
      LocationListener listener = new LocationListener() {

         @Override
         public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onLocationChanged(Location location) {
            loc = location;
            gotLocation = true;
            lm.removeUpdates(this);
         }
      };
      
      //lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 10, listener);
      //lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
      lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, listener);      
   }
   
   @Override
   protected void onHandleIntent(Intent intent) {
      from = intent.getStringExtra("from");
      
      // wait for location
      while (!gotLocation);
      
      // got location, send it off as SMS
      sendLocationSms(from, loc);
   }
   
   @Override
   public void onDestroy() {
      Toast.makeText(getApplicationContext(), "Location service done!", Toast.LENGTH_LONG).show();
      super.onDestroy();
   }
   
   private void sendLocationSms(String to, Location location) {
      Log.d("Location", "Sending location SMS " + location.toString());
   }
}
