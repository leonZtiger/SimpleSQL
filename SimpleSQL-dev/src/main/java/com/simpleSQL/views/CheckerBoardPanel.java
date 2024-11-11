package com.simpleSQL.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

public class CheckerBoardPanel extends JPanel {

	public static int checkerSize = 25;
	float scale = 1;

	public CheckerBoardPanel() {
		super();
		setLayout(null);
		
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
