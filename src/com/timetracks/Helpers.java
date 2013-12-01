package com.timetracks;

import java.util.Date;
import java.util.GregorianCalendar;

public class Helpers {
	/**
	 * Helper to create Date objects.
	 * @param hyphenatedDateString e.g. "2013-12-01-3-22-00"
	 * @return
	 */
	public static Date dateHelper(String hyphenatedString) {
		String[] pargs = hyphenatedString.split("-");
		GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(pargs[0]), Integer.parseInt(pargs[1]), Integer.parseInt(pargs[2]), Integer.parseInt(pargs[3]), Integer.parseInt(pargs[4]), Integer.parseInt(pargs[5]));
		return gc.getTime();	
	}
}
