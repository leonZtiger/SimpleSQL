package com.simpleSQL.controllers;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.simpleSQL.database.DatabaseConnection;
import com.simpleSQL.models.ProjectModel;
import com.simpleSQL.models.WorkbenchModel;
import com.simpleSQL.views.MenuBarView;
import com.simpleSQL.views.ProjectView;
import com.simpleSQL.views.WorkbenchView;
import com.simpleSQL.views.properties.PropertiesWindow;

public class WorkbenchController {

	private MenuBarView menuBarView;
	private WorkbenchModel workbenchModel;
	private WorkbenchView workbenchView;

	public WorkbenchController(MenuBarView menuBarView, WorkbenchView workbenchView, WorkbenchModel workbenchModel) {
		this.menuBarView = menuBarView;
		this.workbenchView = workbenchView;
		this.workbenchModel = workbenchModel;

		// Configure all the listeners to there corresponding action
		menuBarView.addOpenActionListener(e -> {
			ProjectModel newProjectModel = ProjectModel.openFromFileExplorer();
			if (newProjectModel == null)
				return;
			ProjectView projectView = new ProjectView();

			workbenchView.addClosableTab(newProjectModel.getName(), projectView, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!newProjectModel.isSaved()) {
						int res = JOptionPane.showConfirmDialog(workbenchView, "Save before closing?");
						if (res == JOptionPane.YES_OPTION)
							newProjectModel.save();
						else if (res == JOptionPane.CANCEL_OPTION)
							return;
					}

					workbenchView.remove(projectView);
					workbenchModel.remove(newProjectModel);

				}
			});

			new ProjectController(projectView, newProjectModel);

			workbenchModel.addProject(newProjectModel);
		});

		menuBarView.addNewActionListener(e -> {
			ProjectModel newProjectModel = ProjectModel.createByUser();
			if (newProjectModel == null)
				return;

			ProjectView projectView = new ProjectView();

			workbenchView.addClosableTab(newProjectModel.getName(), projectView, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!newProjectModel.isSaved()) {
						int res = JOptionPane.showConfirmDialog(workbenchView, "Save before closing?");
						if (res == JOptionPane.YES_OPTION)
							newProjectModel.save();
						else if (res == JOptionPane.CANCEL_OPTION)
							return;
					}

					workbenchView.remove(projectView);
					workbenchModel.remove(newProjectModel);
				}
			});
			new ProjectController(projectView, newProjectModel);

			workbenchModel.addProject(newProjectModel);
		});

		menuBarView.addSaveActionListener(e -> {
			workbenchModel.getCurrentProject().save();
		});

		menuBarView.addSaveAsActionListener(e -> {
			workbenchModel.getCurrentProject().saveAs();
		});

		menuBarView.addExitActionListener(e -> {
			workbenchModel.exitProject();
		});

		menuBarView.addUndoActionListener(e -> {
			workbenchModel.getCurrentProject().undo();
		});

		menuBarView.addRedoActionListener(e -> {
			workbenchModel.getCurrentProject().redo();
		});

		menuBarView.addFormatActionListener(e -> {
			workbenchModel.getCurrentProject().format();
		});

		menuBarView.addSetConnectionActionListener(e -> {
			DatabaseConnection newCon = new DatabaseConnection();
			if (newCon.askForCredentials()) {
				workbenchModel.getCurrentProject().setNewConnection(newCon);
			}

		});

		menuBarView.addPropertiesActionListener(e -> {
			// opens a new properties window for user, a separate window from the main
			new PropertiesWindow(workbenchModel.getCurrentProject());
		});

		menuBarView.addBuildActionListener(e -> {
			workbenchModel.getCurrentProject().build();
		});
	}
}
