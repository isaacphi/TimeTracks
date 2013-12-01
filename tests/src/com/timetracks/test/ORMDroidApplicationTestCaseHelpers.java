package com.timetracks.test;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.roscopeco.ormdroid.Entity;
import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.models.GTCluster;
import com.timetracks.models.GTPoint;
import com.timetracks.models.GTTimesheetLink;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;

public class ORMDroidApplicationTestCaseHelpers {
	public static void clearAllTables() {
		List<Project> projectList = Entity.query(Project.class).executeMulti();
		if(projectList != null) {
			for(Project p: projectList) { p.delete(); }
		}
		
		List<GTTimesheetLink> linkList = Entity.query(GTTimesheetLink.class).executeMulti();
		if(linkList != null) {
			for(GTTimesheetLink link: linkList) { link.delete(); }
		}
		
		List<GTCluster> clusterList = Entity.query(GTCluster.class).executeMulti();
		if(clusterList != null) {
			for(GTCluster cluster: clusterList) { cluster.delete(); }
		}
		
		List<GTPoint> list = Entity.query(GTPoint.class).executeMulti();
		if(list != null) {
			for(GTPoint item: list) { item.delete(); }
		}
		
		List<TimesheetEntry> timesheetList = Entity.query(TimesheetEntry.class).executeMulti();
		if(list != null) {
			for(TimesheetEntry item: timesheetList) { item.delete(); }
		}
	}
	
	public static final String TABLE_PREFIX = "comtimetracksmodels";
	public static void clearAllTables1() {
		SQLiteDatabase db = ORMDroidApplication.getDefaultDatabase();
		String[] tableNames = {
			"Project", "TimesheetEntry", "GTPoint", "GTCluster", "GTTimesheetLink"
		};
		for(String table:tableNames) {
			table = TABLE_PREFIX + table;
			String selection = null;
			String[] selectionArgs = null;
			db.delete(table, selection, selectionArgs);
		}
	}
}
