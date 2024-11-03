package com.simpleSQL.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;

public class Theme {

	public static Color darkBackground = new Color(18, 18, 18);
	public static Color lightText = new Color(255, 255, 255);
	public static Color darkText = new Color(170, 170, 170);
	public static Color lightBackground = new Color(64, 64, 64);
	public static Font bodyFont = new Font("sans-serif", Font.PLAIN, 11);
	public static Font headFont = new Font("sans-serif", Font.PLAIN, 14);
	public static Font codeFont = new Font("mono space", Font.PLAIN, 11);

	public static void setTheme(JComponent comp) {
		// comp.setBackground(darkBackground);
		// comp.setForeground(lightText);
	}

	public static void setThemeMedium(JComponent comp) {
		// comp.setBackground(lightBackground);
		// comp.setForeground(lightText);
	}
}
