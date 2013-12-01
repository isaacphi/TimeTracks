package com.timetracks.backend.gtmanagement;

import java.util.Date;
import java.util.List;

import com.timetracks.models.GTCluster;
import com.timetracks.models.GTTimesheetLink;
import com.timetracks.models.Project;

public class Tagger {
	static List<GTTimesheetLink> createTimesheetEntriesFor(List<GTCluster> clusterList) {
	
		// if cluster exists:
			// FOR ALL CLUSTERS IN GTCLUSTERLIST
			for (int i = 0; i < clusterList.size(); i++) {
				GTTimesheetLink link = new GTTimesheetLink();
				
				// For all clusters that already have been tagged
				for (int j = i; j > 0; j--) {
					// Run similarity on the cluster(s)
					// if excludedSetByUser : dont run (do you need to know if they're same location?)
					// boolean decision = compareClusters(cluster(i), cluster(j), parameters);
					// if decision :
					// 		- match colours
					//		- match project name (if it exists - taggedByUser == true)
					// else 
					//		- new colour
				}
		//else: (cluster doesnt exist)	
			// Save cluster
			link.gtCluster = clusterList.get(i);
			link.timesheetEntry.startDate = clusterList.get(i).startDate;
			link.timesheetEntry.endDate = clusterList.get(i).endDate;
			link.taggedByUser = false;
			link.isExcluded = false;
			link.excludedSetByUser = false;
			link.projectSetByUser = false;
			link.noteSetByUser = false;

					
					
		
					
		}
		
		
		return null;
	
	}
}
