package com.timetracks.bootstrapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.timetracks.models.GTCluster;

public class GTClusterValues {

	private Date getDate(String hyphenatedString) {
		String[] pargs = hyphenatedString.split("-");
		GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(pargs[0]), Integer.parseInt(pargs[1]), Integer.parseInt(pargs[2]), Integer.parseInt(pargs[3]), Integer.parseInt(pargs[4]), Integer.parseInt(pargs[5]));
		return gc.getTime();
	}
	
	public List<GTCluster> getClusters() {
		List<GTCluster> list = new ArrayList<GTCluster>();
		GTCluster cluster = new GTCluster();
		
		// Monday
		cluster.startDate = getDate("2013-11-25-1-0-0");
		cluster.endDate = getDate("2013-11-25-9-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false; 
		//cluster.project = new Project()
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-25-9-0-0");
		cluster.endDate = getDate("2013-11-25-17-0-0");
		cluster.c_x = 40.74833; // Empire
		cluster.c_y = -73.98556;
		cluster.userProvided = false; 
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-25-17-0-0");
		cluster.endDate = getDate("2013-11-25-23-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false; 
		list.add(cluster);
		
		// Tuesday
		cluster.startDate = getDate("2013-11-26-1-0-0");
		cluster.endDate = getDate("2013-11-26-9-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-26-9-0-0");
		cluster.endDate = getDate("2013-11-26-12-30-0");
		cluster.c_x = 40.74833; // Empire
		cluster.c_y = -73.98556;
		cluster.userProvided = false; 
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-26-12-30-0");
		cluster.endDate = getDate("2013-11-26-13-30-0");
		cluster.c_x = 40.78271; // Central Park
		cluster.c_y = -73.96531;
		cluster.userProvided = false;
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-26-13-30-0");
		cluster.endDate = getDate("2013-11-26-18-0-0");
		cluster.c_x = 40.74833; // Empire
		cluster.c_y = -73.98556;
		cluster.userProvided = false; 
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-26-18-0-0");
		cluster.endDate = getDate("2013-11-26-23-30-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		//Wednesday
		cluster.startDate = getDate("2013-11-27-1-0-0");
		cluster.endDate = getDate("2013-11-27-9-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-27-9-0-0");
		cluster.endDate = getDate("2013-11-27-17-0-0");
		cluster.c_x = 40.74833; // Empire
		cluster.c_y = -73.98556;
		cluster.userProvided = false; 
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-27-17-0-0");
		cluster.endDate = getDate("2013-11-27-23-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		
		//Thursday
		cluster.startDate = getDate("2013-11-28-1-0-0");
		cluster.endDate = getDate("2013-11-28-9-3-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-28-9-3-0");
		cluster.endDate = getDate("2013-11-28-18-0-0");
		cluster.c_x = 40.74833; // Empire
		cluster.c_y = -73.98556;
		cluster.userProvided = false; 
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-28-17-0-0");
		cluster.endDate = getDate("2013-11-28-23-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		//Friday
		cluster.startDate = getDate("2013-11-29-1-0-0");
		cluster.endDate = getDate("2013-11-29-9-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-29-9-0-0");
		cluster.endDate = getDate("2013-11-29-12-0-0");
		cluster.c_x = 40.75201; // Random Dentist in Manhattan
		cluster.c_y = -73.99118;
		cluster.userProvided = false;
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-29-12-0-0");
		cluster.endDate = getDate("2013-11-29-18-0-0");
		cluster.c_x = 40.74833; // Empire
		cluster.c_y = -73.98556;
		cluster.userProvided = false; 
		list.add(cluster);
		
		cluster.startDate = getDate("2013-11-29-18-0-0");
		cluster.endDate = getDate("2013-11-29-23-0-0");
		cluster.c_x = 40.75311; // Alley
		cluster.c_y = -73.98921;
		cluster.userProvided = false;
		list.add(cluster);
		
		
		return list;
	}
}