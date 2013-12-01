package com.timetracks;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ViewWindow {
	public static Date startDate;
	public static Date endDate;
	
	public static void calculateDates(Date maxDbDate) {
		Calendar c = new GregorianCalendar();
		c.setTime(maxDbDate);
		while(c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			c.add(Calendar.DAY_OF_WEEK, 1);
		}
		endDate = c.getTime();
		c.add(Calendar.DAY_OF_WEEK, -6);
		startDate = c.getTime();
	}
}
