package com.simpleSQL.controllers;

import java.awt.event.KeyEvent;
import java.util.UUID;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.simpleSQL.views.ProjectView;

public class KeyUtil {

	public static void addOnPressEnabledBinding(JComponent component, int key, AbstractAction onPressed,
			AbstractAction onReleased) {

		String actionNameP = UUID.randomUUID().toString();

		// On pressed
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0, false),
				actionNameP);
		component.getActionMap().put(actionNameP, onPressed);

		String actionNameR = UUID.randomUUID().toString();
		// On released
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0, true), actionNameR);
		component.getActionMap().put(actionNameR, onReleased);

	}

	public static void addOnPressEnabledBinding(JComponent component, int key, int mask, AbstractAction action) {
		String actionNameP = UUID.randomUUID().toString();

		// On pressed
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, mask, false),
				actionNameP);
		component.getActionMap().put(actionNameP, action);

	}

	public static void addOnPressEnabledBinding(JComponent component, int key, AbstractAction onPressed) {

		String actionNameP = UUID.randomUUID().toString();

		// On pressed
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0, false),
				actionNameP);
		component.getActionMap().put(actionNameP, onPressed);
	}

}
