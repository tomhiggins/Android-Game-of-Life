package de.deepsource.agol.rules.editor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import de.deepsource.agol.Agol;
import de.deepsource.agol.R;

public class RuleEditorActivity extends Activity {
	
	private List<RadioGroup> buttons;
	
	private final int[] gameRule = Agol.getGameRule();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ruleeditor);
		
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
			RadioButton birth = (RadioButton) buttons.get(i).getChildAt(0);
			RadioButton death = (RadioButton) buttons.get(i).getChildAt(1);
			final int finalI = i;
			
			birth.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					gameRule[finalI] = Agol.BIRTH_RULE;
				}
			});
			
			if (gameRule[i] == Agol.BIRTH_RULE) {
				birth.toggle();
			} else {
				death.toggle();
			}
		}
	}
	
	@Override
	protected void onPause() {
		Agol.setGameRule(gameRule);
		super.onPause();
	}
}
