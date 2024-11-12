package com.simpleSQL.propertyWindow.propertyPage;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
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
 * Represents the editor page.
 */
public class Editor extends PageContainer {
	/***
	 * Constructs the Editor page container.
	 * 
	 * @param tempPreferences the temporary object holding settings changes that are not yet applied
	 */
	public Editor(TempLocalPreferences tempPreferences) {
		super("Editor Settings");
		// Add input for editor font size
		content.add(OptionsUtil.createLabeledInput(new JLabel("Font size"),
				OptionsUtil.createMappedTextField(tempPreferences, Property.EDITOR_FONT_SIZE)));
		// Add  input for auto-identation
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Auto indent"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.AUTO_INDENTATION)));
		// Add input for enabling/disabling compile on save
		content.add(OptionsUtil.createLabeledCheckbox(new JLabel("Compile on save"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.CODE_COMPLETION)));
		// Add input for enabling/disabling line numbers
		content.add(OptionsUtil.justifyLeft(OptionsUtil.createLabeledCheckbox(new JLabel("Show line numbers"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.SHOW_LINE_NUMBERS))));
		// Add input for enabling/disabling real-time syntax checking and highlighting 
		content.add(OptionsUtil.justifyLeft(OptionsUtil.createLabeledCheckbox(new JLabel("Show syntax errors"),
				OptionsUtil.createMappedCheckBox(tempPreferences, Property.SHOW_SYNTAX_ERROR))));
	
		
	}

}
