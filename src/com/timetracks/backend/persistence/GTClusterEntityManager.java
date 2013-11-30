package com.timetracks.backend.persistence;

import java.util.List;

import com.timetracks.models.GTCluster;

public class GTClusterEntityManager {
	public GTCluster getClusterById(int id) {
		// TODO
		return null;
	}
	public List<GTCluster> getClustersIntersecting(double cx, double cy, double radius) {
		// TODO
		return null;
	}
	
	// if GTCluster is in database, update the row, otherwise create
	public boolean createGTCluster(GTCluster cluster) {
		return false;
	}
	public boolean updateGTCluster(GTCluster cluster) {
		return false;
	}
}
