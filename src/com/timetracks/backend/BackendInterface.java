package com.timetracks.backend;

import java.util.List;
import java.util.Date;

import com.timetracks.models.GTSequence;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;
import com.timetracks.models.TimeSpaceProjectAssociation;

public interface BackendInterface {
	public List<Project> getAllProjects();
	public void createProject(Project project);

	public List<TimesheetEntry> getTimesheetEntries(Date startTime, Date endTime);
	public List<GTSequence> getGTSequencesForTimesheetEntries(List<TimesheetEntry> entries);
	
	// I'm not sure this is what method you want.
	// It's not clear what this even means.
	public List<TimeSpaceProjectAssociation> getTimesheetsAndGeos(Date startTime, Date endtime);

	public void setNoteToTimesheetEntry(TimesheetEntry entry, String note);
	public void setProjectToTimesheetEntry(TimesheetEntry entry, Project project);

	public void deleteTimesheetEntry(TimesheetEntry entry);
	public void noteExclusion(TimeSpaceProjectAssociation tagging);
}
