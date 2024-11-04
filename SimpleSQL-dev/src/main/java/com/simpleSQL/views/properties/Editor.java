package com.simpleSQL.views.properties;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.simpleSQL.models.PropertiesModel;
import com.simpleSQL.models.Property;
import com.simpleSQL.models.TempLocalPreferences;

public class Editor extends OptionsBase {

	public Editor(TempLocalPreferences tempPreferences) {
		super("Editor Settings");

		content.add(OptionsUtil.createLabeledInput(new JLabel("Font size"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.EDITOR_FONT_SIZE)));
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Auto indent"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.AUTO_INDENTATION)));
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Compile on save"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.CODE_COMPLETION)));
		content.add(OptionsUtil.justifyLeft(OptionsUtil.createLabeledCheckbox(new JLabel("Show line numbers"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.SHOW_LINE_NUMBERS))));
		content.add(OptionsUtil.justifyLeft(OptionsUtil.createLabeledCheckbox(new JLabel("Show syntax errors"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.SHOW_SYNTAX_ERROR))));
	
		
	}

}
