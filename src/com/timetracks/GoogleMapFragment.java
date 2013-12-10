package com.timetracks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.timetracks.backend.BackendInterface;
import com.timetracks.models.GTCluster;
import com.timetracks.models.Project;

public class GoogleMapFragment extends Fragment {
	
	public final static String TAG = "from_map";

	private GoogleMap googleMap;
	static final LatLng TORONTO = new LatLng(43.670852, -79.376435);
	private static final int ID_VIEW_OTHER_FRAGMENT = 1;
	private static final int ID_TAG = 2;
	private static final int ID_EXCLUDE = 3;
	
	OnCalendarSelectedListener mCalendarListener;

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Set the asynchronous tasks going
        new PopulateMapTask().execute();
		
        View rootView = inflater.inflate(R.layout.activity_map_fragment, container, false);
        try {
			initializeMap();
			// Now make a large
		} catch (Exception e) {
			e.printStackTrace();
		}
        googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0) {
				View v = getView();
				QuickAction quickAction = setQuickActionButtons();	
				quickAction.show(v);
				return true;
			}
		});

        return rootView;
    }

	private void initializeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getActivity().getFragmentManager().findFragmentById(
					R.id.map)).getMap(); 
		}

	}

	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
        try {
            mCalendarListener = (OnCalendarSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCalendarSelectedListener");
        }
	}

	@Override
	public void onResume() {
		super.onResume();
		initializeMap();	
	}
	
	
	public QuickAction setQuickActionButtons()	{
		
		Intent intent = getActivity().getIntent();
		
		// Should have variable text and logos...in onResume?
		ActionItem viewOtherFragment;
		if (intent.getStringExtra("Activity") == "GoogleMapFragment")	
			viewOtherFragment = new ActionItem(ID_VIEW_OTHER_FRAGMENT, "Map", getResources().getDrawable(R.drawable.ic_action_map));
		else
			viewOtherFragment = new ActionItem(ID_VIEW_OTHER_FRAGMENT, "Calendar", getResources().getDrawable(R.drawable.ic_action_event));
		
		ActionItem tagItem;
		// Need to detect things from DB before wiring this up. We'll use the same symbol though
		tagItem = new ActionItem(ID_TAG, "Tag", getResources().getDrawable(R.drawable.ic_action_new_label));
		ActionItem exclude = new ActionItem(ID_EXCLUDE, "Exclude", getResources().getDrawable(R.drawable.ic_action_cancel));
		
		final QuickAction quickAction = new QuickAction(getActivity(), QuickAction.HORIZONTAL);
		
		quickAction.addActionItem(viewOtherFragment);
		quickAction.addActionItem(tagItem);
		quickAction.addActionItem(exclude);

		
		quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction quickAction, int pos, int actionId) {
				ActionItem actionItem = quickAction.getActionItem(pos);
				
				if (actionId == ID_VIEW_OTHER_FRAGMENT) {
					// Instead of sending Intent and activity here, use interface to wire over
					mCalendarListener.onCalendarSelected(null); // TODO: get Project here.
					
				} else if (actionId == ID_TAG) {
					Intent i2 = new Intent(getActivity(), TagProjectActivity.class);
					startActivity(i2);
				} else {
					Intent i3 = new Intent(getActivity(), ExcludeLocationActivity.class);
					
				}
			}
		});
		
		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
			@Override
			public void onDismiss() {
				// Nothing to display, just close the window naturally
			}
		});
		
		return quickAction;
	} 
	
	/**
	 * We want to call this asynchronous task at the start of every onCreate instance. We will get the time sheet entries
	 * from the beginning of the week (Sunday), and then add the tags to the map. We will pick up the project info if 
	 * it's already been programmed. 
	 * @author Ivan
	 *
	 */
	private class PopulateMapTask extends AsyncTask<Void, Void, List<LatLng>> {
		float[] colours = {BitmapDescriptorFactory.HUE_BLUE, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_YELLOW, BitmapDescriptorFactory.HUE_GREEN, BitmapDescriptorFactory.HUE_ORANGE, BitmapDescriptorFactory.HUE_AZURE};
		
		@Override
		protected List<LatLng> doInBackground(Void...voids) {
			BackendInterface dao = BackendInjector.getBackend();
			ViewWindow.calculateDates(dao.getMaxTimesheetEntryDate());
			Date startDate = ViewWindow.startDate;
			Date endDate = ViewWindow.endDate;

			List<GTCluster> clusters = dao.getGTClusters(startDate, endDate);

			// Now populate the data, and prep data to be sent to the fragments
			// For maps, we want c_x, c_y to reverse geocode, and display the dialog
			// How do we account for 
			List<LatLng> list = new ArrayList<LatLng>();
			for (GTCluster cluster : clusters) {
				LatLng latlng = new LatLng(cluster.c_x, cluster.c_y);
				list.add(latlng);
			}
			return list;
		}

		@Override
		protected void onPostExecute(List<LatLng> result) {
			// This is where we populate the markers in Google Maps!
			int count = 0;
			for (LatLng latlng : result){
				MarkerOptions options = new MarkerOptions();
				options.position(latlng);
				options.icon(BitmapDescriptorFactory.defaultMarker(colours[count%colours.length]));
				googleMap.addMarker(options);
				count++;
			}
			if(result!=null &&result.size()>0) {
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(result.get(0), 12));
			}
		}
	}
	
	public interface OnCalendarSelectedListener {
        public void onCalendarSelected(Project project);
    }
}

