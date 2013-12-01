package com.timetracks.bootstrapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;

public class TimeSheetEntryValues {

	private static Date getDate(String hyphenatedString) {
		String[] pargs = hyphenatedString.split("-");
		GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(pargs[0]), Integer.parseInt(pargs[1]), Integer.parseInt(pargs[2]), Integer.parseInt(pargs[3]), Integer.parseInt(pargs[4]), Integer.parseInt(pargs[5]));
		return gc.getTime();
	}
	
	public static List<TimesheetEntry> getEntries() {
		List<TimesheetEntry> list = new ArrayList<TimesheetEntry>();
		TimesheetEntry entry = new TimesheetEntry();
		
		Project proj = new Project();
		entry.project = proj;
		
		
		// Monday
		entry.id = 1;
		entry.startDate = getDate("2013-11-25-1-0-0");
		entry.endDate = getDate("2013-11-25-9-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		entry.id = 2;
		entry.startDate = getDate("2013-11-25-9-0-0");
		entry.endDate = getDate("2013-11-25-17-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry.id = 3;
		entry.startDate = getDate("2013-11-25-17-0-0");
		entry.endDate = getDate("2013-11-25-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		// Tuesday
		entry.id = 4;
		entry.startDate = getDate("2013-11-26-1-0-0");
		entry.endDate = getDate("2013-11-26-9-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		entry.id = 5;
		entry.startDate = getDate("2013-11-26-9-0-0");
		entry.endDate = getDate("2013-11-26-12-30-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry.id = 6;
		entry.startDate = getDate("2013-11-26-12-30-0");
		entry.endDate = getDate("2013-11-26-13-30-0");
		entry.note = "This is a note.";
		entry.colourCode = "orange";
		list.add(entry);
		
		entry.id = 7;
		entry.startDate = getDate("2013-11-26-13-30-0");
		entry.endDate = getDate("2013-11-26-18-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry.id = 8;
		entry.startDate = getDate("2013-11-26-18-0-0");
		entry.endDate = getDate("2013-11-26-23-30-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		//Wednesday
		entry.id = 9;
		entry.startDate = getDate("2013-11-27-1-0-0");
		entry.endDate = getDate("2013-11-27-9-0-0");
		entry.note = "This is a note.";
		//entry.project = new Project()
		entry.colourCode = "green";
		list.add(entry);
		
		entry.id = 10;
		entry.startDate = getDate("2013-11-27-9-0-0");
		entry.endDate = getDate("2013-11-27-17-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry.id = 11;
		entry.startDate = getDate("2013-11-27-17-0-0");
		entry.endDate = getDate("2013-11-27-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		
		//Thursday
		entry.id = 12;
		entry.startDate = getDate("2013-11-28-1-0-0");
		entry.endDate = getDate("2013-11-28-9-3-0");
		entry.note = "This is a note.";
		//entry.project = new Project()
		entry.colourCode = "green";
		list.add(entry);
		
		entry.id = 13;
		entry.startDate = getDate("2013-11-28-9-3-0");
		entry.endDate = getDate("2013-11-28-18-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry.id = 14;
		entry.startDate = getDate("2013-11-28-17-0-0");
		entry.endDate = getDate("2013-11-28-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		//Friday
		entry.id = 15;
		entry.startDate = getDate("2013-11-29-1-0-0");
		entry.endDate = getDate("2013-11-29-9-0-0");
		entry.note = "This is a note.";
		//entry.project = new Project()
		entry.colourCode = "green";
		list.add(entry);
		
		entry.id = 16;
		entry.startDate = getDate("2013-11-29-9-0-0");
		entry.endDate = getDate("2013-11-29-12-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "red";
		list.add(entry);
		
		entry.id = 17;
		entry.startDate = getDate("2013-11-29-12-0-0");
		entry.endDate = getDate("2013-11-29-18-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry.id = 18;
		entry.startDate = getDate("2013-11-29-18-0-0");
		entry.endDate = getDate("2013-11-29-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		return list;
	}
}
