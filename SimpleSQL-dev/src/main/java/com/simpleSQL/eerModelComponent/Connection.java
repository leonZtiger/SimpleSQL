package com.simpleSQL.eerModelComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComponent;

public class Connection extends JComponent {

	private AnchorPoint a, b;
	private static int LINE_WIDTH = 10;

	public Connection(AnchorPoint a, AnchorPoint b) {
		super();

		this.a = a;
		this.b = b;
		// Listen for location changes to update bounds and repaint when components move
		a.getParent().addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {
				updateBounds();
			}
		});

		b.getParent().addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {
				updateBounds();
			}
		});

		updateBounds();
	}

	private void updateBounds() {
		Point aPos = a.getParent().getLocation();
		Point bPos = b.getParent().getLocation();
		aPos.x += a.getX();
		aPos.y += a.getY();
		bPos.x += b.getX();
		bPos.y += b.getY();

		int x = Math.min(aPos.x, bPos.x);
		int y = Math.min(aPos.y, bPos.y);
		int width = Math.max(Math.abs(aPos.x - bPos.x), LINE_WIDTH) + AnchorPoint.SIZE;
		int height = Math.max(Math.abs(aPos.y - bPos.y), LINE_WIDTH) + AnchorPoint.SIZE;

		setBounds(x, y, width, height);
		repaint(); // Trigger repaint whenever bounds are updated
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Calculate positions relative to Connection component
		Point aPos = new Point(a.getParent().getX() - getX() + a.getX() + AnchorPoint.SIZE / 2,
				a.getParent().getY() - getY() + a.getY() + AnchorPoint.SIZE / 2);
		Point bPos = new Point(b.getParent().getX() - getX() + b.getX() + AnchorPoint.SIZE / 2,
				b.getParent().getY() - getY() + b.getY() + AnchorPoint.SIZE / 2);

		// Draw the gradient line
		GradientPaint paint = new GradientPaint(aPos, Color.ORANGE, bPos, Color.YELLOW);
		g2d.setPaint(paint);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(aPos.x, aPos.y, bPos.x, bPos.y);
		// g2d.fillRect(0, 0, getWidth(), getHeight());
	}
}
