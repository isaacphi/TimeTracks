package com.timetracks.backend.test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.Helpers;
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
	
	public void testGetTimesheetEntries () {
		assertEquals(0, backend.getAllTimesheetEntries().size());
		Date[] dateRange = new Date[6];
		dateRange[0] = Helpers.dateHelper("2013-12-01-09-00-00");
		dateRange[1] = Helpers.dateHelper("2013-12-01-10-00-00");
		dateRange[2] = Helpers.dateHelper("2013-12-01-11-00-00");
		dateRange[3] = Helpers.dateHelper("2013-12-01-12-00-00");
		dateRange[4] = Helpers.dateHelper("2013-12-01-13-00-00");
		// will create 4 timesheet entries
		
		for(int i=0; i<dateRange.length-1; i++) {
			TimesheetEntry entry = new TimesheetEntry();
			entry.startDate = dateRange[i];
			entry.endDate = dateRange[i+1];
			entry.save();
		}
		
		List<TimesheetEntry> entryList = backend.getTimesheetEntries(
												Helpers.dateHelper("2013-12-01-9-15-00"),
												Helpers.dateHelper("2013-12-01-11-30-00"));
		assertEquals(3, entryList.size());		
	} 
	
	public void testAnnotatingTimesheetEntry() {
		TimesheetEntry entry = new TimesheetEntry();
		entry.save();
		int entry_id = entry.id;
		GTTimesheetLink link = new GTTimesheetLink();
		link.timesheetEntry = entry;
		link.save();
		assertFalse(link.noteSetByUser);
		assertFalse(link.excludedSetByUser);
		assertFalse(link.projectSetByUser);
		assertFalse(link.taggedByUser);
		
		final String note = "I'm a note!";
		backend.setNoteToTimesheetEntry(entry, note);
		
		/* refresh the entry with latest db state */
		entry = TimesheetEntry.getById(entry_id);
		assertEquals(note, entry.note);
		
		link = GTTimesheetLink.getForTimesheetEntry(entry);
		
		assertTrue(link.noteSetByUser);
		assertFalse(link.excludedSetByUser);
		assertFalse(link.projectSetByUser);
		assertFalse(link.taggedByUser);
	}
}
