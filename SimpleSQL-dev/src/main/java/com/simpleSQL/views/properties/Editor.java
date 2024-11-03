package com.simpleSQL.views.properties;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor extends OptionsBase {

	public Editor() {
		super("Editor Settings");
		
		content.add(OptionsUtil.createLabeledInput(new JLabel("Font size"), new JTextField("12")));
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Auto indent"), new JCheckBox()));
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Compile on save"), new JCheckBox()));
	}

}
