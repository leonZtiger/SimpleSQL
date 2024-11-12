package com.simpleSQL.propertyWindow.propertyPage;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.simpleSQL.model.PropertiesModel;
import com.simpleSQL.model.Property;
import com.simpleSQL.model.TempLocalPreferences;
import com.simpleSQL.view.util.OptionsUtil;

/***
 * Represents the Project settings page.
 */
public class Project extends PageContainer {
	/***
	 * Constructs the Project page container.
	 * 
	 * @param tempPreferences the temporary object holding settings changes that are
	 *                        not yet applied
	 */
	public Project(TempLocalPreferences tempPreferences) {
		super("Project Setttings");
		// Add input for default save directory
		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Save Directory"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_SAVE_PATH)));
		// Add input for maximum history length
		content.add(OptionsUtil.createLabeledInput(new JLabel("Max Undo Steps"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.MAX_UNDO_STEPS)));
	}
}
