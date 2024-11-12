package com.simpleSQL.propertyWindow;

import javax.swing.*;
import com.simpleSQL.model.ProjectModel;
import com.simpleSQL.model.TempLocalPreferences;
import com.simpleSQL.propertyWindow.propertyPage.Appearance;
import com.simpleSQL.propertyWindow.propertyPage.DatabaseConnection;
import com.simpleSQL.propertyWindow.propertyPage.Editor;
import com.simpleSQL.propertyWindow.propertyPage.General;
import com.simpleSQL.propertyWindow.propertyPage.Project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A properties dialog window that provides various application settings categories.
 * Allows users to adjust settings for General, Appearance, Editor, Database, and Project properties.
 * Changes can be applied temporarily or saved persistently.
 */
public class PropertiesWindow extends JDialog {

    /**
     * Constructs the PropertiesWindow dialog, initializing it with various settings categories.
     *
     * @param projectModel the current project model for accessing project-specific settings
     */
    public PropertiesWindow(ProjectModel projectModel) {
        super();

        setTitle("Properties");
        setModal(true);
        setSize(new Dimension(600, 500));
        setLayout(new BorderLayout());

        // Define categories for settings pages
        String[] categories = {"General", "Appearance", "Editor", "Database", "Project"};
        JList<String> categoryList = new JList<>(categories);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Panel for displaying the selected settings page
        JPanel settingsPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        settingsPanel.setLayout(cardLayout);

        // Listener to switch settings page on category selection
        categoryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedCategory = categoryList.getSelectedValue();
                cardLayout.show(settingsPanel, selectedCategory);
            }
        });

        // Initialize temporary preferences storage
        TempLocalPreferences tempPreferences = new TempLocalPreferences();

        // Add each settings page to the settings panel
        settingsPanel.add(new General(tempPreferences), "General");
        settingsPanel.add(new Appearance(tempPreferences), "Appearance");
        settingsPanel.add(new Editor(tempPreferences), "Editor");
        settingsPanel.add(new DatabaseConnection(tempPreferences), "Database");
        settingsPanel.add(new Project(tempPreferences), "Project");

        // Create Apply and Cancel buttons
        JButton applyAndCloseButton = new JButton("Apply and close");
        applyAndCloseButton.addActionListener(e -> {
            tempPreferences.apply();
            dispose();
        });

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> tempPreferences.apply());

        JButton cancelButton = new JButton("Close");
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this, "Apply changes before closing?", "", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                tempPreferences.apply();
            }
            dispose();
        });

        // Panel to hold action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(applyAndCloseButton);
        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        // Layout setup: category list on the left, settings panel on the right, and buttons at the bottom
        add(new JScrollPane(categoryList), BorderLayout.WEST);
        add(settingsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Show dialog
        setVisible(true);
        pack();
    }
}
