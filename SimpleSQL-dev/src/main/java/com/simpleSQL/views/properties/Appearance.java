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

import com.simpleSQL.models.PropertiesModel;
import com.simpleSQL.models.Property;
import com.simpleSQL.models.TempLocalPreferences;

public class Appearance extends OptionsBase {

	public Appearance(TempLocalPreferences tempPreferences) {
		super("Appearance Settings");
		JPanel theme = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JComboBox<String> themeSelector = OptionsUtil.createMappedComboBox(tempPreferences, Property.THEME,
				new String[] { "Light", "Dark", "System Default" });
		theme.add(new JLabel("Theme:"));

		theme.add(themeSelector);
		// theme.set
		content.add(theme);

		content.add(OptionsUtil.createLabeledInput(new JLabel("Font size"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.FONT_SIZE)));
		content.add(OptionsUtil.createLabeledInput(new JLabel("Scale"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.UI_SCALING)));
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Show grid lines"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.SHOW_GRID_LINES)));

	}

}
