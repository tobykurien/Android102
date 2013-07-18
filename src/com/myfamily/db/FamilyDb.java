package com.myfamily.db;

import java.util.HashMap;
import java.util.List;

import com.myfamily.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import asia.sonix.android.orm.AbatisService;

public class FamilyDb extends AbatisService {
	public FamilyDb(Context context) {
		super(context, "family");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do upgrades here
	}

	public List<FamilyMember> getFamilyMembers() {
		return executeForBeanList(R.string.getMembers, null, FamilyMember.class);
	}

	public void addFamilyMember(String name, long contactId) {
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("contactId", contactId);
		params.put("name", name);
		execute(R.string.addMember, params);
	}
}
