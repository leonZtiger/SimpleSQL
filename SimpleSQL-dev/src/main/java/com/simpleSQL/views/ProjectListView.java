package com.simpleSQL.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.text.TextAction;
import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectListView extends JPanel {

	private JScrollPane content;
	private JTree tree;

	public ProjectListView() {
		super();

		tree = testNode();

		content = new JScrollPane(tree);
		content.setViewportView(tree);

		tree.addTreeExpansionListener(new TreeExpansionListener() {

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				tree.setPreferredSize(new Dimension(200, calcSize()));
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				tree.setPreferredSize(new Dimension(200, calcSize()));
			}

			private int calcSize() {
				// ( Font + padding) * rows
				return (tree.getFont().getSize() + 10) * tree.getRowCount();
			}
		});

		content.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		content.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		setLayout(new GridLayout(0, 1));

		add(content);

		tree.setComponentPopupMenu(new PopupMenu(getPopupOptions()));
	}

	private ArrayList<TextAction> getPopupOptions() {
		ArrayList<TextAction> actions = new ArrayList<TextAction>();

		actions.add(PopupMenu.createTextAction("Open all", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < tree.getRowCount(); i++)
					tree.expandRow(i);
			}
		}));
		actions.add(PopupMenu.createTextAction("Close all", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < tree.getRowCount(); i++)
					tree.collapseRow(i);
			}
		}));

		return actions;
	}

	private static JTree testNode() {
		// Creating the root node
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Database");

		// Creating child nodes
		DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("Parent 1");
		DefaultMutableTreeNode child1_1 = new DefaultMutableTreeNode("Child 1.1");
		DefaultMutableTreeNode child1_2 = new DefaultMutableTreeNode("Child 1.2");

		// Adding child nodes to the parent1
		parent1.add(child1_1);
		parent1.add(child1_2);

		// Creating another set of child nodes
		DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("Parent 2");
		DefaultMutableTreeNode child2_1 = new DefaultMutableTreeNode("Child 2.1");
		DefaultMutableTreeNode child2_2 = new DefaultMutableTreeNode("Child 2.2");

		// Adding child nodes to the parent2
		parent2.add(child2_1);
		parent2.add(child2_2);

		for (int i = 0; i < 100; i++) {
			parent1.add(new DefaultMutableTreeNode("test " + i));
		}

		// Adding parent nodes to the root
		root.add(parent1);
		root.add(parent2);

		JTree tree = new JTree(root);
		tree.setPreferredSize(new Dimension(200, 10));
		tree.setFocusable(false);

		return tree;
	}

}
