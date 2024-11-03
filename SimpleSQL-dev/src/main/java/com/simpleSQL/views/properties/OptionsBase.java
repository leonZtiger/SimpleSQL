package com.simpleSQL.views.properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class OptionsBase extends JScrollPane{

	protected JPanel content;
	
	public OptionsBase(String title) {
		super();
		content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		content.add(new JLabel(title));
		content.add(Box.createVerticalStrut(10)); // Spacing
		
		setViewportView(content);
	}

}
