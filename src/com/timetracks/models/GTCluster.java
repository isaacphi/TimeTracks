package com.timetracks.models;

import java.util.Date;

import com.roscopeco.ormdroid.Entity;

public class GTCluster extends Entity {
	public int id;
	public double c_x;
	public double c_y;
	public double radius;
	public Date startDate;
	public Date endDate;
	public boolean userProvided;
}
