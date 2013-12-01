package com.timetracks.backend.test;

import java.util.List;

import android.test.ApplicationTestCase;

import com.roscopeco.ormdroid.ORMDroidApplication;
import com.timetracks.backend.Backend;
import com.timetracks.models.Project;

public class BackendTest extends ApplicationTestCase<ORMDroidApplication>{
	Backend backend;
	
	public BackendTest() {
		super(ORMDroidApplication.class);
	}
	
	public void setUp() throws Exception {
		super.setUp();
		this.backend = new Backend();
	}
	
	public void testBasicPersistence() {
		assertEquals(0, backend.getAllProjects().size());
		
		final String someProjectName = "684 Harbord"; 
		Project p = new Project();
		p.name = someProjectName;
		p.save();
		
		List<Project> allProjects = backend.getAllProjects();
		assertEquals(1, allProjects.size());
		assertEquals(someProjectName, allProjects.get(0).name);
	}
}
