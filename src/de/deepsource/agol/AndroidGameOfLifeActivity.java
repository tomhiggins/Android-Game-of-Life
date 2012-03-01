package de.deepsource.agol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import de.deepsource.agol.rules.Conway;
import de.deepsource.agol.rules.editor.RuleEditorActivity;

/**
 * @author Sebastian Ullrich
 * 
 * Seriously, No one cares about activities!
 */
public class AndroidGameOfLifeActivity extends Activity {

	private AndroidGameOfLifeView agolView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// get device width and height BEFORE initializing DrawViews
		final Display display = getWindowManager().getDefaultDisplay();
		Agol.setViewportWidth(display.getWidth());
		Agol.setViewportHeight(display.getHeight());
		
		setContentView(R.layout.main);
		
		agolView = (AndroidGameOfLifeView) findViewById(R.id.androidGameOfLifeView1);

		agolView = new AndroidGameOfLifeView(this);
		agolView.setBackgroundColor(Color.BLACK);
		setContentView(agolView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			agolView.lock();
			agolView.runLoop(new Conway());
			break;

		case R.id.item2:
			agolView.unlock();
			break;
			
		case R.id.item3:
			final Intent intent = new Intent(
					getApplicationContext(),
					RuleEditorActivity.class);

			startActivity(intent);
			break;
		}
		return true;
	}
}
