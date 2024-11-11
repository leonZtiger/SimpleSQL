package com.simpleSQL.eerModel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;

public class Attribute extends ComponentBase {

	public static final Color background = new Color(180, 180, 180);
	public static final Color borderColor = new Color(120, 120, 120);
	
	public Attribute(String text) {
		super(150, 75, text);
		
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

		// Draw Outlined prism
		GradientPaint gradient = new GradientPaint(0, 0, backgroundTemp.brighter(), getWidth(), getHeight(),
				backgroundTemp);
		g2d.setPaint(gradient);
		g2d.fillOval(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth);
		g2d.setColor(borderTemp);
		g2d.setStroke(new BasicStroke(borderWidth));
		g2d.drawOval(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth);

		super.paintComponent(g);
	}

	@Override
	public ComponentBase copy() {

		Attribute copy = new Attribute(getText());
		copy.setLocation(this.getLocation());
		copy.setSize(this.getSize());
		copy.setPreferredSize(this.getPreferredSize());

		return copy;
	}

}
