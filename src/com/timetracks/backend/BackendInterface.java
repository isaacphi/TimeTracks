package com.timetracks.backend;

import java.util.List;
import java.util.Date;

import com.timetracks.models.GTCluster;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;
import com.timetracks.models.GTTimesheetLink;

public interface BackendInterface {
	public List<Project> getAllProjects();
	public void createProject(Project project);

	public List<TimesheetEntry> getTimesheetEntries(Date startTime, Date endTime);
	public List<GTCluster> getGTClustersForTimesheetEntries(List<TimesheetEntry> entryList);
	
	// I'm not sure this is what method you want.
	public List<GTTimesheetLink> getTimesheetsAndGeos(Date startTime, Date endtime);

	public void setNoteToTimesheetEntry(TimesheetEntry entry, String note);
	public void setProjectToTimesheetEntry(TimesheetEntry entry, Project project);

	public void deleteTimesheetEntry(TimesheetEntry entry);
	
	/**
	 * Mark any GTCluster associated with a TimesheetEntry in the provided
	 * date range as excluded. This call may effect many TimesheetEntry objects
	 * created by the tagging algorithm, and thus a full UI refresh is likely 
	 * necessary. 
	 *   
	 * @param cluster
	 * @param startDate
	 * @param endDate
	 */
	public void excludeGTCluster(GTCluster cluster, Date startDate, Date endDate);
}
