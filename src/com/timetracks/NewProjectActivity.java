package com.timetracks;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class NewProjectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_project);
		EditText et = (EditText) findViewById(R.id.enter_project_name);
		if (et != null)	{
			String projectName = et.getText().toString();
			// Persist to database the project name...do we need to set any other booleans?
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_project, menu);
		return true;
	}

}
