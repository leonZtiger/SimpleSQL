package com.simpleSQL.views.properties;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DatabaseConnection extends OptionsBase {

	public DatabaseConnection() {
		super("Database connection settings");

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Host"), new JTextField("http://127.0.0.1")));

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default port"), new JTextField("8453")));

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default user"), new JTextField("user")));

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default password"), new JTextField("password")));

		content.add(OptionsUtil.justifyLeft(OptionsUtil.createPanelWithSubTextFieldBasedOnCheckboc(
				"Connection Settings", new JCheckBox("Reconnect on failure"), new JLabel("Maxiumum tries"),
				new JTextField("6"), new JLabel())));
		

	}
}
