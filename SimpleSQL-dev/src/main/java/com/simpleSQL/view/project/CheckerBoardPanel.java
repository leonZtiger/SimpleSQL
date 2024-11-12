package com.simpleSQL.view.project;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * A custom JPanel that displays a checkerboard background pattern.
 * Allows zooming with the mouse wheel by adjusting the scale.
 */
public class CheckerBoardPanel extends JPanel {

    /** Size of each checker square in pixels */
    public static int checkerSize = 25;

    /** Scale factor for zooming, modified by mouse wheel actions */
    private float scale = 1.0f;

    /**
     * Constructs the CheckerBoardPanel, setting up a mouse wheel listener
     * for zoom functionality.
     */
    public CheckerBoardPanel() {
        super();
        setLayout(null);

        // Mouse wheel listener to adjust the scale factor on scroll
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                scale += e.getPreciseWheelRotation();
            }
        });
    }

    /**
     * Paints the checkerboard pattern as the background of the panel.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the checkerboard color from the current UI theme
        g2d.setColor(UIManager.getColor("Button.background"));

        // Draw horizontal lines for the checkerboard
        int horizontalLines = getHeight() / checkerSize;
        for (int i = 0; i < horizontalLines; i++) {
            int y = i * checkerSize;
            g2d.drawLine(0, y, getWidth(), y);
        }

        // Draw vertical lines for the checkerboard
        int verticalLines = getWidth() / checkerSize;
        for (int i = 0; i < verticalLines; i++) {
            int x = i * checkerSize;
            g2d.drawLine(x, 0, x, getHeight());
        }
    }
}
