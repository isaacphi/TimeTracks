package com.timetracks.models;

import com.roscopeco.ormdroid.Entity;
import static com.roscopeco.ormdroid.Query.eql;


// Output by the tagging algorithm.
// Annotates which attributes were suggested by the backend, and
// which were overriden by the user (for subsequent performance improvements
// of the tagging algorithm).
public class GTTimesheetLink extends Entity {
	public int id;
	public GTCluster gtCluster;
	public TimesheetEntry timesheetEntry;
	public boolean taggedByUser=false;
	public boolean isExcluded=false;
	public boolean excludedSetByUser=false;
	public boolean projectSetByUser=false;
	public boolean noteSetByUser=false;
	
	public static GTTimesheetLink getForTimesheetEntry(TimesheetEntry entry) {
		return Entity.query(GTTimesheetLink.class).where(eql("timesheetEntry", entry)).execute();
	}
}
