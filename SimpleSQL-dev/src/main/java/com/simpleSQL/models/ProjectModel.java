package com.simpleSQL.models;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import com.simpleSQL.database.DatabaseConnection;

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

	public static ProjectModel openFromFileExplorer() {
		// open file explorer at home directory
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 
		if(jfc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) 
			return null;
		
		return new ProjectModel(jfc.getSelectedFile().toString());
	}

	public static ProjectModel createByUser() {
		String path = JOptionPane.showInputDialog("Enter name for new project");
		
		if(path == null)
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

}
