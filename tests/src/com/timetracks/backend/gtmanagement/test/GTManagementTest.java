package com.timetracks.backend.gtmanagement.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

import com.roscopeco.ormdroid.Entity;
import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.backend.gtmanagement.GTManager;
import com.timetracks.models.DatabaseInfo;
import com.timetracks.models.GTCluster;
import com.timetracks.models.GTPoint;

public class GTManagementTest extends ApplicationTestCase<ORMDroidApplication>{

	public GTManagementTest() {
		super(ORMDroidApplication.class);
	}
	
	public GTManager gtManager;
	public Date startDate;
	public Date midDate;
	public Date endDate;
	public void setUp() {
		gtManager = new GTManager();
		SQLiteDatabase db = ORMDroidApplication.getDefaultDatabase();
		db.delete(DatabaseInfo.TABLE_PREFIX + "GTTimesheetLink", null, null);
		db.delete(DatabaseInfo.TABLE_PREFIX + "TimesheetEntry", null, null);
		db.delete(DatabaseInfo.TABLE_PREFIX + "GTCluster", null, null);
		db.delete(DatabaseInfo.TABLE_PREFIX + "GTPoint", null, null);

		double[][] points = { // {x,y}
			{40.75311, -73.98921}, // Alley
			{40.74833, -73.98556}, // Empire
			{40.78271, -73.96531}, // Central Park
			{40.75201, -73.99118}, // Random Dentist in Manhattan
		};
		
		int[] repitition = { 14, 22, 34, 3 };
		
		GTPoint gtPoint;
		Calendar calendar = new GregorianCalendar(2013, 12, 1, 12, 0, 0);
		startDate = calendar.getTime();
		for(int i_point=0; i_point<points.length; i_point++) {
			double[] currentPoint = points[i_point];
			for(int i_rep=0; i_rep<repitition[i_point]; i_rep++) {
				gtPoint = new GTPoint();
				gtPoint.x = currentPoint[0];
				gtPoint.y = currentPoint[1];
				gtPoint.dates = calendar.getTime();
				gtPoint.save();
				calendar.add(Calendar.MINUTE, 5);
			}
		}
		endDate = calendar.getTime(); //
		
		// 20*5 minutes after startDate
		midDate = new GregorianCalendar(2013, 12, 1, 13, 40, 0).getTime();
	}
	
	public void testSummarizeOne() {
		assertEquals(2, gtManager.generateLinks(startDate, midDate).size());
	}
	
	public void testSummarizeTwo() {
		assertEquals(3, gtManager.generateLinks(midDate, endDate).size());
	}
	
	public void testSummarizeThree() {
		assertEquals(4, gtManager.generateLinks(startDate, endDate));
	}
	
	public void testGetLinks() {
		assertEquals(2, gtManager.getLinks(startDate, midDate).size());
		assertEquals(5, gtManager.getLinks(startDate, endDate).size());
		assertEquals(5, Entity.query(GTCluster.class).executeMulti().size());
	}
}
