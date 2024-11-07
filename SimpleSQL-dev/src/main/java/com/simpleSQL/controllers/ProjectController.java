package com.simpleSQL.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
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

		view.setPopupMenu(new PopupMenu(createPopupOptions(model, view)));
	}

	private ArrayList<TextAction> createPopupOptions(ProjectModel model, ProjectView view) {
		ArrayList<TextAction> actions = new ArrayList<TextAction>();

		actions.add(
				PopupMenu.createTextAction("Add Entity", KeyEvent.VK_E, ActionEvent.CTRL_MASK, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						Entity entity = (Entity) ProjectModel.createNewComponent(Entity.class, view.getLastMouseLooc());

						if (entity == null)
							return;

						model.add(entity);
						view.addComponent(entity);
					}
				}));
		actions.add(
				PopupMenu.createTextAction("Add Relation", KeyEvent.VK_R, ActionEvent.CTRL_MASK, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String text = JOptionPane.showInputDialog(view, "Set name of Relation");

						if (text == null)
							return;

						Relation relation = new Relation(view.getLastMouseLooc(), text);
						model.add(relation);
						view.addComponent(relation);
					}
				}));
		actions.add(
				PopupMenu.createTextAction("Add Attribute", KeyEvent.VK_A, ActionEvent.CTRL_MASK, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String text = JOptionPane.showInputDialog(view, "Set name of Attribute");

						if (text == null)
							return;

						Attribute attribute = new Attribute(view.getLastMouseLooc(), text);
						model.add(attribute);
						view.addComponent(attribute);
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
