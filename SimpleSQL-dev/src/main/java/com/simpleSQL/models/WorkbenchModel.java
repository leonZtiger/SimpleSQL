package com.simpleSQL.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkbenchModel {

	private Set<ProjectModel> projects;

	public WorkbenchModel() {
		projects = new HashSet<ProjectModel>();
	}

	public ProjectModel getCurrentProject() {
		// TODO returns the project in the active tab
		return null;
	}

	public void addProject(ProjectModel newProjectModel) {
		projects.add(newProjectModel);
	}

	public void exitProject() {
		// TODO Auto-generated method stub

	}

	public void remove(ProjectModel newProjectModel) {
		projects.remove(newProjectModel);
	}

}
