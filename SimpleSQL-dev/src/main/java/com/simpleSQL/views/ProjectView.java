package com.simpleSQL.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.text.TextAction;

import com.simpleSQL.eerModel.ComponentBase;

public class ProjectView extends JPanel {

	private DrawboardAreaView drawboard;
	private ProjectListView listView;

	public ProjectView() {
		super();

		drawboard = new DrawboardAreaView();

		listView = new ProjectListView();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		GridBagLayout layout = new GridBagLayout();

		setLayout(layout);
		c.weighty = 1;
		c.weightx = 0.85;
		add(drawboard, c);
		c.weightx = 0.15;
		add(listView, c);

	}

	public void setPopupMenu(JPopupMenu menu) {
		drawboard.getDrawArea().setComponentPopupMenu(menu);
	}

	public void setData(ArrayList<ComponentBase> data) {
		
	}
	
	public void addComponent(ComponentBase b) {
		drawboard.getDrawArea().add(b);
	}

	public Object getCurrentTextInput() {
		// TODO Auto-generated method stub
		return null;
	}

	public Point getLastMouseLooc() {
		// TODO Auto-generated method stub
		return null;
	}

}
