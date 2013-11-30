package com.timetracks.bootstrapping;

import java.util.List;

import com.timetracks.models.GTCluster;

public class DatabaseBootstrap {
	private static final String GTCLUSTER_FILENAME="";
	public static void BootstrapDatabase() {
		List<GTCluster> = GTClusterXMLLoader.loadFromFile(GTCLUSTER_FILENAME);
		// and more 
		
		
		// put in database
	}
}
