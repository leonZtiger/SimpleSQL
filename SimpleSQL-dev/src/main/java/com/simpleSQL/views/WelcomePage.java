package com.simpleSQL.views;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WelcomePage extends JScrollPane {

    public WelcomePage() {
        // Create a JFXPanel, which acts as a bridge between Swing and JavaFX
        JFXPanel jfxPanel = new JFXPanel();
        setViewportView(jfxPanel);

        // Load the welcome HTML page in the JavaFX WebView
        Platform.runLater(() -> initializeJavaFX(jfxPanel));
    }

    private void initializeJavaFX(JFXPanel jfxPanel) {
        // Create a WebView and a WebEngine to load the HTML content
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load the HTML file into the WebEngine
        URL path = WelcomePage.class.getClassLoader().getResource("pages/welcome.html");
        if (path != null) {
            webEngine.load(path.toExternalForm());
        } else {
            // Load an error message if the page is not found
            webEngine.loadContent("<html><body><h2>Failed to load welcome page</h2><p>Check the file path and try again.</p></body></html>");
        }

        // Add the WebView to a JavaFX Scene and set the Scene in the JFXPanel
        Scene scene = new Scene(webView);
        jfxPanel.setScene(scene);
        
    }
}
