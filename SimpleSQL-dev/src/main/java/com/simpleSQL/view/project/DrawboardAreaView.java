package com.simpleSQL.view.project;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A scrollable drawing area panel with a checkerboard background. Allows for
 * panning via dragging.
 */
public class DrawboardAreaView extends JScrollPane {

	private final CheckerBoardPanel drawArea;
	private Point lastRightClickPosition;
	private static int START_SIZE = 3000;

	/**
	 * Constructs the DrawboardAreaView, initializing a large drawing area with a
	 * checkerboard background and setting up mouse listeners for panning and
	 * position tracking.
	 */
	public DrawboardAreaView() {
		super();

		lastRightClickPosition = new Point(0, 0);
		drawArea = new CheckerBoardPanel();
		drawArea.setPreferredSize(new Dimension(START_SIZE, START_SIZE));
		drawArea.setAutoscrolls(true);
		add(drawArea);
		setViewportView(drawArea);

		// Mouse listener for panning and right-click tracking
		MouseAdapter scrollByMouseHandler = new MouseAdapter() {

			private Point origin;

			@Override
			public void mousePressed(MouseEvent e) {
				origin = new Point(e.getPoint());

				// Record position on right-click, for getting place position on adding
				// components
				if (e.getButton() == MouseEvent.BUTTON3) {
					lastRightClickPosition = e.getPoint();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (origin != null) {
					int deltaX = origin.x - e.getX();
					int deltaY = origin.y - e.getY();

					// Create a rectangle that represents the current viewport
					Rectangle view = getViewport().getViewRect();
					// Adjust viewport position based on drag direction
					view.x += deltaX;
					view.y += deltaY;

					// Scroll to ensure the new viewport location is visible
					drawArea.scrollRectToVisible(view);
				}
			}
		};

		drawArea.addMouseListener(scrollByMouseHandler);
		drawArea.addMouseMotionListener(scrollByMouseHandler);
	}

	/**
	 * Gets the position of the last right-click within the draw area.
	 *
	 * @return the Point representing the last right-click position within this
	 *         component
	 */
	public Point getLastRightClick() {
		return lastRightClickPosition;
	}

	/**
	 * Retrieves the main drawing area component
	 *
	 * @return the JPanel used as the drawing area
	 */
	public JPanel getDrawArea() {
		return drawArea;
	}
}
