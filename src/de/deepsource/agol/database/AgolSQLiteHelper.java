package de.deepsource.agol.database;

import de.deepsource.agol.Agol;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Creates and upgrades the database for our rules.
 * 
 * @author Jan Pretzel (jan.pretzel@deepsource.de)
 */
public class AgolSQLiteHelper extends SQLiteOpenHelper {
	
	/**
	 * The table name.
	 */
	public static final String TABLE_RULES = "rules";
	
	/**
	 * Name of the id column.
	 */
	public static final String COLUMN_ID = "_id";
	
	/**
	 * Name of the column for the rule name.
	 */
	public static final String COLUMN_NAME = "name";
	
	/**
	 * Name of the column for the n0 rule.
	 */
	public static final String COLUMN_N0 = "n0";
	
	/**
	 * Name of the column for the n1 rule.
	 */
	public static final String COLUMN_N1 = "n1";
	
	/**
	 * Name of the column for the n2 rule.
	 */
	public static final String COLUMN_N2 = "n2";
	
	/**
	 * Name of the column for the n3 rule.
	 */
	public static final String COLUMN_N3 = "n3";
	
	/**
	 * Name of the column for the n4 rule.
	 */
	public static final String COLUMN_N4 = "n4";
	
	/**
	 * Name of the column for the n5 rule.
	 */
	public static final String COLUMN_N5 = "n5";
	
	/**
	 * Name of the column for the n6 rule.
	 */
	public static final String COLUMN_N6 = "n6";
	
	/**
	 * Name of the column for the n7 rule.
	 */
	public static final String COLUMN_N7 = "n7";
	
	/**
	 * Name of the column for the n8 rule.
	 */
	public static final String COLUMN_N8 = "n8";
	
	/**
	 * The name of the database.
	 */
	private static final String DATABASE_NAME = "rules.db";
	
	/**
	 * The Version of the database.
	 */
	private static final int DATABASE_VERSION = 1;
	
	/**
	 * The SQL statement to create the database.
	 */
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
	
	/**
	 * The constructor calls the constructor of SQLiteOpenHelper.
	 * 
	 * @param context The context in which the object is instantiated.
	 */
	public AgolSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);

		// Fill with core data
		ContentValues values = new ContentValues();
		
		// Conway
		values.put(AgolSQLiteHelper.COLUMN_NAME, "Conway");
		values.put(AgolSQLiteHelper.COLUMN_N0, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N1, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N2, Agol.UNDEFINED);
		values.put(AgolSQLiteHelper.COLUMN_N3, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N4, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N5, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N6, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N7, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N8, Agol.DEATH_RULE);
		submitToDb(database, values);
		
		// AntiConway
		values.put(AgolSQLiteHelper.COLUMN_NAME, "AntiConway");
		values.put(AgolSQLiteHelper.COLUMN_N0, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N1, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N2, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N3, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N4, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N5, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N6, Agol.UNDEFINED);
		values.put(AgolSQLiteHelper.COLUMN_N7, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N8, Agol.BIRTH_RULE);
		submitToDb(database, values);
		
		// Shuffle
		values.put(AgolSQLiteHelper.COLUMN_NAME, "Shuffle");
		values.put(AgolSQLiteHelper.COLUMN_N0, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N1, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N2, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N3, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N4, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N5, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N6, Agol.DEATH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N7, Agol.BIRTH_RULE);
		values.put(AgolSQLiteHelper.COLUMN_N8, Agol.DEATH_RULE);
		submitToDb(database, values);
	}
	
	/**
	 * Inserts a row into the database.
	 * 
	 * @param database The database to which we will submit.
	 * @param values The values of the row, that will be inserted.
	 */
	private void submitToDb(SQLiteDatabase database, ContentValues values) {
		long id = database.insert(AgolSQLiteHelper.TABLE_RULES, null, values);
		
		if (id == -1) {
			Log.w(Agol.APP_NAME, "Could not insert core data into database!");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int vOld, int vNew) {
		// 
		database.execSQL("DROP TABLE IF EXISTS" + TABLE_RULES);
		onCreate(database);
	}

}
