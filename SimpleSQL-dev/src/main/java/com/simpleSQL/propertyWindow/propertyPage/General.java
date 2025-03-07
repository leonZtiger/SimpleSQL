package com.simpleSQL.propertyWindow.propertyPage;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.simpleSQL.model.PropertiesModel;
import com.simpleSQL.model.Property;
import com.simpleSQL.model.TempLocalPreferences;
import com.simpleSQL.view.util.OptionsUtil;
/***
 * Represents the General settings page
 */
public class General extends PageContainer {
	/***
	 * Constructs the General page container.
	 * 
	 * @param tempPreferences the temporary object holding settings changes that are not yet applied
	 */
	public General(TempLocalPreferences tempPreferences) {
		super("General Settings");
		
		// Create input with sub inputs
		JTextField saveField = OptionsUtil.createMappedTextField(tempPreferences, Property.AUTO_SAVE_INTERVAL);
		JCheckBox saveBox = OptionsUtil.createMappedCheckBox(tempPreferences, Property.AUTO_SAVE);
		
		saveBox.setText("Auto-Save Interval");
		
		JPanel saveOptions = OptionsUtil.createPanelWithSubTextFieldBasedOnCheckbox("Auto Save settings", saveBox,
				new JLabel("Save every"), saveField, new JLabel("minute(s)"));

		JCheckBox conBox = OptionsUtil.createMappedCheckBox(tempPreferences, Property.CONFIRM_ON_CLOSE);

		saveOptions.add(OptionsUtil.createLabeledCheckbox(new JLabel("Confirm on close"), conBox));
		
		// Add input for save interval
		content.add(OptionsUtil.justifyLeft(saveOptions));

	}
}
