package com.simpleSQL.eerModel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;

public class Entity extends ComponentBase {

	public static final Color background = new Color(112, 161, 215);
	public static final Color borderColor = new Color(70, 130, 180);

	public Entity(String text) {
		super(200, 100, text);

	}

	@Override
	public ComponentBase copy() {

		Entity copy = new Entity(getText());
		copy.setLocation(this.getLocation());
		copy.setSize(this.getSize());
		copy.setPreferredSize(this.getPreferredSize());
		copy.setComponentPopupMenu(this.getComponentPopupMenu());

		return copy;
	}

	@Override
	public void paintComponent(Graphics g) {

		Color backgroundTemp = background;
		Color borderTemp = borderColor;
		
		if (selected) {
			backgroundTemp = backgroundTemp.darker();
			borderTemp = borderTemp.darker();
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw Outlined box
		GradientPaint gradient = new GradientPaint(0, 0, backgroundTemp.brighter(), getWidth(), getHeight(),
				backgroundTemp);
		g2d.setPaint(gradient);
		g2d.fillRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth,
				cornerArc, cornerArc);

		g2d.setStroke(new BasicStroke(borderWidth));
		g2d.setColor(borderTemp);
		g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth,
				cornerArc, cornerArc);

		// Draw text of the component
		super.paintComponent(g);

	}
}
