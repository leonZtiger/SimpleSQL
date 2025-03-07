package com.simpleSQL.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Represents the application's main menu bar, containing File, Edit, and Project menus.
 * Provides menu items with keyboard shortcuts for actions such as opening, saving, undoing, 
 * setting connections, accessing properties and more.
 */
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

    /**
     * Constructs the MenuBarView, setting up menus for file, edit, and project actions.
     */
    public MenuBarView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add the menus
        add(createFileMenu());
        add(createEditMenu());
        add(createProjectMenu());
    }

    /**
     * Creates the File menu with items for opening, creating, saving, and exiting files.
     *
     * @return the configured File menu
     */
    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

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

    /**
     * Creates the Edit menu with items for undo, redo, select all, and formatting.
     *
     * @return the configured Edit menu
     */
    private JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");

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

    /**
     * Creates the Project menu with items for setting a database connection, building the project,
     * and accessing project properties.
     *
     * @return the configured Project menu
     */
    private JMenu createProjectMenu() {
        JMenu projectMenu = new JMenu("Project");

        setConnectionItem = createMenuItem("Set Connection");
        buildItem = createMenuItem("Build");
        propertiesItem = createMenuItem("Properties");

        projectMenu.add(setConnectionItem);
        projectMenu.add(buildItem);
        projectMenu.add(propertiesItem);

        return projectMenu;
    }

    /**
     * Creates a menu item with a keyboard shortcut.
     *
     * @param text      the display text of the menu item
     * @param keyEvent  the key event for the shortcut
     * @param modifiers the modifier keys for the shortcut
     * @return the configured JMenuItem
     */
    private static JMenuItem createMenuItem(String text, int keyEvent, int modifiers) {
        JMenuItem item = new JMenuItem(text);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, modifiers));
        return item;
    }

    /**
     * Creates a menu item without a keyboard shortcut.
     *
     * @param text the display text of the menu item
     * @return the configured JMenuItem
     */
    private static JMenuItem createMenuItem(String text) {
        return new JMenuItem(text);
    }

    // Public methods to add ActionListeners to menu items for handling actions in controllers

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
}
