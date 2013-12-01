package com.timetracks.models;

import java.io.Serializable;
import java.util.Date;

import com.roscopeco.ormdroid.Entity;

public class TimesheetEntry extends Entity implements Serializable{
	private static final long serialVersionUID = -4371373555974701792L;
	public static final String TIMESHEET_ENTRY_TAG = "TIMESHEET_ENTRY";
	public int id;
	public Date startDate;
	public Date endDate;
	public Project project;
	public String note;
	public String colourCode;
}
