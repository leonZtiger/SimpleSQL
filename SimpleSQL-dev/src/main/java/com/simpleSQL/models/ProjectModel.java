package com.simpleSQL.models;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.TextAction;

import com.simpleSQL.database.DatabaseConnection;
import com.simpleSQL.eerModel.Attribute;
import com.simpleSQL.eerModel.ComponentBase;
import com.simpleSQL.eerModel.Connection;
import com.simpleSQL.eerModel.Entity;
import com.simpleSQL.eerModel.Relation;
import com.simpleSQL.views.PopupMenu;

public class ProjectModel {

	private String name;
	private ArrayList<ComponentBase> selectObjectsList;
	private ArrayList<ComponentBase> copyBoard;

	private boolean multiSelect;
	private boolean deselect;

	public ProjectModel(String name) {
		this.multiSelect = false;
		this.deselect = false;
		this.name = name;
		this.selectObjectsList = new ArrayList<ComponentBase>();
		this.copyBoard = new ArrayList<ComponentBase>();
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

	public static MouseListener getComponentMouseListener(ProjectModel model, ComponentBase obj) {
		return new MouseListener() {

			protected boolean clicked = false;
			private long clickTimeStamp;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (clicked && System.currentTimeMillis() - clickTimeStamp < 200) {

					String newName = JOptionPane.showInputDialog("Enter new name");

					if (newName == null || newName.isEmpty())
						return;

					obj.setText(newName);
					clicked = false;
				} else {
					clicked = true;
					clickTimeStamp = System.currentTimeMillis();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Left mouse button pressed
				if (e.getButton() == MouseEvent.BUTTON1) {
					obj.getParent().setComponentZOrder(obj, 0);

					if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
						model.setMultiSelectEnabled();
					} else {
						model.setMultiSelectDisabled();
					}
					if ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0) {
						model.setDeselectEnabled();
					} else {
						model.setDeselectDisabled();
					}

					model.setSelected(obj);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};
	}

	public static ComponentBase createNewComponent(Class<?> c, ProjectModel model) {

		String text = JOptionPane.showInputDialog(null, "Set name of " + c.getName());

		if (text == null || text.isEmpty())
			return null;

		try {

			ComponentBase obj = (ComponentBase) c.getConstructor(String.class).newInstance(text);

			ArrayList<TextAction> actions = new ArrayList<TextAction>();

			actions.add(new TextAction("Rename") {

				@Override
				public void actionPerformed(ActionEvent e) {
					String newName = JOptionPane.showInputDialog("Enter new name");

					if (newName == null || newName.isEmpty())
						return;

					obj.setText(newName);
				}
			});
			actions.add(new TextAction("Copy") {

				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});
			actions.add(new TextAction("Delete") {

				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});

			obj.setComponentPopupMenu(new PopupMenu(actions));

			obj.addMouseListener(getComponentMouseListener(model, obj));

			return obj;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSelected(ComponentBase comp) {

		// No special case, remove selection on re-selection
		if (selectObjectsList.contains(comp)) {
			comp.setNotSelect();
			selectObjectsList.remove(comp);

			if (!multiSelect && !deselect) {
				clearSelections();
			}

		} else if (!multiSelect && !deselect) {
			clearSelections();
			comp.setSelect();
			selectObjectsList.add(comp);
		} else if (multiSelect) {
			comp.setSelect();
			selectObjectsList.add(comp);
		}
	}

	public void clearSelections() {
		selectObjectsList.forEach(e -> {
			e.setNotSelect();
		});
		selectObjectsList.clear();

	}

	public void setMultiSelectEnabled() {
		multiSelect = true;
	}

	public void setDeselectEnabled() {
		deselect = true;
	}

	public void setMultiSelectDisabled() {
		multiSelect = false;
	}

	public void setDeselectDisabled() {
		deselect = false;
	}

	public void deleteSelected() {
		selectObjectsList.clear();
	}

	public ArrayList<ComponentBase> getSelected() {

		return selectObjectsList;
	}

	public void copy(ArrayList<ComponentBase> selected) {
		copyBoard.clear();
		copyBoard.addAll(selected);
		copyBoard = paste();
	}

	public ArrayList<ComponentBase> paste() {
		ArrayList<ComponentBase> copies = new ArrayList<ComponentBase>();
		
		copyBoard.forEach(e -> {
			e.setLocation(e.getLocation().x+20, e.getLocation().y+20);
			copies.add(e.copy());
		});
		
		return copies;
	}

}
