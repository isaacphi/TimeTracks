package com.timetracks.backend;

import java.util.Date;
import java.util.List;

import com.timetracks.bootstrapping.GTClusterValues;
import com.timetracks.bootstrapping.GTTimesheetLinkValues;
import com.timetracks.bootstrapping.TimesheetEntryValues;
import com.timetracks.models.GTCluster;
import com.timetracks.models.GTTimesheetLink;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;

public class BootstrappedBackend extends Backend {
	private static BackendInterface singleton=null;
	public static BackendInterface getInstance() {
		if (singleton==null) {
			singleton=new BootstrappedBackend();
		}
		return singleton;
	}
	private BootstrappedBackend() {
		if(!databaseBootstrapped()) {
			bootstrapDatabase();
		}
	}
	
	boolean databaseBootstrapped() {
		return false;
	}
	
	void bootstrapDatabase() {
		// bootstrap database;
		List<GTTimesheetLink> linkList = GTTimesheetLinkValues.getGTTimesheetLinkValues();
		for(GTTimesheetLink link : linkList) {
			link.timesheetEntry.save();
			link.gtCluster.save();
			link.save();
		}
	}	
}
