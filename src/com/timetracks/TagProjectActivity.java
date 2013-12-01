package com.timetracks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TagProjectActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_project);
		// Use Listview adapter
		String [] choices = {"Add to Existing Project","New Project"};
		
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, choices);
		ListView listView = (ListView) findViewById(R.id.tag_project_dialog);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if (position == 0)	{
					// If first position, add to Existing Project
					Intent intent = new Intent(getApplicationContext(), AddExistingProjectActivity.class);
					startActivity(intent);
					
				} else	{
					Intent intent = new Intent (getApplicationContext(), NewProjectActivity.class);
					startActivity(intent);
				}
			}
		});
			
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_project, menu);
		return true;
	}

}
