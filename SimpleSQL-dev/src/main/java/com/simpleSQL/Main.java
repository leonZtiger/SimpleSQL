package com.simpleSQL;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.simpleSQL.controller.WorkbenchController;
import com.simpleSQL.model.WorkbenchModel;
import com.simpleSQL.view.ConsoleView;
import com.simpleSQL.view.MainView;
import com.simpleSQL.view.MenuBarView;
import com.simpleSQL.view.WorkbenchView;

/**
 * The entry point of the SimpleSQL application.
 * Initializes the look and feel, creates the main views, models, and controllers,
 * and displays the main window.
 */
public class Main {

    /**
     * The main method to launch the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setupLookAndFeel();
            
            // Initialize Views
            ConsoleView consoleView = new ConsoleView();
            MenuBarView menuBarView = new MenuBarView();
            WorkbenchView workbenchView = new WorkbenchView();
            MainView mainView = new MainView(consoleView, menuBarView, workbenchView);

            // Initialize Models
            WorkbenchModel workbenchModel = new WorkbenchModel();

            // Initialize Controllers
            new WorkbenchController(menuBarView, workbenchView, workbenchModel);

            // Display the main application window
            showMainWindow(mainView);
        });
    }

    /**
     * Sets up the look and feel for the application using FlatLaf's Dark theme.
     * Registers any custom theme settings from the 'themes' source.
     */
    private static void setupLookAndFeel() {
        FlatLaf laf = new FlatDarkLaf();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatLaf.setup(laf);

        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
    }

    /**
     * Configures and displays the main window of the application.
     * Sets the window size to 75% of the main monitor's screen size.
     *
     * @param mainView the main application view
     */
    private static void showMainWindow(MainView mainView) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height *= 0.75;
        screenSize.width *= 0.75;
        mainView.pack();
        mainView.setSize(screenSize);
        mainView.setVisible(true);
    }
}
