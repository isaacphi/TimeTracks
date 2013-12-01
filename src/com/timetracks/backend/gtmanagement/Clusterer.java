package com.timetracks.backend.gtmanagement;

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

import com.timetracks.models.GTCluster;
import com.timetracks.models.GTPoint;


public class Clusterer {
	
	private static double average(List<Double> list) {
		
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		return sum/list.size();
	}
	
	private static double maximum(List<Double> list) {
		double max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) > max)
				max = list.get(i);
		}
		return max;
	}
	
	private static double minimum(List<Double> list) {
		double min = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) < min)
				min = list.get(i);
		}
		return min;
	}
	
	private static double max(double one, double two) {
		if (one > two)
			return one;
		else
			return two;
	}
	
	private static double absolute(double number) {
		if (number > 0) 
			return number;
		else
			return number*-1;	
	}
	
	public static List<GTCluster> cluster(List<GTPoint> pointList) {
		List<GTCluster> gtcClusterList = new ArrayList<GTCluster>();
		
		// Variables 
		double distThreshold = 0.0005;
		int timeStepThreshold = 15;
		boolean newLocation = false;
		int count = 0;
		List<Double> xList = new ArrayList<Double>();
		List<Double> yList = new ArrayList<Double>();
		
		for (int i = 0; i < pointList.size()-1; i++) {
			// Check if x and y are within a certain change in distance (distParam)
			if (absolute(pointList.get(i).x - pointList.get(i+1).x) < distThreshold) {
				count++;
				xList.add(pointList.get(i).x);
				yList.add(pointList.get(i).y);
				
				if (count > timeStepThreshold) {
					newLocation = true;
				}
			}
			else {
				if (newLocation == true) {
					// Add the location
					GTCluster cluster = new GTCluster();
					cluster.c_x = average(xList); // needs averaging
					cluster.c_y = average(yList);
					// Radius should be something smart, like stddev/sqrt(N), (or based on measurement error)
					cluster.radius = (max(maximum(xList)-minimum(xList), maximum(yList)-minimum(yList)))/(Math.sqrt((double)xList.size()));
					cluster.userProvided = false;
					cluster.startDate = pointList.get(i-count).dates;
					cluster.endDate = pointList.get(i).dates;
					gtcClusterList.add(cluster);
					newLocation = false;
					xList.clear();
					yList.clear();
				}
				count = 0;
			}
			// Catch the EOF
			if (i == pointList.size()-2) {
				if (newLocation == true) {
					// Add the location
					GTCluster cluster = new GTCluster();
					cluster.c_x = average(xList); // needs averaging
					cluster.c_y = average(yList);
					// Radius should be something smart, like stddev/sqrt(N), (or based on measurement error)
					cluster.radius = (max(maximum(xList)-minimum(xList), maximum(yList)-minimum(yList)))/(Math.sqrt((double)xList.size()));
					cluster.userProvided = false;
					cluster.startDate = pointList.get(i-count).dates;
					cluster.endDate = pointList.get(i).dates;
					gtcClusterList.add(cluster);
					newLocation = false;
					count = 0;
					xList.clear();
					yList.clear();
				}	
			}	
		}
		
		return gtcClusterList;
	}
}
