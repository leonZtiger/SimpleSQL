package com.simpleSQL.views;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.TextAction;

public class DrawboardAreaView extends JScrollPane {

	private CheckerBoardPanel drawArea;
	private Point toAddPosition;

	public DrawboardAreaView() {
		super();

		toAddPosition = new Point(0, 0);
		drawArea = new CheckerBoardPanel();
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

				if (e.getButton() == e.BUTTON3) {
					toAddPosition = e.getPoint();
					System.out.println(e.getPoint().x);
				}
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

	}

	public Point getLastRightClick() {
		return toAddPosition;
	}

	public JPanel getDrawArea() {
		return drawArea;
	}

}
