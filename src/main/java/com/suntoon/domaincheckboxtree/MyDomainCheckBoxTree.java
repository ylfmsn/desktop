package com.suntoon.domaincheckboxtree;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * @ProjectionName desktop
 * @ClassName MyDomainCheckBoxTree
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/9 0009上午 10:18
 * @Version 1.0
 */
public class MyDomainCheckBoxTree extends JFrame {

    HashSet<TreePath> includedPaths = new HashSet<>();
    HashSet<TreePath> excludedPaths = new HashSet<>();
    TreeModel treeModel;

    public MyDomainCheckBoxTree(boolean testDefault) {
        super();
        setSize(500, 500);
        this.getContentPane().setLayout(new BorderLayout());

        final JCheckBoxTree cbt;
        if( testDefault ) {
            treeModel = null;
            cbt = new JCheckBoxTree();
        }
        else {
            treeModel = buildModel();
            LazyCheckBoxCellRenderer treeCellRenderer = new LazyCheckBoxCellRenderer();
            cbt = new JCheckBoxTree(treeModel, null, treeCellRenderer);
            treeCellRenderer.setCheckBoxTree(cbt);
            cbt.addTreeExpansionListener(new NodeExpansionListener());
        }

        JScrollPane s = new JScrollPane();
        s.getViewport().add(cbt);
        getContentPane().add(s, BorderLayout.CENTER);

        //this.getContentPane().add(cbt);

        cbt.addCheckChangeEventListener(new JCheckBoxTree.CheckChangeEventListener() {
            public void checkStateChanged(JCheckBoxTree.CheckChangeEvent event) {
                updatePaths(cbt, event);
                // For Debugging (correctness and laziness)
                System.out.println("\n========== Current State ========");
                System.out.println("+ + + Included Paths: ");
                printPaths(includedPaths);
                System.out.println("- - - Excluded Paths: ");
                printPaths(excludedPaths);
                System.out.println("Size of node-checkState cache = " + cbt.nodesCheckingState.size());
                //
            }
        });
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // (As prelude)Purges any of clickedPath's children from the 2 path-sets.
    // Then adds/removes clickedPath from the 2 path-sets if appropriate.
    protected void updatePaths(JCheckBoxTree cbt,
                               JCheckBoxTree.CheckChangeEvent event){
        boolean  parentAlreadyIncluded = false;
        boolean  parentAlreadyExcluded = false;
        TreePath clickedPath = (TreePath) event.getSource();
        HashSet<TreePath>  toBeRemoved = new HashSet<>();

        //When a node is included/excluded, its children are implied as included/excluded.
        // Note: The direct-parent check is needed to avoid problem if immediate-parent is excluded
        // but grand-father/higher-ancestor is included
        for( TreePath exp : excludedPaths){
            if( clickedPath.isDescendant(exp) ) // exp is descended from clickedPath
                toBeRemoved.add(exp);
            if( isParent(exp, clickedPath)) // clickedPath is child of exp
                parentAlreadyExcluded = true;
        }
        excludedPaths.removeAll(toBeRemoved);
        toBeRemoved.clear();

        for( TreePath inp : includedPaths) {
            if(clickedPath.isDescendant(inp)) // inp is descended from clickedPath
                toBeRemoved.add(inp);
            if( isParent(inp, clickedPath)) // clickedPath is child of inp
                parentAlreadyIncluded = true;
        }
        includedPaths.removeAll(toBeRemoved);
        toBeRemoved.clear();

        // Now add/remove clickedPath from the path-sets as appropriate
        if( cbt.getCheckMode(clickedPath) ){ //selected => to be included
            if(!parentAlreadyIncluded)
                includedPaths.add(clickedPath);
            excludedPaths.remove(clickedPath);
        }else {    //deselected => to be excluded
            if( !parentAlreadyExcluded )
                excludedPaths.add(clickedPath);
            includedPaths.remove(clickedPath);
        }
    }

    // returns true if aPath is immediate parent of bPath; both must be non-null
    protected boolean isParent(TreePath aPath, TreePath bPath){
        return aPath.equals(bPath.getParentPath());
    }

    protected void printPaths(HashSet<TreePath> pathSet){
        TreePath[] paths = pathSet.toArray(new TreePath[pathSet.size()]);
        for (TreePath tp : paths) {
            for (Object pathPart : tp.getPath()) {
                System.out.print(pathPart + ",");
            }
            System.out.println();
        }
    }

    private class LazyCheckBoxCellRenderer extends JPanel implements TreeCellRenderer {
        JCheckBoxTree cbt;
        JCheckBox checkBox;

        public LazyCheckBoxCellRenderer() {
            super();
            this.setLayout(new BorderLayout());
            checkBox = new JCheckBox();
            add(checkBox, BorderLayout.CENTER);
            setOpaque(false);
        }

        public void setCheckBoxTree(JCheckBoxTree someCbt) { cbt = someCbt;}

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean selected, boolean expanded, boolean leaf, int row,
                                                      boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object obj = node.getUserObject();

            checkBox.setText(obj.toString());

            if (obj instanceof Boolean)
                checkBox.setText("Retrieving data...");
            else
            {
                TreePath tp = new TreePath(node.getPath());
                JCheckBoxTree.CheckedNode cn = null;
                if( cbt != null )
                    cn = cbt.getCheckedNode(tp);
                if (cn == null) {
                    return this;
                }
                checkBox.setSelected(cn.isSelected);
                checkBox.setText(obj.toString());
                checkBox.setOpaque(cn.isSelected && cn.hasChildren && ! cn.allChildrenSelected);
            }
            return this;
        }
    }

    public static void main(String args[]) {

        /*try{
            //设置本属性将改变窗口边框样式定义
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        boolean test = false;
        if( args.length > 0 && args[0].equalsIgnoreCase("test") )
            test = true;
        MyDomainCheckBoxTree m = new MyDomainCheckBoxTree(test);
        m.setVisible(true);
    }

    // Make sure expansion is threaded and updating the tree model
    // only occurs within the event dispatching thread.
    class NodeExpansionListener implements TreeExpansionListener
    {
        public void treeExpanded(TreeExpansionEvent event) {
            final DefaultMutableTreeNode node = JCheckBoxTree.getTreeNode(event.getPath());
            Object obj = node.getUserObject();

            //Expand by adding any children nodes
            Thread runner = new Thread() {
                public void run() {
                    if (obj != null && ((MyDomainObject)obj).expand(node)) {
                        Runnable runnable = new Runnable() {
                            public void run() {
                                ((DefaultTreeModel)treeModel).reload(node);
                            }
                        };
                        SwingUtilities.invokeLater(runnable);
                    }
                }
            };
            runner.start();
        }

        public void treeCollapsed(TreeExpansionEvent event) {}
    }

    //====================== Your Domain specific stuff goes here

    protected TreeModel buildModel() {
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode node;
        String[] categories = {"Product","Place","Critter"};
        for (String cat : categories) {
            MyDomainObject d = new MyDomainObject(cat);
            d.hasChildren = true;
            node = new DefaultMutableTreeNode(d);

            topNode.add(node);
            node.add( new DefaultMutableTreeNode(true));
        }

        return new DefaultTreeModel(topNode);
    }

    //sample impl of a domain-object; should have expand method
    class MyDomainObject {
        protected Object data;
        protected boolean  hasChildren;

        public MyDomainObject(Object obj) {
            data = obj;
            hasChildren = new Random().nextBoolean();
        }

        // Expand the tree at parent node and add nodes.
        public boolean expand(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode flagNode = (DefaultMutableTreeNode) parent.getFirstChild();
            if (flagNode == null)    // No flag
                return false;
            Object obj = flagNode.getUserObject();
            if (!(obj instanceof Boolean))
                return false;      // Already expanded

            parent.removeAllChildren();  // Remove FlagNode

            Object[] children = getChildren();
            if (children == null)
                return true;

            // Create a sorted list of domain-objects
            ArrayList sortedChildDomainObjects = new ArrayList();

            for (Object child : children) {
                MyDomainObject newNode = new MyDomainObject(child);
                //System.out.println("Size of arraylist=" + sortedChildDomainObjects.size());
                boolean isAdded = false;
                for (int i = 0; i < sortedChildDomainObjects.size(); i++) {
                    MyDomainObject nd = (MyDomainObject) sortedChildDomainObjects.get(i);
                    if (newNode.compareTo(nd) < 0) {
                        sortedChildDomainObjects.add(i, newNode);
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded)
                    sortedChildDomainObjects.add(newNode);
            }

            // Add children nodes under parent in the tree
            for (Object aChild : sortedChildDomainObjects) {
                MyDomainObject nd = (MyDomainObject) aChild;
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(nd);
                parent.add(node);

                if (nd.hasChildren)
                    node.add(new DefaultMutableTreeNode(true));
            }

            return true;
        }

        private int compareTo(MyDomainObject toCompare) {
            assert toCompare.data != null;
            return data.toString().compareToIgnoreCase(toCompare.data.toString());
        }

        //should be Domain specific; dummy impl provided
        private Object[]  getChildren(){
            if( data == null || (!hasChildren))
                return null;

            Random rand = new Random();

            Object[] children = new Object[rand.nextInt(20)];
            for( int i=0; i < children.length; i++){
                children[i] = data.toString() + "-" + rand.nextInt(1024); ;
            }
            return children;
        }

        public String toString() {
            return data != null ? data.toString() : "(EMPTY)";
        }
    }


}
