package com.timetracks.test;

import android.database.sqlite.SQLiteDatabase;

import com.roscopeco.ormdroid.ORMDroidApplication;

public class ORMDroidApplicationTestCaseHelpers {
	public static final String TABLE_PREFIX = "comtimetracksmodels";
	public static void clearAllTables() {
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
