package com.myfamily.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FamilyDbHelper extends SQLiteOpenHelper {
   public static final String TABLE_FAMILY = "family";
   public static final String COLUMN_ID = "_id";
   public static final String COLUMN_CONTACT_ID = "contact_id";
   public static final String COLUMN_NAME = "name";

   private static final String DATABASE_NAME = "family.db";
   private static final int DATABASE_VERSION = 1;
   
   // Database creation sql statement
   private static final String DATABASE_CREATE = "create table "
       + TABLE_FAMILY + "(" 
       + COLUMN_ID + " integer primary key autoincrement, " 
       + COLUMN_CONTACT_ID + " integer not null, " 
       + COLUMN_NAME + " text not null);";   
   
   public FamilyDbHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase database) {
     database.execSQL(DATABASE_CREATE);
   }   
   
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     // do upgrades here
   }    

}
