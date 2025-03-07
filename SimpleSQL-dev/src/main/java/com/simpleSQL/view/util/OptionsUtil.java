package com.simpleSQL.view.util;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.simpleSQL.model.Property;
import com.simpleSQL.model.TempLocalPreferences;

/**
 * Utility class for creating options components, such as labeled inputs, checkboxes, and combo boxes.
 * Supports dynamic preference mapping, enabling synchronization of UI components with stored settings.
 */
public class OptionsUtil {

    /**
     * Creates a JPanel with a parent JCheckBox and a sub-panel that enables/disables its components
     * based on the state of the parent JCheckBox.
     *
     * @param title      the title of the panel
     * @param parentBox  the parent JCheckBox that controls the sub-panel
     * @param label1     the JLabel in the sub-panel before the text field
     * @param textField  the JTextField in the sub-panel
     * @param label2     the JLabel in the sub-panel after the text field
     * @return a JPanel containing the parent JCheckBox and the sub-panel
     */
    public static JPanel createPanelWithSubTextFieldBasedOnCheckbox(String title, JCheckBox parentBox, JLabel label1,
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

    /**
     * Creates a JPanel containing a label and a text field in a single row.
     *
     * @param label the label to display next to the input field
     * @param input the text field for user input
     * @return a JPanel with the label and text field arranged horizontally
     */
    public static JPanel createLabeledInput(JLabel label, JTextField input) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(input);
        return panel;
    }

    /**
     * Creates a JPanel containing a label and a checkbox in a single row.
     *
     * @param label the label to display next to the checkbox
     * @param input the checkbox for user interaction
     * @return a JPanel with the label and checkbox arranged horizontally
     */
    public static JPanel createLabeledCheckbox(JLabel label, JCheckBox input) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(input);
        return panel;
    }

    /**
     * Wraps a JPanel in a horizontal Box layout, aligning it to the left with space for additional components.
     *
     * @param panel the JPanel to justify to the left
     * @return a Box containing the panel aligned to the left
     */
    public static Box justifyLeft(JPanel panel) {
        Box box = Box.createHorizontalBox();
        box.add(panel);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    /**
     * Creates a JCheckBox that synchronizes its state with a given preference key.
     *
     * @param tempObj the preferences object to read/write the checkbox state
     * @param key     the preference key to associate with this checkbox
     * @return a JCheckBox with the state mapped to the specified preference
     */
    public static JCheckBox createMappedCheckBox(TempLocalPreferences tempObj, Property key) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(tempObj.get(key).equals("1"));
        checkBox.addActionListener(e -> tempObj.set(key, checkBox.isSelected() ? "1" : "0"));
        return checkBox;
    }

    /**
     * Creates a JCheckBox with a title that synchronizes its state with a given preference key.
     *
     * @param tempObj the preferences object to read/write the checkbox state
     * @param key     the preference key to associate with this checkbox
     * @param title   the text to display on the checkbox
     * @return a titled JCheckBox with the state mapped to the specified preference
     */
    public static JCheckBox createMappedCheckBox(TempLocalPreferences tempObj, Property key, String title) {
        JCheckBox checkBox = createMappedCheckBox(tempObj, key);
        checkBox.setText(title);
        return checkBox;
    }

    /**
     * Creates a JTextField that synchronizes its text with a given preference key.
     *
     * @param tempObj the preferences object to read/write the text field value
     * @param key     the preference key to associate with this text field
     * @return a JTextField with the text mapped to the specified preference
     */
    public static JTextField createMappedTextField(TempLocalPreferences tempObj, Property key) {
        JTextField textField = new JTextField();
        textField.setText(tempObj.get(key));
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                tempObj.set(key, textField.getText());
            }
            @Override
            public void focusGained(FocusEvent e) {
                // No action needed on focus gained
            }
        });
        return textField;
    }

    /**
     * Creates a JComboBox with values and synchronizes its selected item with a given preference key.
     *
     * @param tempObj the preferences object to read/write the combo box selection
     * @param key     the preference key to associate with this combo box
     * @param values  the array of selectable items
     * @return a JComboBox with its selection mapped to the specified preference
     */
    public static JComboBox<String> createMappedComboBox(TempLocalPreferences tempObj, Property key, String[] values) {
        JComboBox<String> comboBox = new JComboBox<>(values);
        comboBox.setSelectedItem(tempObj.get(key));
        comboBox.addActionListener(e -> tempObj.set(key, String.valueOf(comboBox.getSelectedItem())));
        return comboBox;
    }
}
