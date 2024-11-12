package com.simpleSQL.eerModelComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

public class Connection extends JComponent {

	private ComponentBase a, b;

	public Connection(ComponentBase a, ComponentBase b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		Point aPos = a.getLocation();
		Point bPos = b.getLocation();

		g2d.drawLine(aPos.x, aPos.y, bPos.x, bPos.y);

	}
}
