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

public class StubbedBackend implements BackendInterface {

	@Override
	public Date getMaxTimesheetEntryDate() {
		return new Date();
	}

	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TimesheetEntry> getTimesheetEntries(Date startDate, Date endDate) {
		return TimesheetEntryValues.getEntries();
	}
	
	@Override
	public List<GTCluster> getGTClusters(Date startDate, Date endDate) {
		return GTClusterValues.getClusters();
	}

	@Override
	public List<GTCluster> getGTClustersForTimesheetEntries(
			List<TimesheetEntry> entryList) {
		return GTClusterValues.getClusters();
	}

	@Override
	public List<GTTimesheetLink> getTimesheetsAndGeos(Date startDate,
			Date endDate) {
		return GTTimesheetLinkValues.getGTTimesheetLinkValues();
	}

	@Override
	public void setNoteToTimesheetEntry(TimesheetEntry entry, String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProjectToTimesheetEntry(TimesheetEntry entry, Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTimesheetEntry(TimesheetEntry entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excludeGTCluster(GTCluster cluster, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		
	}
	
}
