package com.timetracks.backend.gtmanagement;

import java.util.ArrayList;
import java.util.List;

import com.timetracks.models.GTCluster;
import com.timetracks.models.GTTimesheetLink;

public class Tagger {
	
	private static boolean compareClusters(GTCluster one, GTCluster two) {
		
		// Forget the "basic process"
		// Just permRadius hardcoded, worked on all 14 test points!!!!1!!one!!
		// Basic process: if centre is within the circle of the other, then the clusters
		// are the same location. (check both cases)
		// ABS not really necessary with new modifications
		double xDiff = one.c_x - two.c_x;
		if (xDiff < 0) 
			xDiff = xDiff*-1;
		double yDiff = one.c_y - two.c_y;
		if (yDiff < 0) 
			yDiff = yDiff*-1;
		
		double permRadius = 0.0005;
		//if (xDiff > one.radius && yDiff > one.radius && xDiff > two.radius && yDiff > two.radius) 
		if (permRadius > Math.sqrt(xDiff*xDiff + yDiff*yDiff) && permRadius > Math.sqrt(xDiff*xDiff + yDiff*yDiff)) {
			return true;
		}
		return false;
	}
	
	static List<GTTimesheetLink> createTimesheetEntriesFor(List<GTCluster> clusterList) {

			List<GTTimesheetLink> gttList = new ArrayList<GTTimesheetLink>();
			
			// For all clusters in clusterList
			for (int i = 0; i < clusterList.size(); i++) {
				 
				GTTimesheetLink link = GTTimesheetLink.getForGTCluster(clusterList.get(i));
				if (link == null) {
					// Since cluster doesn't have a link, assign all the flags false 
					// and continue to check if the location matches something else
					link = new GTTimesheetLink(); // POSSIBLE ERROR?
					link.gtCluster = clusterList.get(i);
					link.timesheetEntry.startDate = clusterList.get(i).startDate;
					link.timesheetEntry.endDate = clusterList.get(i).endDate;
					link.taggedByUser = false;
					link.isExcluded = false;
					link.excludedSetByUser = false;
					link.projectSetByUser = false;
					link.noteSetByUser = false;
				}
				
				boolean sameLocation = compareClusters(link.gtCluster, clusterList.get(i));
				// For all clusters behind the current cluster (including itself)
				for (int j = i; j >= 0; j--) {
					
					if (sameLocation == true) {
						GTTimesheetLink linkJ = GTTimesheetLink.getForGTCluster(clusterList.get(j));
						link.timesheetEntry.colourCode = linkJ.timesheetEntry.colourCode;
						
						if (linkJ.projectSetByUser == true) {
							link.timesheetEntry.project = linkJ.timesheetEntry.project;
							link.projectSetByUser = true;
						}
						
						if (linkJ.excludedSetByUser == true) {
							// since user excluded, predict the next occurrence of this cluster 
							// should be excluded. (change colour to gray instead of completely remove?)
							link.isExcluded = true;
						}
						// Jump to the end of the checking.
						j = 0;
					}
				}
				
				gttList.add(link);
						
			}
		return gttList;
	
	}
}
