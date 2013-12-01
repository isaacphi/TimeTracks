package com.timetracks.bootstrapping;

import java.util.List;

import com.timetracks.models.GTCluster;
import com.timetracks.bootstrapping.GTClusterValues;

public class DatabaseBootstrap {

	public static void BootstrapDatabase() { 
		
		List<GTCluster> gtcList = GTClusterValues.getClusters();
		
		// Take each element and put it into database
		
		
	}
}
