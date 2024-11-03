package com.simpleSQL.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {

	private ConsoleView consoleView;
	private MenuBarView menuBarView;
	private ProjectListView projectListView;
	private ToolBarView toolBarView;
	private WorkbenchView workbenchView;

	public MainView(ConsoleView consoleView, MenuBarView menuBarView,
			ToolBarView toolBarView, WorkbenchView workbenchView) {
		super();
		this.consoleView = consoleView;
		this.menuBarView = menuBarView;
		this.projectListView = projectListView;
		this.toolBarView = toolBarView;
		this.workbenchView = workbenchView;

		setTitle("SimpleSQL");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Top section of window
		JPanel topJPanel = new JPanel();
		topJPanel.setLayout(new GridLayout(0, 1));
		topJPanel.add(menuBarView);
		topJPanel.add(toolBarView);

		add(consoleView, BorderLayout.SOUTH);
		add(workbenchView, BorderLayout.CENTER);
		add(topJPanel, BorderLayout.NORTH);
	}

}
