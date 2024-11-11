package com.simpleSQL.views;

import java.awt.BorderLayout;
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
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.text.TextAction;

import com.simpleSQL.eerModel.ComponentBase;
import com.simpleSQL.eerModel.Connection;

public class ProjectView extends JPanel {

	private DrawboardAreaView drawboard;
	private ProjectListView listView;
	private EerModelTools eerModelTools;

	public ProjectView() {
		super();

		drawboard = new DrawboardAreaView();
		listView = new ProjectListView();
		eerModelTools = new EerModelTools();
		setLayout(new BorderLayout());
	
		add(drawboard, BorderLayout.CENTER);
		add(listView,  BorderLayout.EAST);
		add(eerModelTools,  BorderLayout.NORTH);

	}
	
	public void refresh(ArrayList<ComponentBase> comps) {
		
		listView.setTree(null);
	}
	
	public void setPopupMenu(JPopupMenu menu) {
		drawboard.getDrawArea().setComponentPopupMenu(menu);
	}

	public void setData(ArrayList<ComponentBase> data) {
		
	}

	public void addComponent(ComponentBase b) {
		drawboard.getDrawArea().add(b);
		drawboard.getDrawArea().setComponentZOrder(b, 0);
	}

	public Point getLastRightClick() {
		return drawboard.getLastRightClick();
	}

	public void setKeyListener(KeyListener keyListener) {
		drawboard.addKeyListener(keyListener);
	}

	public void deleteComponents(ArrayList<ComponentBase> selected) {
		selected.forEach(e -> {
			drawboard.getDrawArea().remove(e);
		});

		drawboard.getDrawArea().repaint();

	}

	public void addConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}
}
