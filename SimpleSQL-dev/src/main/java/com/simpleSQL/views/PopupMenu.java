package com.simpleSQL.views;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.TextAction;

public class PopupMenu extends JPopupMenu {

	private JMenu menu;

	public PopupMenu(ArrayList<TextAction> items) {
		super();

		menu = new JMenu();

		items.forEach(e -> {
			add(e);
			menu.add(e);
		});
	}

	public JMenu getMenu() {
		return menu;
	}

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

	public static TextAction createTextAction(String title, int key, int mask, ActionListener action) {
		TextAction menuAction = createTextAction(title, action);

		menuAction.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(key, mask));

		return menuAction;
	}
}
