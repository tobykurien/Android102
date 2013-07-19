package com.myfamily.db;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import asia.sonix.android.orm.AbatisService;

import com.myfamily.R;

public class FamilyDb extends AbatisService {
	public FamilyDb(Context context) {
		super(context, "family", 1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do upgrades here
	}

	public List<FamilyMember> getFamilyMembers() {
		return executeForBeanList(R.string.getMembers, null, FamilyMember.class);
	}

	public long addFamilyMember(String name, long contactId) {
		ContentValues cv = new ContentValues();
		cv.put("contactId", contactId);
		cv.put("name", name);
		SQLiteDatabase database = getWritableDatabase();
		long ret = database.insert("family", "", cv);
		database.close();
		return ret;
	}
}
