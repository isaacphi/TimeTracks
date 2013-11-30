package com.timetracks.backend.persistence;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.timetracks.models.GTCluster;

public class GTClusterEntityManager extends SQLiteOpenHelper{
	
	public GTClusterEntityManager(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	// This method takes in an id number and queries the database to get the associated cluster
	public GTCluster getClusterById(int id) {
		
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
		    "id",
		    "C_X",
		    "C_Y",
		    "RADIUS",
		    "STARTDATE",
		    "ENDDATE",		 
		    "USERPROVIDED",
		    };
		
		
		SQLiteDatabase db = this.getReadableDatabase();
		
        Cursor cursor = db.query(
        		"TABLE_GTClUSTERS",								//The table to query
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
        gtCluster.startDate = new Date(cursor.getLong(4));		//Not sure how to extract date type from the table
        gtCluster.endDate = new Date(cursor.getLong(5));
        gtCluster.userProvided = (cursor.getString(6) == "true");
        
		return gtCluster;
	}
	public List<GTCluster> getClustersIntersecting(double cx, double cy, double radius) {
		// TODO
		
		return null;
	}
	
	// if GTCluster is in database, update the row, otherwise create
	public boolean createGTCluster(GTCluster cluster) {
		/*if(cluster in Table) {
		    return true;
		}
		else {
			SQLiteDatabase db = this.getWritableDatabase();
		    ContentValues values = new ContentValues();
		    values.put(KEY_NAME, contact.getName()); // Contact Name
		    values.put(KEY_PH_NUM, contact.getPhoneNumber()); // Contact Phone

		    // Inserting Row
		    db.insert(TABLE_CONTACTS, null, values);
		    db.close(); // Closing database connection
			return false;
		}*/
		return false;
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
