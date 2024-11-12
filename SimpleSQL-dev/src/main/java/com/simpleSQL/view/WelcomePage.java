package com.simpleSQL.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.net.URL;

/**
 * A welcome page view embedded within a JScrollPane that uses JavaFX's WebView to display HTML content.
 * Loads an introductory HTML page or displays an error message if the page cannot be found.
 */
public class WelcomePage extends JScrollPane {

    /**
     * Constructs the WelcomePage, initializing a JFXPanel to display JavaFX content within Swing.
     */
    public WelcomePage() {
        // Create a JFXPanel, which serves as a bridge between Swing and JavaFX
        JFXPanel jfxPanel = new JFXPanel();
        setViewportView(jfxPanel);

        // Initialize JavaFX components on the JavaFX Application Thread
        Platform.runLater(() -> initializeJavaFX(jfxPanel));
    }

    /**
     * Initializes JavaFX components, setting up a WebView to load the welcome HTML page.
     * If the page is not found, it displays an error message in the WebView.
     *
     * @param jfxPanel the JFXPanel that will display the JavaFX Scene containing the WebView
     */
    private void initializeJavaFX(JFXPanel jfxPanel) {
        // Create a WebView and WebEngine to load and render HTML content
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load the welcome HTML file, or an error message if the file is not found
        URL path = WelcomePage.class.getClassLoader().getResource("pages/welcome.html");
        if (path != null) {
            webEngine.load(path.toExternalForm());
        } else {
            webEngine.loadContent("<html><body><h2>Failed to load welcome page</h2><p>Check the file path and try again.</p></body></html>");
        }

        // Set up the JavaFX Scene with the WebView and assign it to the JFXPanel
        Scene scene = new Scene(webView);
        jfxPanel.setScene(scene);
    }
}
