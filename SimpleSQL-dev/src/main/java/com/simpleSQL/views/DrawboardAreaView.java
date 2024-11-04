package com.simpleSQL.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class DrawboardAreaView extends JScrollPane {

	private CheckerPatternJPanel drawArea;
	public DrawboardAreaView() {
		super();

		drawArea = new CheckerPatternJPanel();
		drawArea.setPreferredSize(new Dimension(5000, 5000));
		drawArea.setAutoscrolls(true);
		// background.setBackground(Theme.darkText);
		add(drawArea);
		setViewportView(drawArea);

		MouseAdapter scrollByMouseHandler = new MouseAdapter() {

			private Point origin;

			@Override
			public void mousePressed(MouseEvent e) {
				origin = new Point(e.getPoint());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (origin != null) {

					int deltaX = origin.x - e.getX();
					int deltaY = origin.y - e.getY();

					// Create a rectangle thats the same shape as the viewport
					Rectangle view = viewport.getViewRect();
					// Add the mouse delta to the viewport
					// to shift it in the direction we want to look at
					view.x += deltaX;
					view.y += deltaY;
					// Force scroll so that the view is showed in the viewport
					drawArea.scrollRectToVisible(view);
				}
			}

		};
		drawArea.addMouseListener(scrollByMouseHandler);
		drawArea.addMouseMotionListener(scrollByMouseHandler);
		drawArea.add(UiUtil.createButton("icons/play.png", 500));
	}
	
	public JPanel getDrawArea() {
		return drawArea;
	}

	public class CheckerPatternJPanel extends JPanel {

		final int checkerSize;
		float scale = 1;

		public CheckerPatternJPanel() {
			super();

			checkerSize = 50;
			
			addMouseWheelListener(new MouseWheelListener() {

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					
					scale += e.getPreciseWheelRotation();
					
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;
		
			// draw checker board pattern as background
			g2d.setColor(Theme.lightBackground);
			// Draw horizontal lines
			int hLine = getHeight() / checkerSize;
			for (int i = 0; i < hLine; i++) {
				int y = i * checkerSize;
				g2d.drawLine(0, y, getWidth(), y);
			}
			// Draw vertical lines
			int vLine = getWidth() / checkerSize;
			for (int i = 0; i < vLine; i++) {
				int x = checkerSize * i;
				g2d.drawLine(x, 0, x, getHeight());
			}
			
		
		}

	}
}
