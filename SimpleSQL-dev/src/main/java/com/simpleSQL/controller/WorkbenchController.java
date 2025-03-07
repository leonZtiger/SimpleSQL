package com.simpleSQL.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import com.simpleSQL.database.DatabaseConnection;
import com.simpleSQL.model.ProjectModel;
import com.simpleSQL.model.WorkbenchModel;
import com.simpleSQL.propertyWindow.PropertiesWindow;
import com.simpleSQL.view.MenuBarView;
import com.simpleSQL.view.WelcomePage;
import com.simpleSQL.view.WorkbenchView;
import com.simpleSQL.view.project.ProjectView;

/**
 * Controller for managing the interactions between the WorkbenchView, WorkbenchModel, and MenuBarView.
 * Sets up listeners for menu actions and handles creating, opening, saving, and closing projects.
 */
public class WorkbenchController {

    private MenuBarView menuBarView;
    private WorkbenchModel workbenchModel;
    private WorkbenchView workbenchView;

    /**
     * Constructs a WorkbenchController to coordinate interactions between the menu bar, workbench view, and model.
     *
     * @param menuBarView     the menu bar view containing menu items
     * @param workbenchView   the workbench view where projects are displayed in tabs
     * @param workbenchModel  the workbench model managing project data
     */
    public WorkbenchController(MenuBarView menuBarView, WorkbenchView workbenchView, WorkbenchModel workbenchModel) {
        this.menuBarView = menuBarView;
        this.workbenchView = workbenchView;
        this.workbenchModel = workbenchModel;

        // Initialize the welcome tab
        workbenchView.addClosableTab("Welcome!", new WelcomePage(), null);

        // Set up all action listeners
        setupMenuListeners();
    }

    /**
     * Sets up the action listeners for the menu bar options.
     * Each listener handles a specific menu action.
     */
    private void setupMenuListeners() {
        menuBarView.addOpenActionListener(e -> openProject());
        menuBarView.addNewActionListener(e -> createNewProject());
        menuBarView.addSaveActionListener(e -> workbenchModel.getCurrentProject().save());
        menuBarView.addSaveAsActionListener(e -> workbenchModel.getCurrentProject().saveAs());
        menuBarView.addExitActionListener(e -> workbenchModel.exitProject());
        menuBarView.addUndoActionListener(e -> workbenchModel.getCurrentProject().undo());
        menuBarView.addRedoActionListener(e -> workbenchModel.getCurrentProject().redo());
        menuBarView.addFormatActionListener(e -> workbenchModel.getCurrentProject().format());
        menuBarView.addSetConnectionActionListener(e -> setDatabaseConnection());
        menuBarView.addPropertiesActionListener(e -> showPropertiesWindow());
        menuBarView.addBuildActionListener(e -> workbenchModel.getCurrentProject().build());
    }

    /**
     * Opens an existing project by displaying a file explorer to the user.
     * If a project is selected, it is added as a new tab in the workbench.
     */
    private void openProject() {
        ProjectModel newProjectModel = ProjectModel.openFromFileExplorer();
        if (newProjectModel != null) {
            addProjectTab(newProjectModel);
            workbenchModel.addProject(newProjectModel);
        }
    }

    /**
     * Creates a new project by displaying a creation dialog to the user.
     * If a project is created, it is added as a new tab in the workbench.
     */
    private void createNewProject() {
        ProjectModel newProjectModel = ProjectModel.createByUser();
        if (newProjectModel != null) {
            addProjectTab(newProjectModel);
            workbenchModel.addProject(newProjectModel);
        }
    }

    /**
     * Adds a new project tab to the workbench view and sets up a controller and a close action for it.
     *
     * @param projectModel the project model to be represented in the tab
     */
    private void addProjectTab(ProjectModel projectModel) {
        ProjectView projectView = new ProjectView();

        workbenchView.addClosableTab(projectModel.getName(), projectView, e -> {
            if (!projectModel.isSaved()) {
                int res = JOptionPane.showConfirmDialog(workbenchView, "Save before closing?");
                if (res == JOptionPane.YES_OPTION) {
                    projectModel.save();
                } else if (res == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }
            workbenchView.remove(projectView);
            workbenchModel.remove(projectModel);
        });

        // Initialize a ProjectController for managing this project tab
        new ProjectController(projectView, projectModel);
    }

    /**
     * Sets up a new database connection by prompting the user for credentials.
     * The new connection is applied to the current project if successfully created.
     */
    private void setDatabaseConnection() {
        DatabaseConnection newCon = new DatabaseConnection();
        if (newCon.askForCredentials()) {
            workbenchModel.getCurrentProject().setNewConnection(newCon);
        }
    }

    /**
     * Displays the properties window for the current project.
     * Opens a separate window where the user can view and edit project properties.
     */
    private void showPropertiesWindow() {
        new PropertiesWindow(workbenchModel.getCurrentProject());
    }
}
