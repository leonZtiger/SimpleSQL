package com.simpleSQL.propertyWindow.propertyPage;

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

import com.simpleSQL.model.PropertiesModel;
import com.simpleSQL.model.Property;
import com.simpleSQL.model.TempLocalPreferences;
import com.simpleSQL.view.util.OptionsUtil;

/***
 * Represents the appearance settings.
 */
public class Appearance extends PageContainer {
	
	/***
	 * Constructs the Appearance page container.
	 * 
	 * @param tempPreferences the temporary object holding settings changes that are not yet applied
	 */
	public Appearance(TempLocalPreferences tempPreferences) {
		super("Appearance Settings");
		
		// Create theme selection panel
		JPanel theme = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JComboBox<String> themeSelector = OptionsUtil.createMappedComboBox(tempPreferences, Property.THEME,
				new String[] { "Light", "Dark", "System Default" });
		theme.add(new JLabel("Theme:"));
		theme.add(themeSelector);
		content.add(theme);
		
		// Add font size input
		content.add(OptionsUtil.createLabeledInput(new JLabel("Font size"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.FONT_SIZE)));
		// Add scale input
		content.add(OptionsUtil.createLabeledInput(new JLabel("Scale"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.UI_SCALING)));
		// Add input for enabling/disabling grid lines
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Show grid lines"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.SHOW_GRID_LINES)));

	}

}
