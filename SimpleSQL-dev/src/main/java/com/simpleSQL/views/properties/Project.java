package com.simpleSQL.views.properties;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Project extends OptionsBase {

	public Project() {
		super("Project Setttings");
		
		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Save Directory"), new JTextField("Downloads")));
		content.add(OptionsUtil.createLabeledInput(new JLabel("Max Undo Steps"), new JTextField("100")));
		content.add(OptionsUtil.createLabeledInput(new JLabel("Max Redo Steps"), new JTextField("100")));
		
	}
}
