package com.simpleSQL.propertyWindow.propertyPage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/***
 * Represents the minimal implementation of a settings page in the
 * PropertiesWindow.
 */
public class PageContainer extends JScrollPane {
	// This content container, used for adding items to the page
	protected JPanel content;

	/***
	 * Constructs the Page container given a name.
	 * 
	 * @param title the name of the page.
	 */
	public PageContainer(String title) {
		super();
		// Initialize content panel
		content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// Add title as label
		content.add(new JLabel(title));
		content.add(Box.createVerticalStrut(10)); // Spacing

		// Set the viewport to the content
		setViewportView(content);
	}

}
