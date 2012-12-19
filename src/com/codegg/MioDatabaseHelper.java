package com.codegg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MioDatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "CallMe";
	private static final int DB_VERSION = 1;

	public MioDatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "";
		sql += "CREATE TABLE history (";
		sql += "  _id INTEGER PRIMARY KEY,";
		sql += "  url TEXT NOT NULL";
		sql += ")";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
