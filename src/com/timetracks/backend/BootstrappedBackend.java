package com.timetracks.backend;

import java.util.List;

import android.content.SharedPreferences;
import android.util.Log;

import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.bootstrapping.GTTimesheetLinkValues;
import com.timetracks.models.GTTimesheetLink;

public class BootstrappedBackend extends Backend {
	//private static BackendInterface singleton=null;
	private static final String PREFERENCE_FILE="BootstrappedBackendPreferences";
	/*public static BackendInterface getInstance() {
		if (singleton==null) {
			singleton=new BootstrappedBackend();
		}
		return singleton;
	}*/
	public BootstrappedBackend() {
		SharedPreferences preferences = ORMDroidApplication.getSingleton().getApplicationContext().getSharedPreferences(PREFERENCE_FILE, 0);
		if(!preferences.getBoolean("isBootstrapped", false)) {
			Log.d("BootstrappedDatabase", "Bootstrapping database");
			List<GTTimesheetLink> linkList = GTTimesheetLinkValues.getGTTimesheetLinkValues();
			for(GTTimesheetLink link : linkList) {
				link.timesheetEntry.save();
				link.gtCluster.save();
				link.save();
			}
			SharedPreferences.Editor edit = preferences.edit();
			edit.putBoolean("isBootstrapped", true);
			edit.commit();
		}
	}
}
