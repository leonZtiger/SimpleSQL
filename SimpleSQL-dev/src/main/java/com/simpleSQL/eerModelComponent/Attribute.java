package com.simpleSQL.eerModelComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Represents an Attribute component in the EER model with custom rendering.
 * This component appears as an oval with a gradient background and border,
 * and darkens when selected.
 */
public class Attribute extends ComponentBase {

    // Default background color for the attribute
    public static final Color BACKGROUND_COLOR = new Color(180, 180, 180);

    // Default border color for the attribute
    public static final Color BORDER_COLOR = new Color(120, 120, 120);

    /**
     * Constructs an Attribute with the specified text.
     *
     * @param text the text to display within the attribute
     */
    public Attribute(String text) {
        super(150, 75, text);
    }

    /**
     * Paints the component with a custom gradient background and border.
     * Adjusts colors if the component is selected.
     *
     * @param g the Graphics object for rendering
     */
    @Override
    public void paintComponent(Graphics g) {
        // Set background and border colors, darkened if selected
        Color backgroundTemp = selected ? BACKGROUND_COLOR.darker() : BACKGROUND_COLOR;
        Color borderTemp = selected ? BORDER_COLOR.darker() : BORDER_COLOR;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create and set a gradient paint for the oval background
        GradientPaint gradient = new GradientPaint(0, 0, backgroundTemp.brighter(), getWidth(), getHeight(), backgroundTemp);
        g2d.setPaint(gradient);
        g2d.fillOval(BORDER_WIDTH / 2, BORDER_WIDTH / 2, getWidth() - BORDER_WIDTH, getHeight() - BORDER_WIDTH);

        // Set border color and stroke, then draw the oval border
        g2d.setColor(borderTemp);
        g2d.setStroke(new BasicStroke(BORDER_WIDTH));
        g2d.drawOval(BORDER_WIDTH / 2, BORDER_WIDTH / 2, getWidth() - BORDER_WIDTH, getHeight() - BORDER_WIDTH);

        // Call the superclass method to paint additional elements if necessary
        super.paintComponent(g);
    }

    /**
     * Creates a copy of this Attribute with the same properties, location, and size.
     *
     * @return a new Attribute instance with the same data as this one
     */
    @Override
    public ComponentBase copy() {
        Attribute copy = new Attribute(getText());
        copy.setLocation(this.getLocation());
        copy.setSize(this.getSize());
        copy.setPreferredSize(this.getPreferredSize());

        return copy;
    }
}
