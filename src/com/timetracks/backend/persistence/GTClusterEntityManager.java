package com.timetracks.backend.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

import com.timetracks.models.GTCluster;

public class GTClusterEntityManager extends SQLiteOpenHelper{
	
	//Database Version 
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "dataManager";

    //GTCLUSTERS table name 
    private static final String TABLE_GTCLUSTERS = "TABLE_GTClUSTERS";

    //GTCLUSTERS Table Columns names 
    private static final String KEY_ID = "id";
    private static final String KEY_CX = "c_x";
    private static final String KEY_CY = "c_y";
    private static final String KEY_RADIUS = "radius";
    private static final String KEY_STARTDATE = "startDate";
    private static final String KEY_ENDDATE = "endDate";
    private static final String KEY_USERPROVIDED = "userProvided";
    
    public GTClusterEntityManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	// This method takes in an id number and queries the database to get the associated cluster
	public GTCluster getClusterById(int id) {
		
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
		    KEY_ID,
		    KEY_CX,
		    KEY_CY,
		    KEY_RADIUS,
		    KEY_STARTDATE,
		    KEY_ENDDATE,		 
		    KEY_USERPROVIDED,
		    };
		
		
		SQLiteDatabase db = this.getReadableDatabase();
		
        Cursor cursor = db.query(
        		TABLE_GTCLUSTERS,								//The table to query
        		projection,										//The columns to return
        		"id" + "=?",									//The columns for the WHERE clause
        		new String[] { String.valueOf(id) },			// The values for the WHERE clause
    		    null,                                     // don't group the rows
    		    null,                                     // don't filter by row groups
    		    null                               		  // The sort order
        		);
        if (cursor != null)
            cursor.moveToFirst();
        
        GTCluster gtCluster = new GTCluster();
        gtCluster.id = (int) cursor.getLong(0);
        gtCluster.c_x = (double) cursor.getLong(1);
        gtCluster.c_y = (double) cursor.getLong(2);
        gtCluster.radius = (double) cursor.getLong(3);
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");	//Dates are store as strings!!!
        try {
			gtCluster.startDate = format.parse(cursor.getString(5));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						
        try {
			gtCluster.endDate = format.parse(cursor.getString(6));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        gtCluster.userProvided = (cursor.getString(6) == "true");
        
		return gtCluster;
	}
	
	public List<GTCluster> getClustersIntersecting(double cx, double cy, double radius) {
		// TODO
		
		return null;
	}
	
	// if GTCluster is in database, update the row, otherwise create
	public boolean createGTCluster(GTCluster cluster) {
		if(cluster != null) {			//change to cluster in table logic later
		    return true;
		}
		else {
			SQLiteDatabase db = this.getWritableDatabase();
		    ContentValues values = new ContentValues();
		    values.put(KEY_ID, cluster.id); // cluster id
		    values.put(KEY_CX, cluster.c_x); // X coordinate of cluster
		    values.put(KEY_CY, cluster.c_y); // Y coordinate of cluster
		    values.put(KEY_RADIUS, cluster.radius); // radius of cluster
		    values.put(KEY_STARTDATE,  cluster.startDate.toString()); // String of start date
		    values.put(KEY_ENDDATE, cluster.endDate.toString()); // String of end date
		    values.put(KEY_USERPROVIDED, cluster.userProvided); // radius of cluster

		    // Inserting Row
		    db.insert(TABLE_GTCLUSTERS, null, values);
		    db.close(); // Closing database connection
			return false;
		}
	}
	
	public boolean updateGTCluster(GTCluster cluster) {
		return false;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
