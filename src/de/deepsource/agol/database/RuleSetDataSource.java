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

public class RuleSetDataSource {
	private SQLiteDatabase database;
	private AgolSQLiteHelper dbHelper;
	private String[] allColums = { AgolSQLiteHelper.COLUMN_ID,
			AgolSQLiteHelper.COLUMN_NAME, AgolSQLiteHelper.COLUMN_N0,
			AgolSQLiteHelper.COLUMN_N1, AgolSQLiteHelper.COLUMN_N2,
			AgolSQLiteHelper.COLUMN_N3, AgolSQLiteHelper.COLUMN_N4,
			AgolSQLiteHelper.COLUMN_N5, AgolSQLiteHelper.COLUMN_N6,
			AgolSQLiteHelper.COLUMN_N7, AgolSQLiteHelper.COLUMN_N8 };
	
	public RuleSetDataSource(Context context) {
		dbHelper = new AgolSQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
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
			Log.w(Agol.APP_NAME, "Could not insert core data into database!");
		}
	}
	
public List<RuleSet> getAllRulesets() {
	List<RuleSet> ruleSets = new ArrayList<RuleSet>();
	Cursor cursor = database.query(
			AgolSQLiteHelper.TABLE_RULES, 
			allColums, 
			null, null, 
			null, null, null);
	
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
	
	public void deleteRuleset(RuleSet ruleSet) {
		long id = ruleSet.getId();
		database.delete(AgolSQLiteHelper.TABLE_RULES, AgolSQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
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
