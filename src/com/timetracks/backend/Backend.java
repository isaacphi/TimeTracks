package com.timetracks.backend;

import static com.roscopeco.ormdroid.Query.or;
import static com.roscopeco.ormdroid.Query.and;
import static com.roscopeco.ormdroid.Query.eql;
import static com.roscopeco.ormdroid.Query.geq;
import static com.roscopeco.ormdroid.Query.leq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.roscopeco.ormdroid.Entity;
import com.timetracks.models.GTCluster;
import com.timetracks.models.GTTimesheetLink;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;

public class Backend implements BackendInterface {
	@Override
	public List<Project> getAllProjects() {
		return Entity.query(Project.class).executeMulti();
	}

	@Override
	public void createProject(Project project) {
		project.save();		
	}
	
	public List<TimesheetEntry> getAllTimesheetEntries() {
		return Entity.query(TimesheetEntry.class).executeMulti();
	}

	@Override
	public List<TimesheetEntry> getTimesheetEntries(Date startDate, Date endDate) {
		return Entity.query(TimesheetEntry.class).where(
			or(and(geq("startDate", startDate), leq("startDate", endDate)),
			   and(geq("endDate",   startDate), leq("endDate",   endDate)))).executeMulti();
	}

	@Override
	public List<GTTimesheetLink> getTimesheetsAndGeos(Date startTime,
			Date endtime) {
		return null;
	}

	@Override
	public void setNoteToTimesheetEntry(TimesheetEntry entry, String note) {
		GTTimesheetLink link = Entity.query(GTTimesheetLink.class).where(eql("timesheetEntry", entry.id)).execute();
		if(link != null) {
			link.noteSetByUser=true;
			link.save();
		}
		entry.note = note;
		entry.save();
	}

	@Override
	public void setProjectToTimesheetEntry(TimesheetEntry entry, Project project) {
		GTTimesheetLink link = Entity.query(GTTimesheetLink.class).where(eql("timesheetEntry", entry.id)).execute();
		if(link != null) {
			link.projectSetByUser=true;
			link.save();
		}
		if(project.id < 0) {
			Log.e("setProjectToTimesheetEntry", "Calling setProjectToTimesheetEntry with a non-persisted project");
			project.save();
		}
		entry.project = project;
		entry.save();		
	}

	@Override
	public void deleteTimesheetEntry(TimesheetEntry entry) {
		GTTimesheetLink link = GTTimesheetLink.getForTimesheetEntry(entry);
		if(link!=null) {
			link.isExcluded=true;
			link.excludedSetByUser=true;
			link.save();
		}
		entry.delete();		
	}

	@Override
	public void excludeGTCluster(GTCluster cluster, Date startDate, Date endDate) {
		// compute all GTClusters that geo-intersect cluster;
		// delete all associated timesheets as excluded;
		// mark all associated links as excluded
	}

	@Override
	public List<GTCluster> getGTClustersForTimesheetEntries(
			List<TimesheetEntry> entryList) {
		List<GTCluster> clusterList = new ArrayList<GTCluster>();
		for(TimesheetEntry entry : entryList) {
			GTTimesheetLink link = GTTimesheetLink.getForTimesheetEntry(entry);
			if(link == null) {
				clusterList.add(null);
			} else {
				clusterList.add(link.gtCluster);
			}
		}
		
		return clusterList;
	}

	@Override
	public Date getMaxTimesheetEntryDate() {
		// default to current time
		return new Date();
	}
}
