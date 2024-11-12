package com.simpleSQL.view.util;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Utility class for creating styled JButtons with optional icons and text.
 * Provides methods to create buttons with consistent styling and icon scaling.
 */
public class ButtonUtil {

    /**
     * Creates and returns a JButton with an icon and text, with the icon scaled to fit the specified height.
     *
     * @param imageResourcePath the path to the icon image resource
     * @param text              the text to display on the button
     * @param height            the height to scale the icon to
     * @return a JButton with the specified icon, text, and scaled icon height
     */
    public static JButton createButton(String imageResourcePath, String text, int height) {
        JButton btn = createButton(imageResourcePath, height);
        btn.setText(text);
        btn.setHorizontalTextPosition(JLabel.LEFT);
        return btn;
    }

    /**
     * Creates and returns a JButton with an icon only, with the icon scaled to fit the specified height.
     *
     * @param imageResourcePath the path to the icon image resource
     * @param height            the height to scale the icon to
     * @return a JButton with the specified icon and scaled icon height
     */
    public static JButton createButton(String imageResourcePath, int height) {
        JButton btn = initButton();

        // Load and scale the icon to the specified height while maintaining aspect ratio
        ImageIcon icon = new ImageIcon(ButtonUtil.class.getClassLoader().getResource(imageResourcePath));
        float aspectRatio = icon.getIconWidth() / (float) icon.getIconHeight();
        icon.setImage(icon.getImage().getScaledInstance((int) (height * aspectRatio), height, Image.SCALE_SMOOTH));

        btn.setIcon(icon);
        btn.setSize(icon.getIconWidth(), icon.getIconHeight());

        return btn;
    }

    /**
     * Creates and returns a JButton with only text.
     *
     * @param text the text to display on the button
     * @return a JButton with the specified text
     */
    public static JButton createButton(String text) {
        JButton btn = initButton();
        btn.setText(text);
        return btn;
    }

    /**
     * Initializes a JButton with common styling.
     *
     * @return a JButton with base styling applied
     */
    private static JButton initButton() {
        JButton btn = new JButton();
        // Apply common button styles here, if needed
        return btn;
    }
}
