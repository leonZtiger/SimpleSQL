package com.simpleSQL.views.properties;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Appearance  extends OptionsBase{

	public Appearance() {
		super("Appearance Settings");
		JPanel theme = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JComboBox<String> themeSelector = new JComboBox<>(new String[] { "Light", "Dark", "System Default" });
		theme.add(new JLabel("Theme:"));
		theme.add(themeSelector);
		content.add(theme);
		
		content.add(OptionsUtil.createLabeledInput(new JLabel("Font size"), new JTextField("12")));
		content.add(OptionsUtil.createLabeledInput(new JLabel("Scale"), new JTextField("100")));
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Show grid lines"), new JCheckBox()));
		
	}

}
