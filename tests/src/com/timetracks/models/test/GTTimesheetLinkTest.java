package com.timetracks.models.test;

import junit.framework.TestCase;
import com.timetracks.models.GTTimesheetLink;

public class GTTimesheetLinkTest extends TestCase {
	public void test() {
		GTTimesheetLink link = new GTTimesheetLink();
		assertFalse(link.excludedSetByUser);
		assertFalse(link.noteSetByUser);
		assertFalse(link.projectSetByUser);
		assertFalse(link.taggedByUser);
	}

}
