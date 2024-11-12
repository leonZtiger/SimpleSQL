package com.simpleSQL.view.project;

import java.awt.FlowLayout;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.simpleSQL.view.util.ButtonUtil;

/**
 * A toolbar panel for EER model tools, providing buttons for edit mode, validation, compilation, and running.
 * Each button has an associated action that can be set via the public methods.
 */
public class EerModelTools extends JPanel {

    private final JButton editModeBtn;
    private final JButton validateBtn;
    private final JButton compileBtn;
    private final JButton runBtn;

    /**
     * Constructs the EerModelTools toolbar, initializing and adding buttons with specified labels and icons.
     */
    public EerModelTools() {
        super();

        // Configure panel layout
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 30, 7);
        layout.setAlignment(FlowLayout.LEFT);
        setLayout(layout);

        // Initialize buttons
        editModeBtn = new JButton("Enter Connect Mode");
        validateBtn = new JButton("Validate Connections");
        compileBtn = new JButton("Compile");
        runBtn = ButtonUtil.createButton("icons/play.png", "Run", 18);

        // Add buttons to the panel
        add(editModeBtn);
        add(validateBtn);
        add(compileBtn);
        add(runBtn);
    }

    /**
     * Sets the action to perform when the edit mode button is pressed.
     *
     * @param action the Action to associate with the edit mode button
     */
    public void addOnEditMode(Action action) {
        editModeBtn.addActionListener(action);
    }

    /**
     * Sets the action to perform when the validate button is pressed.
     *
     * @param action the Action to associate with the validate button
     */
    public void addOnValidate(Action action) {
        validateBtn.addActionListener(action);
    }

    /**
     * Sets the action to perform when the compile button is pressed.
     *
     * @param action the Action to associate with the compile button
     */
    public void addOnCompile(Action action) {
        compileBtn.addActionListener(action);
    }

    /**
     * Sets the action to perform when the run button is pressed.
     *
     * @param action the Action to associate with the run button
     */
    public void addOnRun(Action action) {
        runBtn.addActionListener(action);
    }
}
