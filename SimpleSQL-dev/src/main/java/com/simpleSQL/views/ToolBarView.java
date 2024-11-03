package com.simpleSQL.views;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ToolBarView extends JPanel{

	public ToolBarView() {
		FlowLayout layout = new FlowLayout(FlowLayout.LEADING,30,7);
		layout.setAlignment(FlowLayout.LEFT);
		setLayout(layout);
	
		
		Component spacer =Box.createVerticalStrut(10);
		
		add(spacer);
		add(UiUtil.createButton("icons/play.png","Debug",Theme.headFont.getSize()));
		add(UiUtil.createButton("icons/play.png","Build",Theme.headFont.getSize()));
		add(UiUtil.createButton("icons/play.png","Run",Theme.headFont.getSize()));

	}
}
