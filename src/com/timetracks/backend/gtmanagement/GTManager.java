package com.timetracks.backend.gtmanagement;

import static com.roscopeco.ormdroid.Query.and;
import static com.roscopeco.ormdroid.Query.geq;
import static com.roscopeco.ormdroid.Query.leq;
import static com.roscopeco.ormdroid.Query.or;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.roscopeco.ormdroid.Entity;
import com.roscopeco.ormdroid.Query.SQLExpression;
import com.timetracks.models.GTCluster;
import com.timetracks.models.GTPoint;
import com.timetracks.models.GTTimesheetLink;

public class GTManager {
	/**
	 * Get GTTimesheetLink objects for a region.
	 * This function may do some summarization of database state, but
	 * attempts to not duplicate this work.
	 *  
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<GTTimesheetLink> getLinks(Date startDate, Date endDate) {
		// calculate area that has not been summarized?
		List<GTTimesheetLink> baseLinks=null;
		
		// any clusters in this region? take the latest one.
		GTCluster cluster = Entity.query(GTCluster.class).where(and(geq("startDate", startDate),
																	leq("startDate", endDate))).execute();
		if(cluster != null) {
			// We should see this period as summarized;
			// return only what's already in the database.
			if(cluster.endDate.after(endDate)) {
				return this.getLinksFromDatabase(startDate, endDate);
			}
			baseLinks = this.getLinksFromDatabase(startDate, cluster.endDate);
			startDate = cluster.endDate;
		}
		// TODO: we potentially get a more compact representation if we remove
		// this intersecting cluster and summarize over that region again. The issue
		// with that removal is that we want to save any user-added annotations
		
		List<GTTimesheetLink> links = this.generateLinks(startDate, endDate);
		if(baseLinks != null) {
			baseLinks.addAll(links);
			links = baseLinks;
		}
		return links;
	}
	
	public List<GTTimesheetLink> getLinksFromDatabase(Date startDate, Date endDate) {
		// I want all GTTimesheetLinks associated with a date range in question
		// Unfortunately, given the ORM, we can't join tables, so we retrieve
		// all matching clusters, then lookup the GTTimesheetLink objects one 
		// by one. This could be more efficient, but I don't care to change
		// things right now.
		List<GTCluster> clusterList;
		SQLExpression where = or(and(geq("startDate", startDate),leq("startDate", endDate)),
                                 and(geq("endDate", startDate),leq("endDate", endDate)));
		clusterList = Entity.query(GTCluster.class).where(where).executeMulti();
		
		List<GTTimesheetLink> linkList = new ArrayList<GTTimesheetLink>();
		for(GTCluster cluster:clusterList) {
			GTTimesheetLink link = GTTimesheetLink.getForGTCluster(cluster);
			if(link!=null) {
				linkList.add(link);
			}
		}
		return linkList;
	}
	
	/**
	 * GTPoint->GTCluster->GTTimesheet link pipeline
	 * 
	 * Summarize any GTPoints that exist between startDate and endDate 
	 * 
	 * There are lots of contingencies here. How do we determine this period?
	 * Do we assume that there are no clusters already in the database within
	 * this range? If there is a GTCluster that butts up against the start or
	 * endDate, do we 
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<GTTimesheetLink> generateLinks(Date startDate, Date endDate) {
		List<GTPoint> pointList = Entity.query(GTPoint.class).where(and(geq("dates", startDate), leq("dates", endDate))).executeMulti();
		
		List<GTCluster> clusterList = Clusterer.cluster(pointList);
		for(GTCluster cluster:clusterList) {
			cluster.save();
		}
		
		/* check if these objects already exist in the database? */
		/*List<GTCluster> newClusters = new ArrayList<GTCluster>();
		for(GTCluster cluster:clusterList) {
			if(!GTCluster.alreadyStored(cluster)) {
				cluster.save();
				newClusters.add(cluster);
			}
		}*/
		List<GTTimesheetLink> gtTimesheetLinks = Tagger.createTimesheetEntriesFor(clusterList);
		if(gtTimesheetLinks != null) {
			for(GTTimesheetLink link:gtTimesheetLinks) {
				link.timesheetEntry.save();
				link.save();
			}
		}
		return gtTimesheetLinks;
	}
}
