package com.simpleSQL.eerModel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;

public class Relation extends ComponentBase {

	public static final Color background = new Color(161, 217, 155);
	public static final Color borderColor = new Color(34, 139, 34);

	public Relation(String text) {
		super(200, 100, text);

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

		GradientPaint gradient = new GradientPaint(0, 0, background.brighter(), getWidth(), getHeight(), backgroundTemp);
		g2d.setPaint(gradient);

		Polygon p = new Polygon();
		p.addPoint(getWidth() / 2, borderWidth);
		p.addPoint(getWidth() - borderWidth, getHeight() / 2);
		p.addPoint(getWidth() / 2, getHeight() - borderWidth);
		p.addPoint(borderWidth, getHeight() / 2);
		g2d.fill(createRoundedPolygon(p.xpoints, p.ypoints, cornerArc));
		g2d.setStroke(new BasicStroke(borderWidth));
		// Draw Diamond Outline
		g2d.setColor(borderTemp);
		g2d.draw(createRoundedPolygon(p.xpoints, p.ypoints, cornerArc));

		super.paintComponent(g);
	}

	private Shape createRoundedPolygon(int[] xPoints, int[] yPoints, int arcRadius) {
		Path2D path = new Path2D.Float();
		int nPoints = xPoints.length;

		for (int i = 0; i < nPoints; i++) {
			int nextIndex = (i + 1) % nPoints;
			int prevIndex = (i - 1 + nPoints) % nPoints;

			// Calculate the direction to each corner and create small line segments with
			// arcs
			double x1 = xPoints[i] + (xPoints[prevIndex] - xPoints[i]) / (2.0 * arcRadius);
			double y1 = yPoints[i] + (yPoints[prevIndex] - yPoints[i]) / (2.0 * arcRadius);
			double x2 = xPoints[i] + (xPoints[nextIndex] - xPoints[i]) / (2.0 * arcRadius);
			double y2 = yPoints[i] + (yPoints[nextIndex] - yPoints[i]) / (2.0 * arcRadius);

			if (i == 0) {
				path.moveTo(x1, y1); // Start the path at the first calculated point
			} else {
				path.lineTo(x1, y1); // Draw line to the start of the corner arc
			}

			// Draw the rounded corner arc
			path.quadTo(xPoints[i], yPoints[i], x2, y2); // Control point is the corner
		}
		path.closePath();
		return path;
	}

	@Override
	public ComponentBase copy() {

		Relation copy = new Relation(getText());
		copy.setLocation(this.getLocation());
		copy.setSize(this.getSize());
		copy.setPreferredSize(this.getPreferredSize());

		return copy;
	}
}
