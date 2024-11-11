package com.simpleSQL.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {

	public MainView(ConsoleView consoleView, MenuBarView menuBarView, WorkbenchView workbenchView) {
		super();
	

		setTitle("SimpleSQL");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(consoleView, BorderLayout.SOUTH);
		add(workbenchView, BorderLayout.CENTER);
		add(menuBarView, BorderLayout.NORTH);
	}

}
