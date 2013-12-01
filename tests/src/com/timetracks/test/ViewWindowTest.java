package com.timetracks.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import com.timetracks.Helpers;
import com.timetracks.ViewWindow;

public class ViewWindowTest extends TestCase {
	public void testSanity() {
		Calendar c = new GregorianCalendar(2013, 10, 30, 0, 0, 0);
		assertEquals(Calendar.NOVEMBER, c.get(Calendar.MONTH));
		assertEquals(Calendar.SATURDAY, c.get(Calendar.DAY_OF_WEEK));
	}
	public void testTimes() {
		String[][] testCases = {
				{ "2013-10-30-0-0-0", "2013-10-24-0-0-0", "2013-10-30-0-0-0" },
				{ "2013-11-01-0-0-0", "2013-11-01-0-0-0", "2013-11-07-0-0-0" },
				{ "2013-11-02-0-0-0", "2013-11-01-0-0-0", "2013-11-07-0-0-0" },
				{ "2013-11-03-0-0-0", "2013-11-01-0-0-0", "2013-11-07-0-0-0" },
				{ "2013-11-04-0-0-0", "2013-11-01-0-0-0", "2013-11-07-0-0-0" },
				{ "2013-11-05-0-0-0", "2013-11-01-0-0-0", "2013-11-07-0-0-0" },
				{ "2013-11-06-0-0-0", "2013-11-01-0-0-0", "2013-11-07-0-0-0" },
				{ "2013-11-07-0-0-0", "2013-11-01-0-0-0", "2013-11-07-0-0-0" },
				{ "2013-11-08-0-0-0", "2013-11-08-0-0-0", "2013-11-14-0-0-0" },
			};
		for(String[] strings : testCases) {
			Date maxDbDate   = Helpers.dateHelper(strings[0]);
			Date e_startDate = Helpers.dateHelper(strings[1]);
			Date e_endDate   = Helpers.dateHelper(strings[2]);
			
			ViewWindow.calculateDates(maxDbDate);
			assertEquals(strings[0], e_startDate, ViewWindow.startDate);
			assertEquals(strings[0], e_endDate, ViewWindow.endDate);
		}
	}
}
