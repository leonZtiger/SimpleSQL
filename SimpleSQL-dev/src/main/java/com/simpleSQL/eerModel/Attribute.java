package com.simpleSQL.eerModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Attribute extends ComponentBase {

	public Attribute(Point placement, String text) {
		super(150, 75, text);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Draw Outlined prism
		g2d.setColor(color);

		g2d.drawOval(pos.x, pos.y, getWidth(), getHeight());
	}

}
