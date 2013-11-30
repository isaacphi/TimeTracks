package com.timetracks.models;

import com.roscopeco.ormdroid.Entity;

// Output by the tagging algorithm.
// Annotates which attributes were suggested by the backend, and
// which were overriden by the user (for subsequent performance improvements
// of the tagging algorithm).
public class GTTimesheetLink extends Entity {
	public int id;
	public GTCluster gtCluster;
	public TimesheetEntry timesheetEntry;
	public boolean taggedByUser;
	public boolean isExcluded;
	public boolean excludedSetByUser;
	public boolean projectSetByUser;
	public boolean noteSetByUser;
}
