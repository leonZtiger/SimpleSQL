package com.simpleSQL.views.properties;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class General extends OptionsBase {

	public General() {
		super("General Settings");

		JPanel saveOptions = OptionsUtil.createPanelWithSubTextFieldBasedOnCheckboc("Auto Save settings",
				new JCheckBox("Auto-Save Interval"), new JLabel("Save every"), new JTextField("6"),
				new JLabel("minute(s)"));

		saveOptions.add(OptionsUtil.createLabeledCheckbox(new JLabel("Confirm on close"), new JCheckBox()));

		content.add(OptionsUtil.justifyLeft(saveOptions));

		content.add(Box.createVerticalStrut(10)); // Spacing

		content.add(OptionsUtil
				.justifyLeft(OptionsUtil.createLabeledCheckbox(new JLabel("Show line numbers"), new JCheckBox())));

	}
}
