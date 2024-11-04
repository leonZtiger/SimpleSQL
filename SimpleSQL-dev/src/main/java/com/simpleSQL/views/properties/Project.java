package com.simpleSQL.views.properties;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.simpleSQL.models.PropertiesModel;
import com.simpleSQL.models.Property;
import com.simpleSQL.models.TempLocalPreferences;

public class Project extends OptionsBase {

	public Project(TempLocalPreferences tempPreferences) {
		super("Project Setttings");
		
		content.add(OptionsUtil.createLabeledInput(new JLabel("Default Save Directory"), OptionsUtil.createMappedTextField(tempPreferences, Property.DEFAULT_SAVE_PATH)));
		content.add(OptionsUtil.createLabeledInput(new JLabel("Max Undo Steps"), OptionsUtil.createMappedTextField(tempPreferences, Property.MAX_UNDO_STEPS)));		
	}
}
