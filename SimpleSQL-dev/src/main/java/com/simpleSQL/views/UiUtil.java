package com.simpleSQL.views;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UiUtil {

	// Creates and returns a JButton with an icon and text, its scaled to fit the
	// parameter height
	public static JButton createButton(String imageResourcePath, String text, int height) {
		JButton btn = createButton(imageResourcePath, height);
		btn.setText(text);
		btn.setHorizontalTextPosition(JLabel.LEFT);
		return btn;
	}

	// Creates and returns a JButton with an icon, its scaled to fit the parameter
	// height
	public static JButton createButton(String imageResourcePath, int height) {

		JButton btn = initButton();

		ImageIcon icon = new ImageIcon(UiUtil.class.getClassLoader().getResource(imageResourcePath));
		float aspect = icon.getIconWidth() / (float) icon.getIconHeight();
		icon.setImage(icon.getImage().getScaledInstance((int) (height * aspect), height, Image.SCALE_SMOOTH));

		btn.setIcon(icon);
		btn.setSize(icon.getIconWidth(), icon.getIconHeight());

		return btn;
	}

	// Creates and returns a JButton with an icon and text, its scaled to fit the
	// parameter height
	public static JButton createButton(String text) {

		JButton btn = initButton();
		btn.setText(text);

		return btn;
	}

	// Creates button with common style
	private static JButton initButton() {
		JButton btn = new JButton();

		return btn;
	}
}
