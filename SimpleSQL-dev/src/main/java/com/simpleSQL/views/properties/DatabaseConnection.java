package com.simpleSQL.views.properties;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.simpleSQL.models.PropertiesModel;
import com.simpleSQL.models.Property;
import com.simpleSQL.models.TempLocalPreferences;

public class DatabaseConnection extends OptionsBase {

	public DatabaseConnection(TempLocalPreferences tempPreferences) {
		super("Database connection settings");

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Host"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_HOST)));

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Port"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_PORT)));

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default User"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_USER)));

		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Password"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_PASSWORD)));

		content.add(
				OptionsUtil.justifyLeft(OptionsUtil.createPanelWithSubTextFieldBasedOnCheckboc("Connection Settings",
						OptionsUtil.createMappedCheckBox(tempPreferences, Property.RECONNECT_ON_FAILURE,
								"Reconnect on failure"),
						new JLabel("Maxiumum tries"),
						OptionsUtil.createMappedTextField(tempPreferences, Property.RECONNECT_ON_FAILURE_TRIES),
						new JLabel())));

	}
}
