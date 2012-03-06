package de.deepsource.agol.database;

import java.util.ArrayList;
import java.util.List;

import de.deepsource.agol.Agol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Presents a data access object, that will handle database connections.
 * Through instances of this class one can access and modify the rules
 * stored in the database.
 * 
 * @author Jan Pretzel (jan.pretzel@deepsource.de)
 */
public class RuleSetDataSource {
	
	/**
	 * Provides methods to open, query, update and close the database.
	 */
	private SQLiteDatabase database;
	
	/**
	 * Helps to create and update the database layout.
	 */
	private AgolSQLiteHelper dbHelper;
	
	/**
	 * Represents all columns of our database table.
	 */
	private String[] allColums = { AgolSQLiteHelper.COLUMN_ID,
			AgolSQLiteHelper.COLUMN_NAME, AgolSQLiteHelper.COLUMN_N0,
			AgolSQLiteHelper.COLUMN_N1, AgolSQLiteHelper.COLUMN_N2,
			AgolSQLiteHelper.COLUMN_N3, AgolSQLiteHelper.COLUMN_N4,
			AgolSQLiteHelper.COLUMN_N5, AgolSQLiteHelper.COLUMN_N6,
			AgolSQLiteHelper.COLUMN_N7, AgolSQLiteHelper.COLUMN_N8 };
	
	/**
	 * The constructor instantiates {@link RuleSetDataSource#dbHelper}.
	 * 
	 * @param context The context in which the object is instantiated.
	 */
	public RuleSetDataSource(Context context) {
		dbHelper = new AgolSQLiteHelper(context);
	}
	
	/**
	 * Opens a connection by giving a assigning a writable database to {@link RuleSetDataSource#database}.
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Closes any open database object.
	 */
	public void close() {
		dbHelper.close();
	}
	
	/**
	 * Saves a given {@link RuleSet} object to the database given by {@link RuleSetDataSource#database}.
	 * 
	 * @param ruleset The RuleSet to be stored in the database.
	 */
	public void createRuleset(RuleSet ruleset) {
		ContentValues values = new ContentValues();
		values.put(AgolSQLiteHelper.COLUMN_NAME, ruleset.getName());
		int[] rules = ruleset.getRuleSet();
		values.put(AgolSQLiteHelper.COLUMN_N0, rules[0]);
		values.put(AgolSQLiteHelper.COLUMN_N1, rules[1]);
		values.put(AgolSQLiteHelper.COLUMN_N2, rules[2]);
		values.put(AgolSQLiteHelper.COLUMN_N3, rules[3]);
		values.put(AgolSQLiteHelper.COLUMN_N4, rules[4]);
		values.put(AgolSQLiteHelper.COLUMN_N5, rules[5]);
		values.put(AgolSQLiteHelper.COLUMN_N6, rules[6]);
		values.put(AgolSQLiteHelper.COLUMN_N7, rules[7]);
		values.put(AgolSQLiteHelper.COLUMN_N8, rules[8]);
		long id = database.insert(AgolSQLiteHelper.TABLE_RULES, null, values);
		
		if (id == -1) {
			Log.w(Agol.APP_NAME, "Could not insert rule into database!");
		}
	}
	
	/**
	 * Gets all rules stored in the database, the database is 
	 * presented by {@link RuleSetDataSource#database}.
	 * 
	 * @return All rules as {@link RuleSet} objects in one ArrayList.
	 */
	public List<RuleSet> getAllRulesets() {
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		Cursor cursor = database.query(AgolSQLiteHelper.TABLE_RULES, allColums, null, null, null, null, null);
		
		// cursor will be in front of the first entry so we 
		// need to move it to the first item
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			RuleSet ruleSet = cursorToRuleSet(cursor);
			ruleSets.add(ruleSet);
			cursor.moveToNext();
		}
		
		cursor.close();
		return ruleSets;
	}
	
	/**
	 * Deletes a rule of the database, the database is
	 * presented by {@link RuleSetDataSource#database}.
	 * 
	 * @param ruleSet The rule that will be deleted presented as RuleSet.
	 */
	public void deleteRuleset(RuleSet ruleSet) {
		long id = ruleSet.getId();
		database.delete(AgolSQLiteHelper.TABLE_RULES, AgolSQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	/**
	 * Writes a cursor object to {@link RuleSet} object.
	 * 
	 * @param cursor The cursor presenting one specific row of a query.
	 * @return The {@link RuleSet} object derived from the cursor.
	 */
	private RuleSet cursorToRuleSet(Cursor cursor) {
		RuleSet ruleSet = new RuleSet();
		ruleSet.setId(cursor.getLong(0));
		ruleSet.setName(cursor.getString(1));
		int[] rules = new int[9];
		
		for (int i = 0; i < rules.length; i++) {
			rules[i] = cursor.getInt(i + 2); 
		}
		
		ruleSet.setRuleSet(rules);
		
		return ruleSet;
	}

}
