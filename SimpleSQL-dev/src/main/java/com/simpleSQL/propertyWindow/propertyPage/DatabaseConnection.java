package com.simpleSQL.propertyWindow.propertyPage;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.simpleSQL.model.PropertiesModel;
import com.simpleSQL.model.Property;
import com.simpleSQL.model.TempLocalPreferences;
import com.simpleSQL.view.util.OptionsUtil;

/***
 * Represents the database managing page.
 */
public class DatabaseConnection extends PageContainer {

	/***
	 * Constructs the DatabaseConnection page container.
	 * 
	 * @param tempPreferences the temporary object holding settings changes that are not yet applied
	 */
	public DatabaseConnection(TempLocalPreferences tempPreferences) {
		super("Database connection settings");
		
		// Add input for default host
		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Host"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_HOST)));
		// ADd input for defualt port
		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Port"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_PORT)));
		// Add input for defualt user
		content.add(OptionsUtil.createLabeledInput(new JLabel("Default User"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_USER)));
		// Add input for defualt password
		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Password"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_PASSWORD)));
		// Add input for re-connect settings
		content.add(
				OptionsUtil.justifyLeft(OptionsUtil.createPanelWithSubTextFieldBasedOnCheckboc("Connection Settings",
						OptionsUtil.createMappedCheckBox(tempPreferences, Property.RECONNECT_ON_FAILURE,
								"Reconnect on failure"),
						new JLabel("Maxiumum tries"),
						OptionsUtil.createMappedTextField(tempPreferences, Property.RECONNECT_ON_FAILURE_TRIES),
						new JLabel())));

	}
}
