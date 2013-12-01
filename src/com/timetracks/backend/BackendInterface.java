package com.timetracks.backend;

import java.util.List;
import java.util.Date;

import com.timetracks.models.GTCluster;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;
import com.timetracks.models.GTTimesheetLink;

/**
 * BackendInterface.
 * 
 * Assume all these methods make at least one database call, and therefore
 * must not be called from within the UI thread (e.g. spawn an AsyncTask).
 * 
 * UI should feel free to set some Entity member data directly (e.g. colourCode)
 * and call the appropriate save method (but this is also blocking).
 *  
 * @author wgeorge
 */
public interface BackendInterface {
	public List<Project> getAllProjects();
	public void createProject(Project project);

	/**
	 * Returns any TimesheetEntry objects that <em>intersects</em> the provided
	 * date range. I.e. any with entry.startDate or entry.endDate between
	 * startDate and endDate will be returned.
	 */
	public List<TimesheetEntry> getTimesheetEntries(Date startDate, Date endDate);
	public List<GTCluster> getGTClustersForTimesheetEntries(List<TimesheetEntry> entryList);
	
	// I'm not sure this is what method you want.
	public List<GTTimesheetLink> getTimesheetsAndGeos(Date startDate, Date endDate);

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
