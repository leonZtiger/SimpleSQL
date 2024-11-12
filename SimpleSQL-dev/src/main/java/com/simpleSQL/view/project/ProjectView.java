package com.simpleSQL.view.project;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import com.simpleSQL.eerModelComponent.ComponentBase;
import com.simpleSQL.eerModelComponent.Connection;

/**
 * The main view panel for a project, integrating a drawing area, project list,
 * and EER model tools. Provides methods to manage and display components
 * and connections, and enables custom popup menus.
 */
public class ProjectView extends JPanel {

    private final DrawboardAreaView drawboard;
    private final ProjectListView listView;
    private final EerModelTools eerModelTools;

    /**
     * Constructs the ProjectView, initializing the drawing area, project list view,
     * and model tools panel.
     */
    public ProjectView() {
        super();
        
        drawboard = new DrawboardAreaView();
        listView = new ProjectListView();
        eerModelTools = new EerModelTools();

        setLayout(new BorderLayout());
        add(drawboard, BorderLayout.CENTER);
        add(listView, BorderLayout.EAST);
        add(eerModelTools, BorderLayout.NORTH);
    }

    /**
     * Refreshes the project list view with updated components.
     *
     * @param components the list of ComponentBase items to display in the list view
     */
    public void refresh(ArrayList<ComponentBase> components) {
        listView.setTree(null);
    }

    /**
     * Sets a custom popup menu to the drawing area for additional context actions.
     *
     * @param menu the JPopupMenu to set on the drawing area
     */
    public void setPopupMenu(JPopupMenu menu) {
        drawboard.getDrawArea().setComponentPopupMenu(menu);
    }

    /**
     * Sets the data for the project view, updating displayed components.
     *
     * @param data the list of ComponentBase items to set as the project data
     */
    public void setData(ArrayList<ComponentBase> data) {
        // Implementation for setting data, if needed
    }

    /**
     * Adds a component to the drawing area and sets it to be in front.
     *
     * @param component the ComponentBase item to add to the draw area
     */
    public void addComponent(ComponentBase component) {
        drawboard.getDrawArea().add(component);
        drawboard.getDrawArea().setComponentZOrder(component, 0);
    }

    /**
     * Gets the position of the last right-click within the drawing area.
     *
     * @return the Point representing the last right-click position
     */
    public Point getLastRightClick() {
        return drawboard.getLastRightClick();
    }

    /**
     * Sets a KeyListener on the drawboard for handling keyboard events.
     *
     * @param keyListener the KeyListener to set on the drawboard
     */
    public void setKeyListener(KeyListener keyListener) {
        drawboard.addKeyListener(keyListener);
    }

    /**
     * Deletes selected components from the drawing area and repaints.
     *
     * @param selected the list of selected ComponentBase items to remove
     */
    public void deleteComponents(ArrayList<ComponentBase> selected) {
        selected.forEach(drawboard.getDrawArea()::remove);
        drawboard.getDrawArea().repaint();
    }

    /**
     * Adds a connection to the project view.
     *
     * @param connection the Connection to add to the project
     */
    public void addConnection(Connection connection) {
        // Implementation for adding a connection, if needed
    }
}
