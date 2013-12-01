package com.timetracks.bootstrapping;

import java.util.ArrayList;
import java.util.List;

import com.timetracks.models.GTCluster;
import com.timetracks.models.GTTimesheetLink;
import com.timetracks.models.TimesheetEntry;

public class GTTimesheetLinkValues {
	
	public List<GTTimesheetLink> getGTTimesheetLinkValues() {
		List<GTTimesheetLink> list = new ArrayList<GTTimesheetLink>();
		GTTimesheetLink link = new GTTimesheetLink();
		
		List<TimesheetEntry> tsList = TimeSheetEntryValues.getEntries();
		List<GTCluster> gtcList = GTClusterValues.getClusters();
	
		for (int i = 0; i < tsList.size(); i++) {
			link.timesheetEntry = tsList.get(i);
			link.gtCluster = gtcList.get(i);
			link.taggedByUser = false;
			link.isExcluded = false;
			link.excludedSetByUser = false;
			link.projectSetByUser = false;
			link.noteSetByUser = false;
			list.add(link);
		}
		
		/* OLD loop
		for (TimesheetEntry entry : tsList) {
			link.timesheetEntry = entry;
			link.taggedByUser = false;
			link.isExcluded = false;
			link.excludedSetByUser = false;
			link.projectSetByUser = false;
			link.noteSetByUser = false;
			list.add(link);
		}*/
		
		return list;
	}

}
