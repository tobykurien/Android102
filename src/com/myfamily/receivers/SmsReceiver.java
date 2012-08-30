package com.myfamily.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;

import com.myfamily.PersonDetailFragment;
import com.myfamily.R;
import com.myfamily.services.LocationService;

public class SmsReceiver extends BroadcastReceiver {
   private static final int LOCATION_ID = 1;

   @Override
   public void onReceive(Context context, Intent intent) {
      Bundle extras = intent.getExtras();

      Object[] pdus = (Object[]) extras.get("pdus");
      for (Object pdu : pdus) {
         SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);

         String origin = msg.getOriginatingAddress();
         String body = msg.getMessageBody();

         // Parse the SMS body
         if (body.trim().equalsIgnoreCase(PersonDetailFragment.LOCATION_SPECIAL_SMS)) {
            // Stop it being passed to the main Messaging inbox
            abortBroadcast();            
            notifyUser(context, "Location requested from " + origin);
            
            // start location service
            Intent i = new Intent(context, LocationService.class);
            i.putExtra("from", origin);
            context.startService(i);
         }
      }
   }
   
   private void notifyUser(Context context, String message) {
      NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
      
      int icon = R.drawable.ic_launcher;
      long when = System.currentTimeMillis();
      Notification notification = new Notification(icon, message, when);
      notification.setLatestEventInfo(context, "MyFamily", message, null);
      
      nm.notify(LOCATION_ID, notification);
   }
}
