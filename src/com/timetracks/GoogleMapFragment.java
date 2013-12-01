package com.timetracks;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.timetracks.models.GTCluster;
import com.timetracks.models.TimesheetEntry;

public class GoogleMapFragment extends Fragment {

	private GoogleMap googleMap;
	static final LatLng TORONTO = new LatLng(43.670852, -79.376435);
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_map_fragment, container, false);
        try {
			initializeMap();
			// Now make a large
		} catch (Exception e) {
			e.printStackTrace();
		}
        return rootView;
    }

	private void initializeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getActivity().getFragmentManager().findFragmentById(
					R.id.map)).getMap(); 
			/*
			Marker toronto = googleMap.addMarker(new MarkerOptions().position(
					TORONTO).title("Toronto"));
			// Move the camera instantly to hamburg with a zoom of 15.
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TORONTO, 15));

			// Zoom in, animating the camera.
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getActivity().getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
						
			}*/
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		initializeMap();
		// Recognize where we're getting data from now.
		Intent i = getActivity().getIntent();
		if (i != null && i.getExtras() != null)	{
			// If neither of these is null, we know that it's coming from calendar view, and view on map
			Bundle bundle = i.getExtras();
			List<TimesheetEntry> timesheetEntryList = (List<TimesheetEntry>) bundle.getSerializable(TimesheetEntry.TIMESHEET_ENTRY_TAG);
			List<GTCluster> gtClusterList = (List<GTCluster>) bundle.getSerializable(GTCluster.GT_CLUSTER_TAG);
			
			// Do a marker colour lookup
			// TODO: Need to make this variable on the timesheetEntry.colourCode
			int colour = getResources().getColor(R.color.blue);
			for (int j = 0; j < timesheetEntryList.size(); j++)	{
				TimesheetEntry timeSheetEntry = timesheetEntryList.get(j);
				GTCluster gtCluster = gtClusterList.get(j);
				
				MarkerOptions marker = new MarkerOptions().position(new LatLng(gtCluster.c_x, gtCluster.c_y));
				marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
			}
			
			
			
			
			
		}
		
	}
}

