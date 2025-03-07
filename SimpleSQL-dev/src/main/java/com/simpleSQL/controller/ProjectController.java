package com.simpleSQL.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.text.TextAction;

import com.simpleSQL.eerModelComponent.AnchorPoint;
import com.simpleSQL.eerModelComponent.Attribute;
import com.simpleSQL.eerModelComponent.ComponentBase;
import com.simpleSQL.eerModelComponent.Connection;
import com.simpleSQL.eerModelComponent.Entity;
import com.simpleSQL.eerModelComponent.Relation;
import com.simpleSQL.model.ProjectModel;
import com.simpleSQL.view.PopupMenu;
import com.simpleSQL.view.project.ProjectView;
import com.simpleSQL.model.ProjectEvent;

/**
 * Controller for the ProjectView and ProjectModel, handling user interactions
 * such as key bindings and context menu actions.
 */
public class ProjectController {

	private ProjectView view;
	private ProjectModel model;
	private AnchorPoint tempAnchor = null;

	/**
	 * Constructs a ProjectController to manage interactions between the specified
	 * view and model.
	 *
	 * @param view  the ProjectView displaying the project components
	 * @param model the ProjectModel containing project data and logic
	 */
	public ProjectController(ProjectView view, ProjectModel model) {
		this.view = view;
		this.model = model;

		// Populate the view with data from the model
		view.setData(model.getData());

		// Setup popup menu with options
		JPopupMenu menu = new PopupMenu(createPopupOptions(model, view));
		view.setPopupMenu(menu);

		// Setup key bindings for common actions
		setupKeyBindings();

		model.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				String e = evt.getPropertyName();

				System.out.println(e);
				//view.refreshList(model.getData());

			}
		});
	}

	/**
	 * Sets up key bindings for actions such as delete, copy, paste, and creating a
	 * connection. Key bindings are attached to the ProjectView component.
	 */
	private void setupKeyBindings() {
		// Delete action
		AbstractAction onDelete = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.deleteComponents(model.getSelected());
				model.deleteSelected();
			}
		};
		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_DELETE, onDelete);

		// Copy action
		AbstractAction onCopy = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.copy(model.getSelected());
			}
		};
		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK, onCopy);

		// Paste action
		AbstractAction onPaste = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.paste().forEach(o -> {
					o.addMouseListener(ProjectModel.getComponentMouseListener(model, o));
					view.addComponent(o);
					view.repaint();
				});
			}
		};
		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK, onPaste);

		// Create connection action
		AbstractAction onMakeConnection = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Placeholder for connection creation logic
			}
		};
		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK, onMakeConnection);
	}

	/**
	 * Creates an ArrayList of context menu options as TextActions for the
	 * ProjectView. Each action performs a specific function, such as adding an
	 * Entity, Relation, or Attribute.
	 *
	 * @param model the ProjectModel to interact with
	 * @param view  the ProjectView to update
	 * @return an ArrayList of TextActions for the popup menu
	 */
	private ArrayList<TextAction> createPopupOptions(ProjectModel model, ProjectView view) {
		ArrayList<TextAction> actions = new ArrayList<>();

		actions.add(PopupMenu.createTextAction("Add Entity", KeyEvent.VK_E, ActionEvent.CTRL_MASK, e -> {
			Entity entity = (Entity) ProjectModel.createNewComponent(Entity.class, model);
			if (entity != null) {

				entity.getAnchors().forEach(a -> {
					setComponentAnchorListener(a);
				});

				model.add(entity);
				view.addComponent(entity);
				entity.setLocation(view.getLastRightClick());
				entity.repaint();
			}
		}));

		actions.add(PopupMenu.createTextAction("Add Relation", KeyEvent.VK_R, ActionEvent.CTRL_MASK, e -> {
			Relation relation = (Relation) ProjectModel.createNewComponent(Relation.class, model);
			if (relation != null) {

				relation.getAnchors().forEach(a -> {
					setComponentAnchorListener(a);
				});
				model.add(relation);
				view.addComponent(relation);
				relation.setLocation(view.getLastRightClick());
				relation.repaint();
			}
		}));

		actions.add(PopupMenu.createTextAction("Add Attribute", KeyEvent.VK_A, ActionEvent.CTRL_MASK, e -> {
			Attribute attribute = (Attribute) ProjectModel.createNewComponent(Attribute.class, model);
			if (attribute != null) {

				attribute.getAnchors().forEach(a -> {
					setComponentAnchorListener(a);
				});

				model.add(attribute);
				view.addComponent(attribute);
				attribute.setLocation(view.getLastRightClick());
				attribute.repaint();
			}
		}));

		// TODO Actions for distinct and overlapping attributes
		actions.add(PopupMenu.createTextAction("Add Distinct", KeyEvent.VK_D, ActionEvent.CTRL_MASK, e -> {
			System.out.println("TODO! Adding distinct attribute...");
		}));

		actions.add(PopupMenu.createTextAction("Add Overlapping", KeyEvent.VK_O, ActionEvent.CTRL_MASK, e -> {
			System.out.println("TODO! Adding overlapping attribute...");
		}));

		return actions;
	}

	/***
	 * Adds a configured listener for the passed AnchorPoint, that handles clicks.
	 * 
	 * @param a AnchorPoint to add mouse event listener to
	 */
	private void setComponentAnchorListener(AnchorPoint a) {

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				a.getParent().dispatchEvent(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (tempAnchor != null) {
					view.addConnection(new Connection(tempAnchor, a));
					view.repaint();
					tempAnchor = null;
				}
				tempAnchor = a;
				a.getParent().getParent().dispatchEvent(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				a.getParent().getParent().dispatchEvent(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				a.getParent().getParent().dispatchEvent(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				a.getParent().getParent().dispatchEvent(e);
			}

		};

		MouseMotionListener motionListener = new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				a.getParent().getParent().dispatchEvent(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				a.getParent().getParent().dispatchEvent(e);
			}
		};

		a.addMouseListener(mouseListener);
		a.addMouseMotionListener(motionListener);
	}
}
