package com.timetracks.backend.gtmanagement.test;

import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.Helpers;
import com.timetracks.models.DatabaseInfo;
import com.timetracks.models.GTPoint;

public class GTManagementTest extends ApplicationTestCase<ORMDroidApplication>{

	public GTManagementTest(Class<ORMDroidApplication> applicationClass) {
		super(applicationClass);
	}
	
	public void setUp() {
		SQLiteDatabase db = ORMDroidApplication.getDefaultDatabase();
		db.delete(DatabaseInfo.TABLE_PREFIX + "GTTimesheetLink", null, null);
		db.delete(DatabaseInfo.TABLE_PREFIX + "TimesheetEntry", null, null);
		db.delete(DatabaseInfo.TABLE_PREFIX + "GTCluster", null, null);
		db.delete(DatabaseInfo.TABLE_PREFIX + "GTPoint", null, null);
	}
	
	public void testSummarize() {
		double[][] points = {
			// {x,y}
			{40.75311, -73.98921}, // Alley
			{40.74833, -73.98556}, // Empire
			{40.78271, -73.96531}, // Central Park
			{40.75201, -73.99118}, // Random Dentist in Manhattan
		};
		
		GTPoint point = new GTPoint();
		point.x = points[0][0];
		point.y = points[0][1];
		point.date = Helpers.dateHelper("2013-11-30-11-00-00");
	}
}
