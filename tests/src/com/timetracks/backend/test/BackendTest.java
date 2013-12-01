package com.timetracks.backend.test;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.backend.Backend;
import com.timetracks.models.GTCluster;
import com.timetracks.models.GTPoint;
import com.timetracks.models.GTTimesheetLink;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;

public class BackendTest extends ApplicationTestCase<ORMDroidApplication>{
	Backend backend;
	
	public BackendTest() {
		super(ORMDroidApplication.class);
	}
	
	public void setUp() throws Exception {
		super.setUp();
		backend = new Backend();
		clearDatabase();
	}
	
	static void clearDatabase() {
		assertAllTablesCreated();
		clearAllTables();
	}
	static void assertAllTablesCreated() {
		Project p = new Project();
		p.save();
		TimesheetEntry entry = new TimesheetEntry();
		entry.save();
		GTCluster cluster = new GTCluster();
		cluster.save();
		GTPoint point = new GTPoint();
		point.save();
		GTTimesheetLink link = new GTTimesheetLink();
		link.timesheetEntry = entry;
		link.gtCluster = cluster;
		link.save();
	}
	
	static final String TABLE_PREFIX = "comtimetracksmodels";
	static void clearAllTables() {
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
	
	public void testBasicPersistence() {
		assertEquals(0, backend.getAllProjects().size());
		
		final String someProjectName = "684 Harbord"; 
		Project p = new Project();
		p.name = someProjectName;
		p.save();
		
		List<Project> allProjects = backend.getAllProjects();
		assertEquals(1, allProjects.size());
		assertEquals(someProjectName, allProjects.get(0).name);
		
		allProjects.get(0).delete();
		
		assertEquals(0, backend.getAllProjects().size());
	}
	
	private Date dateHelper(String string) {
		String[] parts = string.split("-");
		int[] int_parts = new int[parts.length];
		for(int i=0; i<parts.length; i++) {
			int_parts[i] = Integer.parseInt(parts[i]);
		}
		return new GregorianCalendar(int_parts[0], int_parts[1], int_parts[2], int_parts[3], int_parts[4], int_parts[5]).getTime();
		
	}
	
	public void testGetTimesheetEntries () {
		assertEquals(0, backend.getAllTimesheetEntries().size());
		Date[] dateRange = new Date[6];
		dateRange[0] = dateHelper("2013-12-01-09-00-00");
		dateRange[1] = dateHelper("2013-12-01-10-00-00");
		dateRange[2] = dateHelper("2013-12-01-11-00-00");
		dateRange[3] = dateHelper("2013-12-01-12-00-00");
		dateRange[4] = dateHelper("2013-12-01-13-00-00");
		// will create 4 timesheet entries
		
		for(int i=0; i<dateRange.length-1; i++) {
			TimesheetEntry entry = new TimesheetEntry();
			entry.startDate = dateRange[i];
			entry.endDate = dateRange[i+1];
			entry.save();
		}
		
		List<TimesheetEntry> entryList = backend.getTimesheetEntries(
												dateHelper("2013-12-01-9-15-00"),
												dateHelper("2013-12-01-11-30-00"));
		assertEquals(3, entryList.size());		
	}
}
