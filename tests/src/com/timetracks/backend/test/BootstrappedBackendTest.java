package com.timetracks.backend.test;

import android.test.ApplicationTestCase;

import com.roscopeco.ormdroid.Entity;
import com.roscopeco.ormdroid.ORMDroidApplication;
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
	
	public void testBootstrap() {
		assertEquals(0, Entity.query(GTCluster.class).executeMulti().size());
		assertEquals(0, Entity.query(TimesheetEntry.class).executeMulti().size());
		assertEquals(0, Entity.query(GTTimesheetLink.class).executeMulti().size());
		
		backend.bootstrap();
		
		assertEquals(GTClusterValues.getClusters().size(), Entity.query(GTCluster.class).executeMulti().size());
		assertEquals(TimesheetEntryValues.getEntries().size(), Entity.query(TimesheetEntry.class).executeMulti().size());
		assertEquals(GTTimesheetLinkValues.getGTTimesheetLinkValues().size(), Entity.query(GTTimesheetLink.class).executeMulti().size());		
	}
}
