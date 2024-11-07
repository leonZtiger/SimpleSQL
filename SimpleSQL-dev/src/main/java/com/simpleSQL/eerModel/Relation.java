package com.simpleSQL.eerModel;

import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;

public class Relation extends ComponentBase {

	public Relation(Point point, String text) {
		super(200, 100, text);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Draw Outlined prism
		g2d.setColor(color);

		Polygon p = new Polygon();
		p.addPoint(pos.x + getWidth() / 2, pos.y + borderWidth);
		p.addPoint(pos.x + getWidth() - borderWidth, pos.y + getHeight() / 2);
		p.addPoint(pos.x + getWidth() / 2, pos.y + getHeight() - borderWidth);
		p.addPoint(pos.x + borderWidth, pos.y + getHeight() / 2);

		g2d.drawPolygon(p);
	}
}
