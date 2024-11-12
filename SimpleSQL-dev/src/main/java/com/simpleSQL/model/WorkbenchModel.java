package com.simpleSQL.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Model representing the main workbench, which manages multiple projects.
 * Provides methods to add, remove, and manage the lifecycle of projects.
 */
public class WorkbenchModel {

    // A set of projects currently managed by the workbench
    private final Set<ProjectModel> projects;

    /**
     * Constructs a WorkbenchModel instance, initializing the project set.
     */
    public WorkbenchModel() {
        projects = new HashSet<>();
    }

    /**
     * Retrieves the currently active project.
     *
     * @return the current ProjectModel, or null if no project is active
     */
    public ProjectModel getCurrentProject() {
        // TODO: Implement logic to determine and return the current project
        return null;
    }

    /**
     * Adds a new project to the workbench.
     *
     * @param newProjectModel the ProjectModel to add
     */
    public void addProject(ProjectModel newProjectModel) {
        projects.add(newProjectModel);
    }

    /**
     * Exits the current project (stub for further implementation).
     */
    public void exitProject() {
        // TODO: Implement project exit functionality
    }

    /**
     * Removes a specified project from the workbench.
     *
     * @param projectModel the ProjectModel to remove
     */
    public void remove(ProjectModel projectModel) {
        projects.remove(projectModel);
    }
}
