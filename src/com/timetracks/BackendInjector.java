package com.timetracks;

import com.timetracks.backend.BackendInterface;
import com.timetracks.backend.BootstrappedBackend;

public class BackendInjector {
	public static BackendInterface getBackend() {
		return BootstrappedBackend.getInstance();
	}
}
