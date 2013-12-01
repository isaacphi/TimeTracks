package com.timetracks.backend;

import java.util.List;

import android.content.SharedPreferences;
import android.util.Log;

import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.bootstrapping.GTTimesheetLinkValues;
import com.timetracks.models.GTTimesheetLink;

public class BootstrappableBackend extends Backend {
	private static final String PREFERENCE_FILE="BootstrappedBackendPreferences";
	public BootstrappableBackend() {
		SharedPreferences preferences = ORMDroidApplication.getSingleton().getApplicationContext().getSharedPreferences(PREFERENCE_FILE, 0);
		if(!preferences.getBoolean("isBootstrapped", false)) {
			bootstrap();
			SharedPreferences.Editor edit = preferences.edit();
			edit.putBoolean("isBootstrapped", true);
			edit.commit();
		}
	}
	public void bootstrap() {
		List<GTTimesheetLink> linkList = GTTimesheetLinkValues.getGTTimesheetLinkValues();
		for(GTTimesheetLink link:linkList) {
			if(link.timesheetEntry.project != null) {
				link.timesheetEntry.project.save();
			}
			link.timesheetEntry.save();
			link.gtCluster.save();
			link.save();
		}
	}
}
