package com.timetracks;

import com.timetracks.backend.BackendInterface;
import com.timetracks.backend.StubbedBackend;

public class BackendInjector {
	public static BackendInterface getBackend() {
		return new StubbedBackend();
		//return new BootstrappableBackend();
	}
}
