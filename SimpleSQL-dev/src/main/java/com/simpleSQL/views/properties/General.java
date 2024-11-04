package com.simpleSQL.views.properties;

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

import com.simpleSQL.models.PropertiesModel;
import com.simpleSQL.models.Property;
import com.simpleSQL.models.TempLocalPreferences;

public class General extends OptionsBase {

	public General(TempLocalPreferences tempPreferences) {
		super("General Settings");

		JTextField saveField = OptionsUtil.createMappedTextField(tempPreferences, Property.AUTO_SAVE_INTERVAL);
		JCheckBox saveBox = OptionsUtil.createMappedCheckBox(tempPreferences, Property.AUTO_SAVE);
		
		saveBox.setText("Auto-Save Interval");
		
		JPanel saveOptions = OptionsUtil.createPanelWithSubTextFieldBasedOnCheckboc("Auto Save settings", saveBox,
				new JLabel("Save every"), saveField, new JLabel("minute(s)"));

		JCheckBox conBox = OptionsUtil.createMappedCheckBox(tempPreferences, Property.CONFIRM_ON_CLOSE);

		saveOptions.add(OptionsUtil.createLabeledCheckbox(new JLabel("Confirm on close"), conBox));

		content.add(OptionsUtil.justifyLeft(saveOptions));

	}
}
