package com.simpleSQL.controllers;

import java.awt.event.*;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.KeyStore.Entry;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.TextAction;

import com.simpleSQL.eerModel.Attribute;
import com.simpleSQL.eerModel.Entity;
import com.simpleSQL.eerModel.Relation;
import com.simpleSQL.models.ProjectModel;
import com.simpleSQL.views.PopupMenu;
import com.simpleSQL.views.ProjectView;

public class ProjectController {

	private ProjectView view;
	private ProjectModel model;

	public ProjectController(ProjectView view, ProjectModel model) {
		this.view = view;
		this.model = model;

		// Fill view with data from model
		view.setData(model.getData());

		JPopupMenu menu = new PopupMenu(createPopupOptions(model, view));
		view.setPopupMenu(menu);

		// Setup key bindings
		view.setFocusable(true);

		AbstractAction onDelete = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.deleteComponents(model.getSelected());
				model.deleteSelected();
			}
		};

		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_DELETE, onDelete);

		AbstractAction onCopy = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.copy(model.getSelected());
			}
		};

		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK, onCopy);

		AbstractAction onPaste = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.paste().forEach(o -> {
					o.addMouseListener(ProjectModel.getComponentMouseListener(model, o));
					view.addComponent(o);

				});
			}

		};

		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK, onPaste);

		AbstractAction onMakeConnection = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		};

		KeyUtil.addOnPressEnabledBinding(view, KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK, onMakeConnection);

	}

	private ArrayList<TextAction> createPopupOptions(ProjectModel model, ProjectView view) {

		ArrayList<TextAction> actions = new ArrayList<TextAction>();

		actions.add(
				PopupMenu.createTextAction("Add Entity", KeyEvent.VK_E, ActionEvent.CTRL_MASK, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						Entity entity = (Entity) ProjectModel.createNewComponent(Entity.class, model);

						if (entity == null)
							return;

						model.add(entity);
						view.addComponent(entity);
						entity.setLocation(view.getLastRightClick());
						entity.repaint();

					}
				}));
		actions.add(
				PopupMenu.createTextAction("Add Relation", KeyEvent.VK_R, ActionEvent.CTRL_MASK, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						Relation relation = (Relation) ProjectModel.createNewComponent(Relation.class, model);

						if (relation == null)
							return;

						model.add(relation);
						view.addComponent(relation);
						relation.setLocation(view.getLastRightClick());
						relation.repaint();
					}
				}));
		actions.add(
				PopupMenu.createTextAction("Add Attribute", KeyEvent.VK_A, ActionEvent.CTRL_MASK, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						Attribute attribute = (Attribute) ProjectModel.createNewComponent(Attribute.class, model);

						if (attribute == null)
							return;

						model.add(attribute);
						view.addComponent(attribute);
						attribute.setLocation(view.getLastRightClick());
						attribute.repaint();
					}
				}));
		actions.add(
				PopupMenu.createTextAction("Add Distinct", KeyEvent.VK_D, ActionEvent.CTRL_MASK, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Adding...");
					}
				}));

		actions.add(PopupMenu.createTextAction("Add Overlapping", KeyEvent.VK_O, ActionEvent.CTRL_MASK,
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Adding...");
					}
				}));

		return actions;
	}
}
