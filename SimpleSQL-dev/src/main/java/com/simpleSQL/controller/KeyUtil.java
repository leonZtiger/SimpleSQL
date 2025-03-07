package com.simpleSQL.controller;

import java.util.UUID;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Utility class for adding key bindings to components, allowing actions
 * to be triggered on key press or release events.
 */
public class KeyUtil {

    /**
     * Adds key bindings to a component for when a specific key is pressed
     * and released.
     *
     * @param component  the component to which the key binding is added
     * @param key        the key code for the binding (e.g., KeyEvent.VK_ENTER)
     * @param onPressed  the action to perform when the key is pressed
     * @param onReleased the action to perform when the key is released
     */
    public static void addOnPressEnabledBinding(JComponent component, int key, AbstractAction onPressed,
                                                AbstractAction onReleased) {
        String actionNamePress = UUID.randomUUID().toString();
        String actionNameRelease = UUID.randomUUID().toString();

        // Key pressed
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                 .put(KeyStroke.getKeyStroke(key, 0, false), actionNamePress);
        component.getActionMap().put(actionNamePress, onPressed);

        // Key released
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                 .put(KeyStroke.getKeyStroke(key, 0, true), actionNameRelease);
        component.getActionMap().put(actionNameRelease, onReleased);
    }

    /**
     * Adds a key binding to a component for a specific key and modifier
     * combination, triggering an action on key press.
     *
     * @param component the component to which the key binding is added
     * @param key       the key code for the binding (e.g., KeyEvent.VK_ENTER)
     * @param mask      the modifier mask (e.g., KeyEvent.SHIFT_DOWN_MASK)
     * @param action    the action to perform when the key and mask are pressed
     */
    public static void addOnPressEnabledBinding(JComponent component, int key, int mask, AbstractAction action) {
        String actionNamePress = UUID.randomUUID().toString();

        // Key pressed with modifier
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                 .put(KeyStroke.getKeyStroke(key, mask, false), actionNamePress);
        component.getActionMap().put(actionNamePress, action);
    }

    /**
     * Adds a key binding to a component for a specific key, triggering an
     * action on key press.
     *
     * @param component the component to which the key binding is added
     * @param key       the key code for the binding (e.g., KeyEvent.VK_ENTER)
     * @param onPressed the action to perform when the key is pressed
     */
    public static void addOnPressEnabledBinding(JComponent component, int key, AbstractAction onPressed) {
        String actionNamePress = UUID.randomUUID().toString();

        // Key pressed without modifier
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                 .put(KeyStroke.getKeyStroke(key, 0, false), actionNamePress);
        component.getActionMap().put(actionNamePress, onPressed);
    }
}
