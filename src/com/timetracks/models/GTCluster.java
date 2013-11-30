package com.timetracks.models;

import java.util.Date;

import android.provider.BaseColumns;

public class GTCluster {
	public int id;
	public double c_x;
	public double c_y;
	public double radius;
	public Date startDate;
	public Date endDate;
	public boolean userProvided;
	
	public static abstract class GTClusterSchema implements BaseColumns {
		public static final String TABLE_NAME="GTCluster";
		public static final String COLUMN_NAME_C_X="cx";
		public static final String COLUMN_NAME_C_Y="cy";
		public static final String COLUMN_NAME_RADIUS="radius";
		public static final String COLUMN_NAME_START_TIME="start_time";
		public static final String COLUMN_NAME_END_TIME="end_time";
		public static final String COLUMN_NAME_USER_SET="set_by_user";
	}
}
