package com.simpleSQL.eerModel;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Entity extends ComponentBase {

	public Entity(Point lastMouseLooc, String text) {
		super(200, 100, text);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Draw Outlined box
		g2d.setColor(color);
		g2d.drawRect(pos.x, pos.y, getWidth(), getHeight());
	}
}
