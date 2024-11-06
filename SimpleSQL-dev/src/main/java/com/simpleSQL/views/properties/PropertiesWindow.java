package com.simpleSQL.views.properties;

import javax.sound.midi.VoiceStatus;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.IconUIResource;

import com.simpleSQL.models.ProjectModel;
import com.simpleSQL.models.TempLocalPreferences;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PropertiesWindow extends JDialog {

	public PropertiesWindow(ProjectModel projectModel) {
		super();
		
		this.setTitle("Properties");
		this.setModal(true);

		setSize(new Dimension(600, 500));
		setLayout(new BorderLayout());

		// Categories for settings (these could be expanded or made dynamic)
		String[] categories = { "General", "Appearance", "Editor", "Database", "Project" };
		JList<String> categoryList = new JList<>(categories);
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Panel to display selected settings page
		JPanel settingsPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		settingsPanel.setLayout(cardLayout);
		
		// Add selection listener to switch settings page on category selection
		categoryList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				String selectedCategory = categoryList.getSelectedValue();
				cardLayout.show(settingsPanel, selectedCategory);
			}
		});

		TempLocalPreferences tempPreferences = new TempLocalPreferences();

		// Initialize and add each settings page
		settingsPanel.add(new General(tempPreferences), "General");
		settingsPanel.add(new Appearance(tempPreferences), "Appearance");
		settingsPanel.add(new Editor(tempPreferences), "Editor");
		settingsPanel.add(new DatabaseConnection(tempPreferences), "Database");
		settingsPanel.add(new Project(tempPreferences), "Project");

		// Create Apply and cancel button
		JButton applyAndClose = new JButton("Apply and close");

		applyAndClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tempPreferences.apply();
				dispose();
			}
		});

		JButton apply = new JButton("Apply");

		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tempPreferences.apply();
			}
		});

		JButton cancel = new JButton("Close");

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Apply changes before closing?", "", JOptionPane.YES_NO_OPTION);

				if (i == 0)
					tempPreferences.apply();

				dispose();
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(applyAndClose);
		buttonPanel.add(apply);
		buttonPanel.add(cancel);

		// Layout setup: list on the left, settings panel on the right
		add(new JScrollPane(categoryList), BorderLayout.WEST);
		add(settingsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
		pack();
	}

}
