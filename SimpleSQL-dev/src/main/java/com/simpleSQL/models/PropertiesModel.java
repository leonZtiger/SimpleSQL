package com.simpleSQL.models;

import java.util.prefs.Preferences;

public class PropertiesModel {

	private static Preferences prefs = Preferences.userNodeForPackage(PropertiesModel.class);

	public static void setPreference(Property key, String value) {
		prefs.put(key.name(), value);
	}

	public static String getPreference(Property e) {
		return prefs.get(e.name(), "");
	}
}
