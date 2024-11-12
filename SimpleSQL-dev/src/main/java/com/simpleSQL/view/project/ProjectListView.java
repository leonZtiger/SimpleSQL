package com.simpleSQL.view.project;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.text.TextAction;
import com.simpleSQL.view.PopupMenu;

/**
 * A panel displaying a tree view of the project components with options to expand/collapse
 * all nodes and refresh.
 */
public class ProjectListView extends JPanel {

    private final JScrollPane content;
    private JTree tree;

    /**
     * Constructs the ProjectListView, initializing a scrollable JTree with
     * expansion listeners and a popup menu for quick actions.
     */
    public ProjectListView() {
        super();

        tree = new JTree();
        content = new JScrollPane(tree);

        // Configure scroll pane
        content.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        content.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setPreferredSize(new Dimension(200, 200));
        setLayout(new GridLayout(0, 1));
        add(content);

        // Add expansion listener to dynamically adjust tree size
        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                tree.setPreferredSize(calculateTreeSize());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                tree.setPreferredSize(calculateTreeSize());
            }

            /**
             * Calculates and returns the preferred size of the tree based on font size
             * and row count, for dynamic resizing during expansion/collapse.
             *
             * @return the Dimension representing the preferred size of the tree
             */
            private Dimension calculateTreeSize() {
                return new Dimension(tree.getWidth(), (tree.getFont().getSize() + 10) * tree.getRowCount());
            }
        });

        // Set custom popup menu with options
        tree.setComponentPopupMenu(new PopupMenu(getPopupOptions()));
    }

    /**
     * Generates a list of popup menu actions, including options to open/close all tree nodes and refresh the view.
     *
     * @return an ArrayList of TextAction representing menu options
     */
    private ArrayList<TextAction> getPopupOptions() {
        ArrayList<TextAction> actions = new ArrayList<>();

        actions.add(PopupMenu.createTextAction("Open all", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tree.getRowCount(); i++) {
                    tree.expandRow(i);
                }
            }
        }));

        actions.add(PopupMenu.createTextAction("Close all", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tree.getRowCount(); i++) {
                    tree.collapseRow(i);
                }
            }
        }));

        actions.add(PopupMenu.createTextAction("Refresh", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tree.getRowCount(); i++) {
                    tree.collapseRow(i);
                }
            }
        }));
        
        return actions;
    }

    /**
     * Sets the JTree displayed in the panel.
     *
     * @param tree the JTree to display within the ProjectListView
     */
    public void setTree(JTree tree) {
        this.tree = tree;
    }
}
