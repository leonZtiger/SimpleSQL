package com.simpleSQL;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.simpleSQL.controllers.WorkbenchController;
import com.simpleSQL.models.WorkbenchModel;
import com.simpleSQL.views.ConsoleView;
import com.simpleSQL.views.MainView;
import com.simpleSQL.views.MenuBarView;
import com.simpleSQL.views.ToolBarView;
import com.simpleSQL.views.WorkbenchView;

public class Main {

	public static void main(String[] args) {
	
	
		
        SwingUtilities.invokeLater(() -> {
		// Setup Theme
		FlatLaf laf = new FlatDarkLaf();

		FlatLaf.registerCustomDefaultsSource("themes");

		FlatLaf.setup(laf);
		// Set UI design
		
		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Create views
		ConsoleView consoleView = new ConsoleView();
		MenuBarView menuBarView = new MenuBarView();
		ToolBarView toolBarView = new ToolBarView();
		WorkbenchView workbenchView = new WorkbenchView();
		MainView mainView = new MainView(consoleView,menuBarView,toolBarView,workbenchView);
		
		// Set window to 75% of main monitor size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.height *= 0.75;
		screenSize.width *= 0.75;
		mainView.pack();
		mainView.setSize(screenSize);
		mainView.setVisible(true);
		
		// Create Models
		WorkbenchModel workbenchModel = new WorkbenchModel();
		
		// Create controllers
		WorkbenchController workbenchController = new WorkbenchController(menuBarView,workbenchView, workbenchModel);
		
		
        });
	}
}
