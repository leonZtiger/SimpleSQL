package com.simpleSQL.eerModelComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * Represents a Relation component in the EER model with custom rendering.
 * This component appears as a diamond shape with rounded corners, a gradient background,
 * and a border that darkens when selected.
 */
public class Relation extends ComponentBase {

    // Default background color for the relation component
    public static final Color BACKGROUND_COLOR = new Color(161, 217, 155);

    // Default border color for the relation component
    public static final Color BORDER_COLOR = new Color(34, 139, 34);

    /**
     * Constructs a Relation component with the specified text.
     *
     * @param text the text to display within the relation component
     */
    public Relation(String text) {
        super(200, 100, text);
    }

    /**
     * Paints the component with a custom gradient background and rounded diamond shape.
     * Adjusts colors if the component is selected.
     *
     * @param g the Graphics object for rendering
     */
    @Override
    protected void paintComponent(Graphics g) {
        Color backgroundTemp = selected ? BACKGROUND_COLOR.darker() : BACKGROUND_COLOR;
        Color borderTemp = selected ? BORDER_COLOR.darker() : BORDER_COLOR;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set up gradient fill for the component background
        GradientPaint gradient = new GradientPaint(0, 0, BACKGROUND_COLOR.brighter(), getWidth(), getHeight(), backgroundTemp);
        g2d.setPaint(gradient);

        // Define a diamond shape and apply rounded corners
        Polygon diamond = new Polygon();
        diamond.addPoint(getWidth() / 2, BORDER_WIDTH);
        diamond.addPoint(getWidth() - BORDER_WIDTH, getHeight() / 2);
        diamond.addPoint(getWidth() / 2, getHeight() - BORDER_WIDTH);
        diamond.addPoint(BORDER_WIDTH, getHeight() / 2);

        // Fill the rounded diamond shape
        g2d.fill(createRoundedPolygon(diamond.xpoints, diamond.ypoints, CORNER_ARC));

        // Draw the border of the rounded diamond shape
        g2d.setStroke(new BasicStroke(BORDER_WIDTH));
        g2d.setColor(borderTemp);
        g2d.draw(createRoundedPolygon(diamond.xpoints, diamond.ypoints, CORNER_ARC));

        // Draw the component's text centered within the shape
        super.paintComponent(g);
    }

    /**
     * Creates a rounded polygon shape with arcs at each corner, based on the provided coordinates.
     *
     * @param xPoints   the x-coordinates of the polygon vertices
     * @param yPoints   the y-coordinates of the polygon vertices
     * @param arcRadius the radius of the corner arcs
     * @return a Shape object representing the rounded polygon
     */
    private Shape createRoundedPolygon(int[] xPoints, int[] yPoints, int arcRadius) {
        Path2D path = new Path2D.Float();
        int nPoints = xPoints.length;

        for (int i = 0; i < nPoints; i++) {
            int nextIndex = (i + 1) % nPoints;
            int prevIndex = (i - 1 + nPoints) % nPoints;

            // Calculate start and end points for the arc at each corner
            double x1 = xPoints[i] + (xPoints[prevIndex] - xPoints[i]) / (2.0 * arcRadius);
            double y1 = yPoints[i] + (yPoints[prevIndex] - yPoints[i]) / (2.0 * arcRadius);
            double x2 = xPoints[i] + (xPoints[nextIndex] - xPoints[i]) / (2.0 * arcRadius);
            double y2 = yPoints[i] + (yPoints[nextIndex] - yPoints[i]) / (2.0 * arcRadius);

            if (i == 0) {
                path.moveTo(x1, y1); // Start the path at the first calculated point
            } else {
                path.lineTo(x1, y1); // Draw line to the start of the corner arc
            }

            // Draw the rounded corner arc
            path.quadTo(xPoints[i], yPoints[i], x2, y2); // Control point is the corner
        }
        path.closePath();
        return path;
    }

    /**
     * Creates a copy of this Relation component with the same properties, location, and size.
     *
     * @return a new Relation instance with the same data as this one
     */
    @Override
    public ComponentBase copy() {
        Relation copy = new Relation(getText());
        copy.setLocation(this.getLocation());
        copy.setSize(this.getSize());
        copy.setPreferredSize(this.getPreferredSize());

        return copy;
    }
}
