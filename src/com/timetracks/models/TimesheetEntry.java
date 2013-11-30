package com.timetracks.models;

import java.util.Date;

import com.roscopeco.ormdroid.Entity;

public class TimesheetEntry extends Entity {
	public int id;
	public Date startDate;
	public Date endDate;
	public Project project;
	public String note;
	public String colourCode;
}
