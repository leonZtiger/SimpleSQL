package com.simpleSQL.eerModelComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

/**
 * Represents an Entity component in the EER model with custom rendering. This
 * component appears as a rounded rectangle with a gradient background and
 * border, and darkens when selected.
 */
public class Entity extends ComponentBase {

	// Default background color for the entity
	public static final Color BACKGROUND_COLOR = new Color(112, 161, 215);

	// Default border color for the entity
	public static final Color BORDER_COLOR = new Color(70, 130, 180);

	/**
	 * Constructs an Entity with the specified text.
	 *
	 * @param text the text to display within the entity
	 */
	public Entity(String text) {
		super(200, 100, text);
	}

	/**
	 * Creates a copy of this Entity with the same properties, location, and size.
	 *
	 * @return a new Entity instance with the same data as this one
	 */
	@Override
	public ComponentBase copy() {
		Entity copy = new Entity(getText());
		copy.setLocation(this.getLocation());
		copy.setSize(this.getSize());
		copy.setPreferredSize(this.getPreferredSize());
		copy.setComponentPopupMenu(this.getComponentPopupMenu());

		return copy;
	}

	/**
	 * Paints the component with a custom gradient background and border. Adjusts
	 * colors if the component is selected.
	 *
	 * @param g the Graphics object for rendering
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Color backgroundTemp = selected ? BACKGROUND_COLOR.darker() : BACKGROUND_COLOR;
		Color borderTemp = selected ? Color.orange : BORDER_COLOR;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the filled, rounded rectangle with gradient background
		GradientPaint gradient = new GradientPaint(0, 0, backgroundTemp.brighter(), getWidth(), getHeight(),
				backgroundTemp);
		g2d.setColor(Color.black);
		// g2d.fillRect(0, 0, getWidth(),getHeight());

		g2d.setPaint(gradient);
		g2d.fillRoundRect(PADDING, PADDING, width, height, CORNER_ARC, CORNER_ARC);

		// Draw the border of the rounded rectangle
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
		g2d.setColor(borderTemp);
		g2d.drawRoundRect(PADDING, PADDING, width, height, CORNER_ARC, CORNER_ARC);

		// Draw the text within the component, centered
		super.paintComponent(g);
	}

	/***
	 * Access method for the components background color
	 * 
	 * @return color of this component
	 */
	@Override
	public Color getColor() {
		return BACKGROUND_COLOR;
	}
}
