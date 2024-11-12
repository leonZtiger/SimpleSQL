package com.simpleSQL.eerModelComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JViewport;
import com.simpleSQL.view.project.CheckerBoardPanel;

/**
 * An abstract base class representing a draggable component with text, a
 * selectable state, and optional grid snapping. This component supports
 * customized actions on double-click.
 */
public abstract class ComponentBase extends JComponent {

	// Text displayed within the component
	private String text;

	// Action triggered on double-click
	private Action onDoubleClick;

	// Default colors and styles
	protected Color color = Color.white;
	protected boolean selected = false;
	protected static final Font FONT = new Font("Roboto", Font.BOLD, 18);
	protected static final int BORDER_WIDTH = 5;
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
		setText(text);
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));

		// Add mouse listener and motion listener for dragging functionality
		MouseAdapter adapter = createMouseAdapter();
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
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
	 * Abstract method to create a copy of this component.
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
		int y = getHeight() / 2 + BORDER_WIDTH;
		g2d.drawString(text, x, y);

		// Set stroke width for any child drawing operations
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
	}

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
	 * Sets the action to be performed on a double-click event.
	 *
	 * @param action the action triggered on double-click
	 */
	public void setOnDoubleClick(Action action) {
		onDoubleClick = action;
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
