package com.simpleSQL.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A temporary storage model for local application preferences.
 * Stores preferences in memory, allowing modifications to be applied 
 * to the main Preferences only when confirmed by the user.
 */
public class TempLocalPreferences {

    // Temporary storage for preference key-value pairs
    private final HashMap<Property, String> tempValues;

    /**
     * Constructs a TempLocalPreferences instance, initializing it with
     * the current preferences from PropertiesModel.
     */
    public TempLocalPreferences() {
        tempValues = new HashMap<>();

        // Load initial values from PropertiesModel
        List<Property> properties = Arrays.asList(Property.class.getEnumConstants());
        properties.forEach(property -> tempValues.put(property, PropertiesModel.getPreference(property)));
    }

    /**
     * Sets a temporary value for a specific preference property.
     *
     * @param key   the preference property to set
     * @param value the value to assign temporarily
     */
    public void set(Property key, String value) {
        tempValues.put(key, value);
    }

    /**
     * Retrieves the temporary value for a specific preference property.
     *
     * @param key the preference property to retrieve
     * @return the temporary value for the specified property
     */
    public String get(Property key) {
        return tempValues.get(key);
    }

    /**
     * Applies all temporary preferences to the main PreferencesModel,
     * committing them as permanent user preferences.
     */
    public void apply() {
        tempValues.forEach(PropertiesModel::setPreference);
    }
}
