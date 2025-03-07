package com.simpleSQL.eerModelComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JViewport;
import com.simpleSQL.view.project.CheckerBoardPanel;

/**
 * An abstract base class representing a draggable component with text, a
 * selectable state, and optional grid snapping.
 */
public abstract class ComponentBase extends JComponent {

	// Text displayed within the component
	private String text;
	// The rendered size
	protected int width, height;
	// Anchor points for connections with the component
	protected ArrayList<AnchorPoint> anchorPoints;
	// Default colors and styles
	protected Color color = Color.white;
	protected boolean selected = false;
	protected static final Font FONT = new Font("Roboto", Font.BOLD, 18);
	protected static final int BORDER_WIDTH = 5;
	protected static final int PADDING = AnchorPoint.SIZE / 2;
	protected static final int CORNER_ARC = 10;

	// Snap-to-grid setting
	public static boolean snapToGrid = true;

	/**
	 * Constructs a ComponentBase with specified size and text.
	 *
	 * @param width  the width of the component
	 * @param height the height of the component
	 * @param text   the text displayed within the component
	 */
	public ComponentBase(int width, int height, String text) {
		super();

		this.width = width;
		this.height = height;

		setText(text);
		// Add padding for AnchorPoints, so they fit inside of the view bound
		setSize(width + PADDING * 2, height + PADDING * 2);
		setPreferredSize(getSize());
		// Add mouse listener and motion listener for dragging functionality
		MouseAdapter adapter = createMouseAdapter();
		addMouseListener(adapter);
		addMouseMotionListener(adapter);

		// Initialize connection points
		// _____X______
		// |-----------|
		// X-Component-X
		// |_____X_____|
		//
		// X = placement
		anchorPoints = new ArrayList<AnchorPoint>();
		// Top point
		anchorPoints.add(createAndAddAnchorPoint(getWidth() / 2 - PADDING, 0));
		// Right point
		anchorPoints.add(createAndAddAnchorPoint(getWidth() - PADDING * 2, getHeight() / 2 - PADDING));
		// Bottom point
		anchorPoints.add(createAndAddAnchorPoint(getWidth() / 2 - PADDING, getHeight() - PADDING * 2));
		// Left point
		anchorPoints.add(createAndAddAnchorPoint(0, getHeight() / 2 - PADDING));

	}

	/***
	 * Creates a AnchorPoint for this object and also adds it to its JComponent
	 * children.
	 * 
	 * @param x center x coordinate of anchor point
	 * @param y center y coordinate of anchor point
	 */
	private AnchorPoint createAndAddAnchorPoint(int x, int y) {

		AnchorPoint anchor = new AnchorPoint(new Point(x, y));
		add(anchor);
		return anchor;
	}

	/**
	 * Creates a MouseAdapter to handle dragging and selecting behaviors.
	 * 
	 * @return a configured MouseAdapter for this component
	 */
	private MouseAdapter createMouseAdapter() {
		return new MouseAdapter() {
			private Point origin;

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					origin = e.getPoint();
					repaint();
					setCursor(new Cursor(Cursor.MOVE_CURSOR));
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				repaint();
				origin = null;
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (origin != null) {
					// Calculate delta movement
					Point delta = new Point(origin.x - e.getX(), origin.y - e.getY());

					// Calculate new location
					Point coord = new Point(getLocation().x - delta.x, getLocation().y - delta.y);

					// Adjust viewport if dragged outside visible area
					Rectangle view = adjustViewport(coord, delta);

					// Apply grid snapping if enabled
					if (snapToGrid) {
						coord = snapToGrid(coord, CheckerBoardPanel.checkerSize);
					}

					// Set component's new location
					setLocation(coord);

					((JComponent) getParent()).scrollRectToVisible(view);
				}
			}

			/**
			 * Adjusts the viewport position to ensure the component remains visible during
			 * dragging.
			 */
			private Rectangle adjustViewport(Point coord, Point delta) {

				Rectangle view = ((JViewport) getParent().getParent()).getViewRect();
				// If drag is outside of view port, scroll into place
				if (view.x > coord.x)
					view.x -= delta.x;
				else if ((view.x + view.width - getWidth()) < coord.x)
					view.x -= delta.x;

				if (view.y > coord.y)
					view.y -= delta.y;
				else if ((view.y + view.height - getHeight()) < coord.y)
					view.y -= delta.y;

				coord.x = Math.max(Math.min(getParent().getWidth() - getWidth(), coord.x), 0);
				coord.y = Math.max(Math.min(getParent().getHeight() - getHeight(), coord.y), 0);

				return view;
			}

			/**
			 * Snaps the coordinate to the nearest grid size.
			 */
			private Point snapToGrid(Point coord, int gridSize) {
				return new Point(Math.round((float) coord.x / gridSize) * gridSize,
						Math.round((float) coord.y / gridSize) * gridSize);
			}
		};
	}

	/**
	 * Abstract method for creating a copy of this component.
	 *
	 * @return a new instance of ComponentBase with the same data
	 */
	public abstract ComponentBase copy();

	/**
	 * Paints the component, rendering its text centered within it.
	 *
	 * @param g the Graphics object used for painting
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);

		// Center the text within the component
		FontMetrics metrics = g.getFontMetrics(FONT);
		g2d.setFont(FONT);
		int x = getWidth() / 2 - metrics.stringWidth(text) / 2;
		int y = height / 2 + PADDING;
		g2d.drawString(text, x, y);
	}

	/***
	 * Returns all the AnchorPoints in this component.
	 * 
	 * @return List of all anchors points in this component
	 */
	public ArrayList<AnchorPoint> getAnchors() {
		return anchorPoints;
	}

	/**
	 * Abstract method to get the components background color.
	 * 
	 * @return color of the component
	 */
	public abstract Color getColor();

	/**
	 * Sets this component to a selected state and repaints it.
	 */
	public void setSelected() {
		selected = true;
		repaint();
	}

	/**
	 * Sets this component to a not-selected state and repaints it.
	 */
	public void setNotSelected() {
		selected = false;
		repaint();
	}

	/**
	 * Returns the text displayed within the component.
	 *
	 * @return the text content of the component
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of the component, formatting the text to capitalize the first
	 * letter.
	 *
	 * @param text the text to display within the component
	 */
	public void setText(String text) {
		if (text == null || text.isEmpty()) {
			this.text = "";
			return;
		}

		// Capitalize the first letter and lower-case the rest
		text = text.trim();
		this.text = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();

		repaint();
	}
}
