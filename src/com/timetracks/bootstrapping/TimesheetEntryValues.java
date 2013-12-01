package com.timetracks.bootstrapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.timetracks.Helpers;
import com.timetracks.models.Project;
import com.timetracks.models.TimesheetEntry;

public class TimesheetEntryValues {

	public static Date getDate(String hyphenatedString) {
		return Helpers.dateHelper(hyphenatedString);
	}
	
	public static List<TimesheetEntry> getEntries() {
		List<TimesheetEntry> list = new ArrayList<TimesheetEntry>();
		TimesheetEntry entry;
		
		Project proj = new Project();
		
		
		// Monday
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 1;
		entry.startDate = getDate("2013-10-25-1-0-0");
		entry.endDate = getDate("2013-10-25-9-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);

		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 2;
		entry.startDate = getDate("2013-10-25-9-0-0");
		entry.endDate = getDate("2013-10-25-17-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 3;
		entry.startDate = getDate("2013-10-25-17-0-0");
		entry.endDate = getDate("2013-10-25-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		// Tuesday
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 4;
		entry.startDate = getDate("2013-10-26-1-0-0");
		entry.endDate = getDate("2013-10-26-10-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 5;
		entry.startDate = getDate("2013-10-26-9-0-0");
		entry.endDate = getDate("2013-10-26-12-30-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 6;
		entry.startDate = getDate("2013-10-26-12-30-0");
		entry.endDate = getDate("2013-10-26-13-30-0");
		entry.note = "This is a note.";
		entry.colourCode = "orange";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 7;
		entry.startDate = getDate("2013-10-26-13-30-0");
		entry.endDate = getDate("2013-10-26-18-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 8;
		entry.startDate = getDate("2013-10-26-18-0-0");
		entry.endDate = getDate("2013-10-26-23-30-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		//Wednesday
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 9;
		entry.startDate = getDate("2013-10-27-1-0-0");
		entry.endDate = getDate("2013-10-27-9-0-0");
		entry.note = "This is a note.";
		//entry.project = new Project()
		entry.colourCode = "green";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 10;
		entry.startDate = getDate("2013-10-27-9-0-0");
		entry.endDate = getDate("2013-10-27-17-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 11;
		entry.startDate = getDate("2013-10-27-17-0-0");
		entry.endDate = getDate("2013-10-27-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		
		//Thursday
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 12;
		entry.startDate = getDate("2013-10-28-1-0-0");
		entry.endDate = getDate("2013-10-28-10-3-0");
		entry.note = "This is a note.";
		//entry.project = new Project()
		entry.colourCode = "green";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 13;
		entry.startDate = getDate("2013-10-28-10-3-0");
		entry.endDate = getDate("2013-10-28-18-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 14;
		entry.startDate = getDate("2013-10-28-17-0-0");
		entry.endDate = getDate("2013-10-28-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		//Friday
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 15;
		entry.startDate = getDate("2013-10-29-1-0-0");
		entry.endDate = getDate("2013-10-29-9-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 16;
		entry.startDate = getDate("2013-10-29-9-30-0");
		entry.endDate = getDate("2013-10-29-12-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "red";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 17;
		entry.startDate = getDate("2013-10-29-12-0-0");
		entry.endDate = getDate("2013-10-29-18-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "blue";
		list.add(entry);
		
		entry = new TimesheetEntry();
		entry.project = proj;
		entry.id = 18;
		entry.startDate = getDate("2013-10-29-18-0-0");
		entry.endDate = getDate("2013-10-29-23-0-0");
		entry.note = "This is a note.";
		entry.colourCode = "green";
		list.add(entry);
		
		return list;
	}
}
