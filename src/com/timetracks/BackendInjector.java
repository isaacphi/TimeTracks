package com.timetracks;

import com.timetracks.backend.BackendInterface;
import com.timetracks.backend.BootstrappableBackend;

public class BackendInjector {
	public static BackendInterface getBackend() {
		//return BootstrappedBackend.getInstance();
		return new BootstrappableBackend();
	}
}
