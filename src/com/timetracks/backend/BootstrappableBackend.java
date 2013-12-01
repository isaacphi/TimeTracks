package com.timetracks.backend;

import java.util.List;

import com.timetracks.bootstrapping.GTTimesheetLinkValues;
import com.timetracks.models.GTTimesheetLink;

public class BootstrappableBackend extends Backend {
	public void bootstrap() {
		List<GTTimesheetLink> linkList = GTTimesheetLinkValues.getGTTimesheetLinkValues();
		for(GTTimesheetLink link:linkList) {
			link.timesheetEntry.save();
			link.gtCluster.save();
			link.save();
		}
	}
}
