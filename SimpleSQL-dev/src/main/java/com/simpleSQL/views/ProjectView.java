package com.simpleSQL.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JPanel;

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
}
