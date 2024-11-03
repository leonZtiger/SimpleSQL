package com.simpleSQL.views.properties;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class NestedOption extends JPanel{

	public NestedOption() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
	}
	
	public void addSubOption(Component c) {
		c.setEnabled(isEnabled());
	}
}
