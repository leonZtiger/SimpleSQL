package com.simpleSQL.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
/***
 * Represents the main application view.
 */
public class MainView extends JFrame {

	/***
	 * Constructs the MainView based on the passed consoleView, menuBarView and workbenchView
	 * 
	 * @param consoleView
	 * @param menuBarView
	 * @param workbenchView
	 */
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
