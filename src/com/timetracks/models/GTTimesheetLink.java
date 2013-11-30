package com.timetracks.models;

// Output by the tagging algorithm.
// Annotates which attributes were suggested by the backend, and
// which were overriden by the user (for subsequent performance improvements
// of the tagging algorithm).
public class GTTimesheetLink {
	public GTCluster gtCluster;
	public TimesheetEntry timesheetEntry;
	public boolean taggedByUser;
	public boolean isExcluded;
	public boolean excludedSetByUser;
	public boolean projectSetByUser;
	public boolean noteSetByUser;
}
