package com.simpleSQL.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import com.simpleSQL.view.util.ButtonUtil;

/**
 * Represents the main workbench view with a tabbed pane, allowing for
 * multiple tabs with closable content sections.
 * Supports adding tabs with customizable close buttons and change listeners.
 */
public class WorkbenchView extends JTabbedPane {

    /**
     * Constructs the WorkbenchView as a tabbed pane container.
     */
    public WorkbenchView() {
        super();
    }

    /**
     * Adds a closable tab to the workbench, with a specified title, content,
     * and an action listener for closing the tab.
     *
     * @param title        the title displayed on the tab
     * @param content      the component to display within the tab
     * @param onCloseAction the ActionListener triggered when the close button is clicked
     */
    public void addClosableTab(String title, Component content, ActionListener onCloseAction) {
        // Add the content component to the tabbed pane
        addTab(title, content);

        // Create a panel to hold the tab title and close button
        JPanel tabComponent = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabComponent.setOpaque(false);

        // Label for the tab title
        JLabel tabTitle = new JLabel(title);
        tabComponent.add(tabTitle);

        // Close button for the tab
        JButton closeButton = ButtonUtil.createButton("icons/close.png", tabTitle.getFont().getSize());
        closeButton.setMargin(new Insets(5, 5, 5, 5)); // Set margins for close button appearance
        closeButton.addActionListener(onCloseAction); // Attach the close action listener

        tabComponent.add(closeButton);

        // Assign the custom tab component to the latest tab (at the highest index)
        int newTabIndex = getTabCount() - 1;
        setTabComponentAt(newTabIndex, tabComponent);
        setSelectedIndex(newTabIndex); // Select the newly added tab
    }

    /**
     * Adds a ChangeListener to monitor tab selection changes.
     *
     * @param listener the ChangeListener to add
     */
    public void addOnChangeListener(ChangeListener listener) {
        addChangeListener(listener);
    }
}
