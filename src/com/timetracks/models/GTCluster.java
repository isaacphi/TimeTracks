package com.timetracks.models;

import java.io.Serializable;
import java.util.Date;

import com.roscopeco.ormdroid.Entity;

public class GTCluster extends Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1340363157358959107L;
	public static final String GT_CLUSTER_TAG = "GT_CLUSTER";
	
	public int id;
	public double c_x;
	public double c_y;
	public double radius;
	public Date startDate;
	public Date endDate;
	public boolean userProvided;
}
