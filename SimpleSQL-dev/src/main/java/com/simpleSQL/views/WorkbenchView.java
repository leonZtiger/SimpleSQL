package com.simpleSQL.views;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class WorkbenchView extends JTabbedPane {

	public WorkbenchView() {
		super();
	}
	
	public void addClosableTab(String title, Component content, ActionListener onCloseAction) {
        // Add the content component to the tabed pane
        addTab(title, content);

        // Create a panel to hold the tab title and close button
        JPanel tabComponent = new JPanel();
        tabComponent.setLayout(new FlowLayout());
        tabComponent.setOpaque(false);

        JLabel tabTitle = new JLabel(title);
        tabComponent.add(tabTitle);

        JButton closeButton = UiUtil.createButton("icons/close.png", Theme.bodyFont.getSize());
        // Style the cross
        closeButton.setMargin(new Insets(5,5,5,5));
        // Add an ActionListener to handle the tab closing
        closeButton.addActionListener(onCloseAction);

        tabComponent.add(closeButton);

        // Set the custom tab component, newest tab is at the highest index, ie index at coun.
        setTabComponentAt(getTabCount() - 1, tabComponent);
        setSelectedIndex(getTabCount() - 1);
    }
}
