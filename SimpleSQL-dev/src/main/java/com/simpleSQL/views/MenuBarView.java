package com.simpleSQL.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBarView extends JMenuBar {

	private JMenuItem openItem;
	private JMenuItem newItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem saveAllItem;
	private JMenuItem exitItem;
	private JMenuItem undoItem;
	private JMenuItem redoItem;
	private JMenuItem selectAllItem;
	private JMenuItem formatItem;
	private JMenuItem setConnectionItem;
	private JMenuItem buildItem;
	private JMenuItem propertiesItem;

	public MenuBarView() {
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);

		// Add the menus
		add(createFileMenu());
		add(createEditMenu());
		add(createProjectMenu());
	}

	// Public methods to add ActionListeners from the controller
	public void addOpenActionListener(ActionListener listener) {
		openItem.addActionListener(listener);
	}

	public void addNewActionListener(ActionListener listener) {
		newItem.addActionListener(listener);
	}

	public void addSaveActionListener(ActionListener listener) {
		saveItem.addActionListener(listener);
	}

	public void addSaveAsActionListener(ActionListener listener) {
		saveAsItem.addActionListener(listener);
	}

	public void addSaveAllActionListener(ActionListener listener) {
		saveAllItem.addActionListener(listener);
	}

	public void addExitActionListener(ActionListener listener) {
		exitItem.addActionListener(listener);
	}

	public void addUndoActionListener(ActionListener listener) {
		undoItem.addActionListener(listener);
	}

	public void addRedoActionListener(ActionListener listener) {
		redoItem.addActionListener(listener);
	}

	public void addSelectAllActionListener(ActionListener listener) {
		selectAllItem.addActionListener(listener);
	}

	public void addFormatActionListener(ActionListener listener) {
		formatItem.addActionListener(listener);
	}

	public void addSetConnectionActionListener(ActionListener listener) {
		setConnectionItem.addActionListener(listener);
	}

	public void addBuildActionListener(ActionListener listener) {
		buildItem.addActionListener(listener);
	}

	public void addPropertiesActionListener(ActionListener listener) {
		propertiesItem.addActionListener(listener);
	}

	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(Theme.headFont);

		openItem = createMenuItem("Open", KeyEvent.VK_B, ActionEvent.ALT_MASK);
		newItem = createMenuItem("New", KeyEvent.VK_L, ActionEvent.ALT_MASK);
		saveItem = createMenuItem("Save", KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		saveAsItem = createMenuItem("Save As");
		saveAllItem = createMenuItem("Save All", KeyEvent.VK_S, ActionEvent.SHIFT_MASK | ActionEvent.CTRL_MASK);
		exitItem = createMenuItem("Exit");

		fileMenu.add(openItem);
		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(saveAllItem);
		fileMenu.add(exitItem);

		return fileMenu;
	}

	private JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		editMenu.setFont(Theme.headFont);

		undoItem = createMenuItem("Undo", KeyEvent.VK_Z, ActionEvent.CTRL_MASK);
		redoItem = createMenuItem("Redo", KeyEvent.VK_Y, ActionEvent.CTRL_MASK);
		selectAllItem = createMenuItem("Select All", KeyEvent.VK_A, ActionEvent.CTRL_MASK);
		formatItem = createMenuItem("Format", KeyEvent.VK_F, ActionEvent.SHIFT_MASK | ActionEvent.CTRL_MASK);

		editMenu.add(undoItem);
		editMenu.add(redoItem);
		editMenu.add(selectAllItem);
		editMenu.add(formatItem);

		return editMenu;
	}

	private JMenu createProjectMenu() {
		JMenu projectMenu = new JMenu("Project");
		projectMenu.setFont(Theme.headFont);

		setConnectionItem = createMenuItem("Set Connection");
		buildItem = createMenuItem("Build");
		propertiesItem = createMenuItem("Properties");

		projectMenu.add(setConnectionItem);
		projectMenu.add(buildItem);
		projectMenu.add(propertiesItem);

		return projectMenu;
	}

	private static JMenuItem createMenuItem(String text, int keyEvent, int modifiers) {
		JMenuItem item = new JMenuItem(text);
		item.setFont(Theme.bodyFont);

		item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, modifiers));

		return item;
	}

	private static JMenuItem createMenuItem(String text) {
		JMenuItem item = new JMenuItem(text);
		item.setFont(Theme.bodyFont);

		return item;
	}
}
