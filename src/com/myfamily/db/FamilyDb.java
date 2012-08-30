package com.myfamily.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myfamily.db.helper.FamilyDbHelper;
import com.myfamily.db.model.FamilyMember;

public class FamilyDb {

   // Database fields
   private SQLiteDatabase database;
   private FamilyDbHelper dbHelper;
   private String[] allColumns = { 
            FamilyDbHelper.COLUMN_ID, 
            FamilyDbHelper.COLUMN_CONTACT_ID, 
            FamilyDbHelper.COLUMN_NAME 
   };

   public FamilyDb(Context context) {
      dbHelper = new FamilyDbHelper(context);
      database = dbHelper.getWritableDatabase();               
   }
   
   public long addFamilyMember(String name, long contactId) {
      ContentValues cv = new ContentValues();
      cv.put(FamilyDbHelper.COLUMN_CONTACT_ID, contactId);
      cv.put(FamilyDbHelper.COLUMN_NAME, name);
      long ret = database.insert(FamilyDbHelper.TABLE_FAMILY, "", cv);
      database.close();
      return ret;
   }
   
   public List<FamilyMember> getFamilyMembers() {
      List<FamilyMember> familyMembers = new ArrayList<FamilyMember>();

      Cursor cursor = database.query(FamilyDbHelper.TABLE_FAMILY,
          allColumns, null, null, null, null, null);

      cursor.moveToFirst();
      while (!cursor.isAfterLast()) {
        FamilyMember family = familyMember(cursor);
        familyMembers.add(family);
        cursor.moveToNext();
      }
      
      // Make sure to close the cursor
      cursor.close();
      database.close();
      return familyMembers;
   }
   
   private FamilyMember familyMember(Cursor c) {
      FamilyMember ret = new FamilyMember(-1, -1, null);
      ret.setId(c.getLong(c.getColumnIndex(FamilyDbHelper.COLUMN_ID)));
      ret.setContactId(c.getLong(c.getColumnIndex(FamilyDbHelper.COLUMN_CONTACT_ID)));
      ret.setName(c.getString(c.getColumnIndex(FamilyDbHelper.COLUMN_NAME)));
      return ret;
   }
}
