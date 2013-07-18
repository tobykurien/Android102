package com.myfamily;

import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myfamily.db.FamilyDb;
import com.myfamily.db.FamilyMember;

public class PersonDetailFragment extends Fragment {
   public static final String ARG_ITEM_ID = "item_id";
   public static final String LOCATION_SPECIAL_SMS = "MYFAMILY - REQUESTING LOCATION";

   FamilyMember mItem;

   public PersonDetailFragment() {
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (getArguments().containsKey(ARG_ITEM_ID)) {
         List<FamilyMember> family = new FamilyDb(getActivity()).getFamilyMembers();
         int position = Integer.parseInt(getArguments().getString(ARG_ITEM_ID));
         mItem = family.get(position - 1);
      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_person_detail, container, false);
      if (mItem != null) {
         ((TextView) rootView.findViewById(R.id.person_detail)).setText(mItem.getName());
      }

      // set up handlers
      Button bCall = (Button) rootView.findViewById(R.id.btn_call);
      bCall.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View arg0) {
            String number = getNumber(mItem.getContactId());
            if (number != null) {
               Intent i = new Intent(Intent.ACTION_CALL);
               i.setData(Uri.parse("tel:" + number));
               startActivity(i);
            }
         }
      });

      Button bLocate = (Button) rootView.findViewById(R.id.btn_locate);
      bLocate.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View arg0) {
            String number = getNumber(mItem.getContactId());
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number, 
                     null, 
                     LOCATION_SPECIAL_SMS, 
                     null, 
                     null);
            Toast.makeText(getActivity(), "Location request sent", Toast.LENGTH_LONG).show();
         }
      });
      
      
      return rootView;
   }

   private String getNumber(long contactId) {
      String number = null;
      Cursor c = getActivity().getContentResolver().query(CommonDataKinds.Phone.CONTENT_URI, null,
               CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { String.valueOf(mItem.getContactId()) }, null);

      if (c.moveToNext()) {
         number = c.getString(c.getColumnIndex(CommonDataKinds.Phone.NUMBER));
      }
      c.close();

      return number;
   }
}
