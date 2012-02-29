package de.deepsource.agol.database;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RuleSetDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColums = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_N0,
			MySQLiteHelper.COLUMN_N1, MySQLiteHelper.COLUMN_N2,
			MySQLiteHelper.COLUMN_N3, MySQLiteHelper.COLUMN_N4,
			MySQLiteHelper.COLUMN_N5, MySQLiteHelper.COLUMN_N6,
			MySQLiteHelper.COLUMN_N7, MySQLiteHelper.COLUMN_N8 };
	
	public RuleSetDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public void createRuleset(RuleSet ruleset) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, ruleset.getName());
		int[] rules = ruleset.getRuleSet();
		values.put(MySQLiteHelper.COLUMN_N0, rules[0]);
		values.put(MySQLiteHelper.COLUMN_N1, rules[1]);
		values.put(MySQLiteHelper.COLUMN_N2, rules[2]);
		values.put(MySQLiteHelper.COLUMN_N3, rules[3]);
		values.put(MySQLiteHelper.COLUMN_N4, rules[4]);
		values.put(MySQLiteHelper.COLUMN_N5, rules[5]);
		values.put(MySQLiteHelper.COLUMN_N6, rules[6]);
		values.put(MySQLiteHelper.COLUMN_N7, rules[7]);
		values.put(MySQLiteHelper.COLUMN_N8, rules[8]);
		long id = database.insert(MySQLiteHelper.TABLE_RULES, null, values);
	}
	
	public List<RuleSet> getAllRulesets() {
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_RULES, allColums, null, null, null, null, null);
		
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
