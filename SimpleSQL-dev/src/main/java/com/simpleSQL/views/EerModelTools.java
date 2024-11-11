package com.simpleSQL.views;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.sound.midi.VoiceStatus;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EerModelTools extends JPanel {

	private JButton editModeBtn;
	private JButton validateBtn;
	private JButton compile;
	private JButton runBtn;

	public EerModelTools() {
		super();

		FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 30, 7);
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);

		editModeBtn = new JButton("Enter Connect Mode");
		validateBtn = new JButton("Validate connections");
		compile = new JButton("Compile");
		runBtn = UiUtil.createButton("icons/play.png", "Run", 18);
		
		add(editModeBtn);
		add(validateBtn);
		add(compile);
		add(runBtn);

	}

	public void addOnEditMode(Action action) {
		editModeBtn.addActionListener(action);
	}

	public void addOnValidate(Action action) {
		validateBtn.addActionListener(action);
	}

	public void addOnCompile(Action action) {
		compile.addActionListener(action);
	}

	public void addOnRun(Action action) {
		runBtn.addActionListener(action);
	}
}
