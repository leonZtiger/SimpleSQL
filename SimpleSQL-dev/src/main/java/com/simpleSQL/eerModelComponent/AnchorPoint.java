package com.simpleSQL.eerModelComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

/***
 * Represents a anchor point thats rendered as a circle. Should be used for
 * setting connections start/end points of ComponentBase objects.
 */
public class AnchorPoint extends JComponent {

	// Diameter of circle
	public static final int SIZE = 20;
	// Border width of the rendered circle
	private static final int BORDER_WIDTH = 1;

	/***
	 * Constructs a AnchorPoint that is placed at the passed point relative to its parent.
	 * 
	 * @param point the Point of which this anchor is placed relative to its parent
	 */
	public AnchorPoint(Point point) {
		super();
		
		setPreferredSize(new Dimension(AnchorPoint.SIZE, AnchorPoint.SIZE));
		setBounds(new Rectangle(point.x, point.y, AnchorPoint.SIZE, AnchorPoint.SIZE));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		RadialGradientPaint paint = new RadialGradientPaint(new Point(SIZE / 3, SIZE / 3), (float) SIZE / 2,
				new float[] { 0.05f, 0.9f }, new Color[] { Color.white, Color.gray }, CycleMethod.REFLECT);
		g2d.setPaint(paint);
		g2d.fillOval(0, 0, SIZE, SIZE);

		g2d.setColor(Color.DARK_GRAY);
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
		g2d.drawOval(BORDER_WIDTH / 2, BORDER_WIDTH / 2, SIZE - BORDER_WIDTH, SIZE - BORDER_WIDTH);

	}
}
