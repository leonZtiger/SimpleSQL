package com.simpleSQL.model;

import java.util.prefs.Preferences;

/**
 * Manages application properties using the Preferences API.
 * Allows for setting and retrieving persistent user preferences.
 */
public class PropertiesModel {

    // Preferences instance to store and retrieve settings
    private static final Preferences prefs = Preferences.userNodeForPackage(PropertiesModel.class);

    /**
     * Sets a preference with the specified key and value.
     *
     * @param key   the property key to set
     * @param value the value to assign to the property
     */
    public static void setPreference(Property key, String value) {
        prefs.put(key.name(), value);
    }

    /**
     * Retrieves the value of a specified preference key.
     *
     * @param key the property key to retrieve
     * @return the stored value for the key, or an empty string if no value exists
     */
    public static String getPreference(Property key) {
        return prefs.get(key.name(), "");
    }
}
