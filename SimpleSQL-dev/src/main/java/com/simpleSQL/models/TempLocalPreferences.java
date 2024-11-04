package com.simpleSQL.models;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

public class TempLocalPreferences {

	private HashMap<Property, String> tempValues;

	public TempLocalPreferences() {
		tempValues = new HashMap<Property, String>();

		// Initialize with default values from PropertiesModel

		List<Property> properties = Arrays.asList(Property.class.getEnumConstants());

		properties.forEach((e) -> {

			tempValues.put(e, PropertiesModel.getPreference(e));
		});
	}

	public void set(Property key, String value) {
		tempValues.put(key, value);
	}

	public String get(Property key) {
		return tempValues.get(key);
	}

	public void apply() {
		tempValues.forEach((k, v) -> {
			PropertiesModel.setPreference(k, v);
		});
	}

}
