package com.simpleSQL.models;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import com.simpleSQL.database.DatabaseConnection;
import com.simpleSQL.eerModel.Attribute;
import com.simpleSQL.eerModel.ComponentBase;
import com.simpleSQL.eerModel.Entity;
import com.simpleSQL.eerModel.Relation;

public class ProjectModel {

	private String name;

	public ProjectModel(String name) {
		this.name = name;
	}

	public void format() {
		// TODO Auto-generated method stub

	}

	public void redo() {
		// TODO Auto-generated method stub

	}

	public void undo() {
		// TODO Auto-generated method stub

	}

	public void setNewConnection(DatabaseConnection newCon) {
		// TODO Auto-generated method stub

	}

	public void build() {
		// TODO Auto-generated method stub

	}

	public boolean isSaved() {
		return false;
	}

	public static ProjectModel openFromFileExplorer() {
		// open file explorer at home directory
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (jfc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return null;

		return new ProjectModel(jfc.getSelectedFile().toString());
	}

	public static ProjectModel createByUser() {
		String path = JOptionPane.showInputDialog("Enter name for new project");

		if (path == null)
			return null;

		return new ProjectModel(path);
	}

	public void save() {
		// TODO Auto-generated method stub

	}

	public void saveAs() {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	public ArrayList<ComponentBase> getData() {

		return null;
	}

	public void add(Entity entity) {

	}

	public void add(Relation relation) {

	}

	public void add(Attribute attribute) {
		// TODO Auto-generated method stub

	}

	public static ComponentBase createNewComponent(Class<?> c, Point placement) {

		String text = JOptionPane.showInputDialog(null, "Set name of " + c.getName());

		if (text == null || text.isEmpty())
			return null;

		try {
			return (ComponentBase) c.getConstructor(Point.class, String.class).newInstance(placement, text);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
