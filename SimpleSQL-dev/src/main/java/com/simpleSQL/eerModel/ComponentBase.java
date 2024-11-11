package com.simpleSQL.eerModel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JViewport;

import com.simpleSQL.views.CheckerBoardPanel;

public abstract class ComponentBase extends JComponent {

	private String text;
	private Action onDubbelClick;
	protected Color color = Color.white;
	protected boolean selected = false;

	protected final static Font font = new Font("Roboto", Font.BOLD, 18);
	protected final static int borderWidth = 5;
	protected static final int cornerArc = 10;
	public static boolean snapToGrid = false;

	public ComponentBase(int width, int height, String text) {
		super();
		setText(text);

		setSize(width, height);
		setPreferredSize(new Dimension(width, height));

		MouseAdapter adapter = new MouseAdapter() {
			private Point origin;

			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					origin = new Point(e.getPoint());
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				repaint();
				origin = null;
			}

			@Override
			public void mouseDragged(MouseEvent e) {

				if (origin != null) {

					int deltaX = origin.x - e.getX();
					int deltaY = origin.y - e.getY();

					// Set new location
					int xCoord = getLocation().x - deltaX;
					int yCoord = getLocation().y - deltaY;

					// Get the viewport of the scroll container
					Rectangle view = ((JViewport) getParent().getParent()).getViewRect();

					// If drag is outside of view port, scroll into place
					if (view.x > xCoord)
						view.x -= deltaX;
					else if ((view.x + view.width - getWidth()) < xCoord)
						view.x -= deltaX;

					if (view.y > yCoord)
						view.y -= deltaY;
					else if ((view.y + view.height - getHeight()) < yCoord)
						view.y -= deltaY;

					xCoord = Math.max(Math.min(getParent().getWidth() - getWidth(), xCoord), 0);
					yCoord = Math.max(Math.min(getParent().getHeight() - getHeight(), yCoord), 0);

					// If snap to grids enabled

					// Snap to grid
					int snappedX = (int) Math.round((float) xCoord / CheckerBoardPanel.checkerSize)
							* CheckerBoardPanel.checkerSize;
					int snappedY = (int) Math.round((float) yCoord / CheckerBoardPanel.checkerSize)
							* CheckerBoardPanel.checkerSize;
					// Set location
					setLocation(snappedX, snappedY);

					// Scroll container to the new viewport
					((JComponent) getParent()).scrollRectToVisible(view);
				}
			}
		};

		addMouseListener(adapter);
		addMouseMotionListener(adapter);
	}

	public abstract ComponentBase copy();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);

		// Center text
		FontMetrics metrics = g.getFontMetrics(font);
		g2d.setFont(font);
		g2d.drawString(text, getWidth() / 2 - metrics.stringWidth(text) / 2, getHeight() / 2 + borderWidth);

		// Set stroke for children draw functions
		g2d.setStroke(new BasicStroke(borderWidth));
	}

	public void setSelect() {
		selected = true;
		repaint();
	}

	public void setOnDubbelClick(Action action) {
		onDubbelClick = action;
	}

	public void setNotSelect() {
		selected = false;
		repaint();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {

		if (text.length() < 1) {
			this.text = "";
			return;
		}

		// Format text
		text = text.trim();
		// Get first letter and make it uppercase
		String s = text.substring(0, 1).toUpperCase();
		// Set the rest to lower case
		text = text.toLowerCase();
		// Concatenate to get whole string
		text = s + text.substring(1);

		this.text = text;

		repaint();
	}

}
