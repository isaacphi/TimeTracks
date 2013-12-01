package com.timetracks;

import java.util.Date;
import java.util.GregorianCalendar;

public class ViewWindow {
	public static Date startDate;
	public static Date endDate;
	
	public static void calculateDates() {
		startDate = new GregorianCalendar(2013,12,1,0,0,0).getTime();
		endDate   = new GregorianCalendar(2013,12,7,0,0,0).getTime();
	}
}
