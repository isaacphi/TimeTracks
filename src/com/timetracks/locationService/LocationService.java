package com.timetracks.locationService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * LocationService
 * 
 * Intention: the service we run in the background, which polls 
 * the location api and records this data as GTPoints for later summarization.
 */
public class LocationService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	// Interacts with Location API to periodically poll location,
	// and push to database.

}
