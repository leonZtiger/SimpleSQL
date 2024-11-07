package com.simpleSQL.eerModel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ComponentBase extends JComponent {

	private String text;
	protected Point pos;
	protected Color color = Color.white;
	protected final static Font font = new Font(Font.SERIF, Font.BOLD, 18);
	protected final static int borderWidth = 5;

	public ComponentBase(int width, int height, String text) {
		super();
		this.text = text;
		pos = new Point(0, 0);
		setSize(width, height);
		setLocation(pos);
		setPreferredSize(new Dimension(width, height));

		MouseAdapter adapter = new MouseAdapter() {

			private Point origin;

			@Override
			public void mousePressed(MouseEvent e) {
				origin = new Point(e.getPoint());
				setSelectionColor();
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setNoneSelectionColor();
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (origin != null) {

					int deltaX = origin.x - e.getX();
					int deltaY = origin.y - e.getY();

					setLocation(getLocation().x - deltaX, getLocation().y - deltaY);
				}
			}

		};

		addMouseListener(adapter);
		addMouseMotionListener(adapter);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.white);
		// Center text
		FontMetrics metrics = g.getFontMetrics(font);
		g2d.setFont(font);
		g2d.drawString(text, pos.x + getWidth() / 2 - metrics.stringWidth(text) / 2,
				pos.y + getHeight() / 2 + borderWidth);

		// Set stroke for childrens draw functions
		g2d.setStroke(new BasicStroke(borderWidth));
	}

	protected void setSelectionColor() {
		color = Color.cyan;
	}

	protected void setNoneSelectionColor() {
		color = Color.white;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
