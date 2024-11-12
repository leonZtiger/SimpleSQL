package com.simpleSQL.model;

import java.awt.Point;
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
import com.simpleSQL.eerModelComponent.Attribute;
import com.simpleSQL.eerModelComponent.ComponentBase;
import com.simpleSQL.eerModelComponent.Entity;
import com.simpleSQL.eerModelComponent.Relation;
import com.simpleSQL.view.PopupMenu;

/**
 * Model class representing a project, which contains multiple components such
 * as entities, relations, and attributes. Supports actions such as saving,
 * copying, pasting, and managing selections.
 */
public class ProjectModel {

	private String name;
	private ArrayList<ComponentBase> selectObjectsList;
	private ArrayList<ComponentBase> copyBoard;

	private boolean multiSelect;
	private boolean deselect;

	/**
	 * Constructs a ProjectModel with the specified project name.
	 *
	 * @param name the name of the project
	 */
	public ProjectModel(String name) {
		this.multiSelect = false;
		this.deselect = false;
		this.name = name;
		this.selectObjectsList = new ArrayList<>();
		this.copyBoard = new ArrayList<>();
	}

	/**
	 * Formats the project data (stub for further implementation).
	 */
	public void format() {
		// TODO: Implement formatting functionality
	}

	/**
	 * Redoes the last undone action (stub for further implementation).
	 */
	public void redo() {
		// TODO: Implement redo functionality
	}

	/**
	 * Undoes the last action (stub for further implementation).
	 */
	public void undo() {
		// TODO: Implement undo functionality
	}

	/**
	 * Sets a new database connection for the project (stub for further
	 * implementation).
	 *
	 * @param newCon the new database connection
	 */
	public void setNewConnection(DatabaseConnection newCon) {
		// TODO: Implement database connection functionality
	}

	/**
	 * Builds the project (stub for further implementation).
	 */
	public void build() {
		// TODO: Implement build functionality
	}

	/**
	 * Checks if the project is saved.
	 *
	 * @return False if project is not saved. True if its saved.
	 */
	public boolean isSaved() {
		return false;
	}

	/**
	 * Opens a project by selecting a file from a file explorer.
	 *
	 * @return a new ProjectModel with the selected file path as its name, or null
	 *         if no file was selected
	 */
	public static ProjectModel openFromFileExplorer() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (jfc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return null;

		return new ProjectModel(jfc.getSelectedFile().toString());
	}

	/**
	 * Creates a new project with a name specified by the user.
	 *
	 * @return a new ProjectModel with the specified name, or null if the input was
	 *         canceled
	 */
	public static ProjectModel createByUser() {
		String path = JOptionPane.showInputDialog("Enter name for new project");
		return (path != null) ? new ProjectModel(path) : null;
	}

	/**
	 * Saves the project (stub for further implementation).
	 */
	public void save() {
		// TODO: Implement save functionality
	}

	/**
	 * Saves the project under a different name (stub for further implementation).
	 */
	public void saveAs() {
		// TODO: Implement save as functionality
	}

	/**
	 * Gets the name of the project.
	 *
	 * @return the name of the project
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the data of the project components.
	 *
	 * @return a list of components, or null by default
	 */
	public ArrayList<ComponentBase> getData() {
		return null;
	}

	public void add(Entity entity) {
		// TODO: Implement add entity functionality
	}

	public void add(Relation relation) {
		// TODO: Implement add relation functionality
	}

	public void add(Attribute attribute) {
		// TODO: Implement add attribute functionality
	}

	/**
	 * Returns a MouseListener for a given component in the model, handling
	 * selection and name editing on double-click.
	 *
	 * @param model the project model containing the component
	 * @param obj   the component for which the listener is created
	 * @return a configured MouseListener
	 */
	public static MouseListener getComponentMouseListener(ProjectModel model, ComponentBase obj) {
		return new MouseListener() {

			private boolean clicked = false;
			private long clickTimeStamp;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (clicked && System.currentTimeMillis() - clickTimeStamp < 200) {
					String newName = JOptionPane.showInputDialog("Enter new name");
					if (newName != null && !newName.isEmpty())
						obj.setText(newName);
					clicked = false;
				} else {
					clicked = true;
					clickTimeStamp = System.currentTimeMillis();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}
		};
	}

	/**
	 * Creates a new component of the specified type with a name, and sets up
	 * actions for renaming, copying, and deleting the component.
	 *
	 * @param c     the class of the component to create
	 * @param model the project model to which the component will belong
	 * @return the created component, or null if an error occurred
	 */
	public static ComponentBase createNewComponent(Class<?> c, ProjectModel model) {
		String text = JOptionPane.showInputDialog(null, "Set name of " + c.getSimpleName());
		if (text == null || text.isEmpty())
			return null;

		try {
			ComponentBase obj = (ComponentBase) c.getConstructor(String.class).newInstance(text);
			ArrayList<TextAction> actions = new ArrayList<>();

			actions.add(new TextAction("Rename") {
				@Override
				public void actionPerformed(ActionEvent e) {
					String newName = JOptionPane.showInputDialog("Enter new name");
					if (newName != null && !newName.isEmpty())
						obj.setText(newName);
				}
			});

			actions.add(new TextAction("Copy") {
				@Override
				public void actionPerformed(ActionEvent e) {
					model.copy(model.getSelected());
				}
			});

			actions.add(new TextAction("Delete") {
				@Override
				public void actionPerformed(ActionEvent e) {
					model.deleteSelected();
				}
			});

			obj.setComponentPopupMenu(new PopupMenu(actions));
			obj.addMouseListener(getComponentMouseListener(model, obj));
			return obj;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSelected(ComponentBase comp) {
		if (selectObjectsList.contains(comp)) {
			comp.setNotSelected();
			selectObjectsList.remove(comp);
			if (!multiSelect && !deselect)
				clearSelections();
		} else if (!multiSelect && !deselect) {
			clearSelections();
			comp.setSelected();
			selectObjectsList.add(comp);
		} else if (multiSelect) {
			comp.setSelected();
			selectObjectsList.add(comp);
		}
	}

	public void clearSelections() {
		selectObjectsList.forEach(ComponentBase::setNotSelected);
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
		// Storing only a copy of the exact copied element, not a reference to the
		// copied selection.
		// When pasting later, the copyboard will still be able to past even if the
		// copied element is deleted.
		copyBoard = paste();
	}

	public ArrayList<ComponentBase> paste() {
		ArrayList<ComponentBase> copies = new ArrayList<ComponentBase>();

		copyBoard.forEach(e -> {
			ComponentBase new_e = e.copy();
			new_e.setLocation(e.getLocation().x + 20, e.getLocation().y + 20);
			copies.add(new_e);
		});

		return copies;
	}
}
