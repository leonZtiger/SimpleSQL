package com.simpleSQL.views.properties;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.simpleSQL.models.ProjectModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PropertiesWindow extends JFrame {

	private ProjectModel projectModel;
	private JList<String> categoryList;
	private JPanel settingsPanel;
	private CardLayout cardLayout;

	public PropertiesWindow(ProjectModel projectModel) {
		super("Project Properties");
		this.projectModel = projectModel;

		setSize(new Dimension(600, 500));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		// Categories for settings (these could be expanded or made dynamic)
		String[] categories = { "General", "Appearance", "Editor", "Database","Project" };
		categoryList = new JList<>(categories);
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Add selection listener to switch settings page on category selection
		categoryList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				String selectedCategory = categoryList.getSelectedValue();
				cardLayout.show(settingsPanel, selectedCategory);
			}
		});

		// Panel to display selected settings page
		settingsPanel = new JPanel();
		cardLayout = new CardLayout();
		settingsPanel.setLayout(cardLayout);

		// Initialize and add each settings page
		settingsPanel.add(new General(), "General");
		settingsPanel.add(new Appearance(), "Appearance");
		settingsPanel.add(new Editor(), "Editor");
		settingsPanel.add(new DatabaseConnection(), "Database");
		settingsPanel.add(new Project(), "Project");

		// Layout setup: list on the left, settings panel on the right
		add(new JScrollPane(categoryList), BorderLayout.WEST);
		add(settingsPanel, BorderLayout.CENTER);

		setVisible(true);
		pack();
	}
}
