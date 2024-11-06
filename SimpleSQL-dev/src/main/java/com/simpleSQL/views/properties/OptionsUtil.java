package com.simpleSQL.views.properties;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.simpleSQL.models.Property;
import com.simpleSQL.models.TempLocalPreferences;

public class OptionsUtil {

	public static JPanel createPanelWithSubTextFieldBasedOnCheckboc(String title, JCheckBox parentBox, JLabel label1,
			JTextField textField, JLabel label2) {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder(title));
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

	public static JCheckBox createMappedCheckBox(TempLocalPreferences tempObj, Property key) {
		JCheckBox b = new JCheckBox();

		b.setSelected(tempObj.get(key).equals("1"));
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tempObj.set(key, b.isSelected() ? "1" : "0");
			}
		});
		return b;
	}

	public static JCheckBox createMappedCheckBox(TempLocalPreferences tempObj, Property key, String title) {
		JCheckBox b = createMappedCheckBox(tempObj, key);
		b.setText(title);

		return b;
	}

	public static JTextField createMappedTextField(TempLocalPreferences tempObj, Property key) {
		JTextField t = new JTextField();

		t.setText(tempObj.get(key));
		t.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				tempObj.set(key, t.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});
		return t;
	}

	public static JComboBox<String> createMappedComboBox(TempLocalPreferences tempObj, Property key, String[] values) {
		JComboBox<String> b = new JComboBox<String>(values);

		b.setSelectedItem(tempObj.get(key));
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tempObj.set(key, String.valueOf(b.getSelectedItem()));
			}
		});
		return b;

	}

}
