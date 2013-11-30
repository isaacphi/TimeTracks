package com.timetracks.bootstrapping;

import java.util.List;

import com.timetracks.models.TimesheetEntry;

public class TimesheetEntryXMLAdapter {
	public String xml;
	public TimesheetEntryXMLAdapter(String xml) {
		this.xml = xml;
	}
	
	public static List<TimesheetEntry> parseFile(String filename) {
		// load xml from file;
		//xml = getXMLFromFile(filename);
		//return TimesheetEntryXMLAdapater(xml).getEntries();
		return null;
	}
}
