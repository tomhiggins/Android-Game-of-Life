package de.deepsource.agol.rules.editor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import de.deepsource.agol.Agol;
import de.deepsource.agol.R;
import de.deepsource.agol.database.RuleSet;
import de.deepsource.agol.database.RuleSetDataSource;

public class RuleEditorActivity extends Activity {
	
	private RuleSetDataSource datasource;
	
	private List<RadioGroup> buttons;
	
	private int[] gameRule = new int[9];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ruleeditor);
		
		datasource = new RuleSetDataSource(this);
		
		buttons = new ArrayList<RadioGroup>();
		buttons.add((RadioGroup) findViewById(R.id.radioGroup0));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup1));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup2));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup3));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup4));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup5));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup6));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup7));
		buttons.add((RadioGroup) findViewById(R.id.radioGroup8));
		
		// set initial state and listeners
		for (int i = 0; i < buttons.size(); i++) {
			
			// add listeners
			RadioGroup group = buttons.get(i);
			for (int j = 0; j < group.getChildCount(); j++) {
				RadioButton button = (RadioButton) group.getChildAt(j);
				final int finalI = i;
				final int finalJ = j;
				
				button.setOnClickListener(new OnClickListener() {	
					
					@Override
					public void onClick(View v) {
						if (finalJ == 0) {
							gameRule[finalI] = Agol.BIRTH_RULE;
						} else if (finalJ == 1) {
							gameRule[finalI] = Agol.DEATH_RULE;
						} else {
							gameRule[finalI] = Agol.UNDEFINED;
						}
						
					}
				});
			}
		}
		
		updateRadioButtons();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ruleeditormenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.load:
			loadRuleSets();
			break;
			
		case R.id.save:
			saveRuleSet();
			break;
		
		case R.id.delete:
			deleteRuleSet();
			break;
		}
		
		return true;
	}
	
	private void deleteRuleSet() {
		datasource.open();

		final List<RuleSet> values = datasource.getAllRulesets();
		int size = values.size();
		final CharSequence[] items = new CharSequence[size];

		for (int i = 0; i < size; i++) {
			items[i] = values.get(i).getName();
		}
		
		final Context context = this;

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setTitle(getString(R.string.pick_ruleset));
		builder.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
				builder2.setMessage(getString(R.string.deleted_ruleset_warning) + " " + items[which] + "?")
					.setCancelable(true)
					.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							datasource.deleteRuleset(values.get(which));
							Toast.makeText(getApplicationContext(), getString(R.string.deleted_ruleset) + items[which],
									Toast.LENGTH_SHORT).show();
						}
					})
					.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
				AlertDialog alert2 = builder2.create();
				alert2.show();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

	private void saveRuleSet() {
		TextView name = (TextView) findViewById(R.id.editTextName);
		if (name.getText().length() > 0) {
			RuleSet ruleSet = new RuleSet();
			ruleSet.setName(name.getText().toString());
			ruleSet.setRuleSet(gameRule);
			
			datasource.open();
			datasource.createRuleset(ruleSet);
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(getString(R.string.save_warning))
				.setCancelable(true)
				.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
	
	private void loadRuleSets() {
		datasource.open();
		
		final List<RuleSet> values = datasource.getAllRulesets();
		int size = values.size();
		final CharSequence[] items = new CharSequence[size];
		
		for (int i = 0; i < size; i++) {
			items[i] = values.get(i).getName();
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(getString(R.string.pick_ruleset));
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Agol.setRuleSet(values.get(which).getRuleSet());
				updateRadioButtons();
				Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void updateRadioButtons() {
		gameRule = Agol.getRuleSet();
		RadioButton temp;
		
		for (int i = 0; i < buttons.size(); i++) {
			temp = (RadioButton) buttons.get(i).getChildAt(0);
			int rule = gameRule[i];
			
			if (rule == Agol.DEATH_RULE) {
				temp = (RadioButton) buttons.get(i).getChildAt(1);
			} else if (rule == Agol.UNDEFINED) {
				temp = (RadioButton) buttons.get(i).getChildAt(2);
			}
			
			temp.toggle();
		}
	}
	
	@Override
	protected void onPause() {
		datasource.close();
		Agol.setRuleSet(gameRule);
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
}
