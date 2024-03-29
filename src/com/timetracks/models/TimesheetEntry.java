package com.timetracks.models;

import java.io.Serializable;
import java.util.Date;

import com.roscopeco.ormdroid.Entity;
import static com.roscopeco.ormdroid.Query.eql;

public class TimesheetEntry extends Entity implements Serializable{
	private static final long serialVersionUID = -4371373555974701792L;
	public static final String TIMESHEET_ENTRY_TAG = "TIMESHEET_ENTRY";
	public int id;
	public Date startDate;
	public Date endDate;
	public Project project;
	public String note;
	public String colourCode;
	
	public static TimesheetEntry getById(int id) {
		return Entity.query(TimesheetEntry.class).where(eql("id", id)).execute();
	}
	
	public boolean spansMultipleDays() {
		return startDate.getDay() != endDate.getDay();
	}
}
