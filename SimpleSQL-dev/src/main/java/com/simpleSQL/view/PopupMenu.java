package com.simpleSQL.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.TextAction;

/**
 * Represents a popup menu with customizable items, supporting both regular
 * and keyboard-accelerated menu actions. Provides helper methods for
 * creating text actions with or without keyboard shortcuts.
 */
public class PopupMenu extends JPopupMenu {

    private final JMenu menu;

    /**
     * Constructs a PopupMenu with a list of TextAction items.
     *
     * @param items the list of TextActions to be included in the popup menu
     */
    public PopupMenu(ArrayList<TextAction> items) {
        super();

        menu = new JMenu();

        // Add each action to both the popup menu and the JMenu for access
        items.forEach(action -> {
            add(action);
            menu.add(action);
        });
    }

    /**
     * Retrieves the JMenu representation of this popup menu, containing all items.
     *
     * @return the JMenu containing the same items as the popup menu
     */
    public JMenu getMenu() {
        return menu;
    }

    /**
     * Creates a text-based menu item action.
     *
     * @param title  the title of the menu item
     * @param action the ActionListener that defines the action's behavior
     * @return a configured TextAction with the specified title and action
     */
    public static TextAction createTextAction(String title, ActionListener action) {
        TextAction menuAction = new TextAction(title) {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.actionPerformed(e);
            }
        };

        menuAction.putValue(AbstractAction.NAME, title);
        return menuAction;
    }

    /**
     * Creates a text-based menu item action with a keyboard shortcut.
     *
     * @param title  the title of the menu item
     * @param key    the key event associated with the shortcut
     * @param mask   the modifier keys for the shortcut
     * @param action the ActionListener that defines the action's behavior
     * @return a configured TextAction with the specified title, shortcut, and action
     */
    public static TextAction createTextAction(String title, int key, int mask, ActionListener action) {
        TextAction menuAction = createTextAction(title, action);
        menuAction.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(key, mask));
        return menuAction;
    }
}
