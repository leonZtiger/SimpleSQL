package com.simpleSQL.views.properties;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsUtil {

	public static JPanel createPanelWithSubTextFieldBasedOnCheckboc(String title, JCheckBox parentBox, JLabel label1,
			JTextField textField, JLabel label2) {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder(title));

		parentBox.setSelected(true);
		panel.add(parentBox);

		JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		subPanel.add(label1);
		subPanel.add(textField);
		subPanel.add(label2);
		subPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		panel.add(subPanel);

		parentBox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				boolean isEnabled = parentBox.isSelected();
				label1.setEnabled(isEnabled);
				textField.setEnabled(isEnabled);
				label2.setEnabled(isEnabled);
			}
		});

		return panel;
	}

	public static JPanel createLabeledInput(JLabel label, JTextField input) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panel.add(label);
		panel.add(input);

		return panel;
	}

	public static JPanel createLabeledCheckbox(JLabel label, JCheckBox input) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panel.add(label);
		panel.add(input);

		return panel;
	}
	
	public static Box justifyLeft(JPanel p) {
		
		Box box = Box.createHorizontalBox();
		box.add(p);
		box.add(box.createHorizontalGlue());
		
		return box;
	}
}
