package de.deepsource.agol.database;

import de.deepsource.agol.Agol;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_RULES = "rules";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_N0 = "n0";
	public static final String COLUMN_N1 = "n1";
	public static final String COLUMN_N2 = "n2";
	public static final String COLUMN_N3 = "n3";
	public static final String COLUMN_N4 = "n4";
	public static final String COLUMN_N5 = "n5";
	public static final String COLUMN_N6 = "n6";
	public static final String COLUMN_N7 = "n7";
	public static final String COLUMN_N8 = "n8";
	
	private static final String DATABASE_NAME = "rules.db";
	private static final int DATABASE_VERSION = 1;
	
	// sql statement to create database
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_RULES + "( " 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_NAME + " text not null, "
			+ COLUMN_N0 + " integer not null, "
			+ COLUMN_N1 + " integer not null, "
			+ COLUMN_N2 + " integer not null, "
			+ COLUMN_N3 + " integer not null, "
			+ COLUMN_N4 + " integer not null, "
			+ COLUMN_N5 + " integer not null, "
			+ COLUMN_N6 + " integer not null, "
			+ COLUMN_N7 + " integer not null, "
			+ COLUMN_N8 + " integer not null);";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);

		// Fill with core data
		ContentValues values = new ContentValues();
		
		// Conway
		values.put(MySQLiteHelper.COLUMN_NAME, "Conway");
		values.put(MySQLiteHelper.COLUMN_N0, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N1, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N2, Agol.UNDEFINED);
		values.put(MySQLiteHelper.COLUMN_N3, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N4, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N5, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N6, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N7, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N8, Agol.DEATH_RULE);
		submitToDb(database, values);
		
		// AntiConway
		values.put(MySQLiteHelper.COLUMN_NAME, "AntiConway");
		values.put(MySQLiteHelper.COLUMN_N0, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N1, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N2, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N3, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N4, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N5, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N6, Agol.UNDEFINED);
		values.put(MySQLiteHelper.COLUMN_N7, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N8, Agol.BIRTH_RULE);
		submitToDb(database, values);
		
		// Shuffle
		values.put(MySQLiteHelper.COLUMN_NAME, "Shuffle");
		values.put(MySQLiteHelper.COLUMN_N0, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N1, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N2, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N3, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N4, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N5, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N6, Agol.DEATH_RULE);
		values.put(MySQLiteHelper.COLUMN_N7, Agol.BIRTH_RULE);
		values.put(MySQLiteHelper.COLUMN_N8, Agol.DEATH_RULE);
		submitToDb(database, values);
	}
	
	private void submitToDb(SQLiteDatabase database, ContentValues values) {
		long id = database.insert(MySQLiteHelper.TABLE_RULES, null, values);
		
		if (id == -1) {
			// TODO: add global
			Log.e("Agol", "Could not insert core data into database!");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int vOld, int vNew) {
		database.execSQL("DROP TABLE IF EXISTS" + TABLE_RULES);
		onCreate(database);
	}

}
