package com.timetracks.backend.test;

import java.util.Date;
import java.util.List;

import android.test.ApplicationTestCase;

import com.roscopeco.ormdroid.Entity;
import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.Helpers;
import com.timetracks.backend.BootstrappableBackend;
import com.timetracks.bootstrapping.GTClusterValues;
import com.timetracks.bootstrapping.GTTimesheetLinkValues;
import com.timetracks.bootstrapping.TimesheetEntryValues;
import com.timetracks.models.GTCluster;
import com.timetracks.models.GTTimesheetLink;
import com.timetracks.models.TimesheetEntry;
import com.timetracks.test.ORMDroidApplicationTestCaseHelpers;

public class BootstrappedBackendTest extends ApplicationTestCase<ORMDroidApplication>{
	BootstrappableBackend backend;
	List<GTCluster> e_cluster_list   = GTClusterValues.getClusters();
	List<GTTimesheetLink> e_link_list= GTTimesheetLinkValues.getGTTimesheetLinkValues();
	List<TimesheetEntry> e_timesheet_list=TimesheetEntryValues.getEntries(); 

	
	public BootstrappedBackendTest() {
		super(ORMDroidApplication.class);
	}
	
	public void setUp() {
		ORMDroidApplicationTestCaseHelpers.clearAllTables();
		backend = new BootstrappableBackend();
	}
	
	public void tearDown() {
		ORMDroidApplicationTestCaseHelpers.clearAllTables();		
	}
	
	public void testBootstrapBasic() {
		assertEquals(0, Entity.query(GTTimesheetLink.class).executeMulti().size());
		assertEquals(0, Entity.query(GTCluster.class).executeMulti().size());
		assertEquals(0, Entity.query(TimesheetEntry.class).executeMulti().size());
		
		backend.bootstrap();
		
		assertEquals(e_cluster_list.size(), Entity.query(GTCluster.class).executeMulti().size());
		assertEquals(e_timesheet_list.size(), Entity.query(TimesheetEntry.class).executeMulti().size());
		assertEquals(e_link_list.size(), Entity.query(GTTimesheetLink.class).executeMulti().size());		
	}
	
	public void testGetTimesheetEntries() {
		backend.bootstrap();
		assertEquals(e_timesheet_list.size(),
					 backend.getAllTimesheetEntries().size());
	}
	
	public void testGetTimesheetEntriesByDate() {
		backend.bootstrap();
		assertEquals(e_cluster_list.size(),
				backend.getTimesheetEntries(new Date(0), new Date(Long.MAX_VALUE)).size());
	}
	
	public void getMaxDate() {
		backend.bootstrap();
		Date endDate = backend.getMaxTimesheetEntryDate();
		assertEquals(Helpers.dateHelper("2013-11-29-23-0-0"), endDate);
	}
}
