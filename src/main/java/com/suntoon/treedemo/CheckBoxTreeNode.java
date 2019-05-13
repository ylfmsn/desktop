package com.suntoon.treedemo;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @ProjectionName desktop
 * @ClassName CheckBoxTreeNode
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/8 0008下午 4:43
 * @Version 1.0
 */
public class CheckBoxTreeNode extends DefaultMutableTreeNode {

    protected boolean isSelected;
    private Object attr;

    public Object getAttr() {
        return this.attr;
    }

    public CheckBoxTreeNode() {
        this(null);
    }

    public CheckBoxTreeNode(Object userObject) {
        this(userObject, true, false);
        attr = userObject;
    }

    public CheckBoxTreeNode(Object userObject, boolean allowsChildren, boolean isSelected) {
        super(userObject, allowsChildren);
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean _isSelected) {
        this.isSelected = _isSelected;

        if (_isSelected) {
            //如果选中，则将其所有的子节点都选中
            if (children != null) {
                for (Object obj : children) {
                    CheckBoxTreeNode node = (CheckBoxTreeNode) obj;
                    if (_isSelected != node.isSelected) {
                        node.setSelected(_isSelected);
                    }
                }
            }

            //向上检查，如果父节点的所有子节点都被选中，那么将父节点也选中
            CheckBoxTreeNode pNode = (CheckBoxTreeNode) parent;
            //开始检查pNode的所有子节点是否被选中
            if (pNode != null) {
                int index = 0;
                for (; index < pNode.children.size(); ++index) {
                    CheckBoxTreeNode pChildNode = (CheckBoxTreeNode) pNode.children.get(index);
                    if (!pChildNode.isSelected) {
                        break;
                    }
                }

                /**
                 * 表明pNode所有子节点都已经选中，则选中父节点，
                 * 该方法是一个递归方法，一次再次不需要进行迭代，因为
                 * 当选中父节点后，父节点本身会向上检查
                 */
                if (index == pNode.children.size()) {
                    if (pNode.isSelected() != _isSelected) {
                        pNode.setSelected(_isSelected);
                    }
                }
            }
        } else {

            /**
             * 如果是取消父节点导致子节点取消，那么此时所有的子节点都应该是选中的；
             * 否则就是子节点取消导致父节点取消，然后父节点取消导致需要取消子节点，但
             * 是这时候是不需要取消子节点的
             */
            if (children != null) {

                int index = 0;
                for (; index < children.size(); ++index) {
                    CheckBoxTreeNode childNode = (CheckBoxTreeNode) children.get(index);
                    if (!childNode.isSelected) {
                        break;
                    }
                }

                //从上向下取消的时候
                if (index == children.size()) {
                    for (int i = 0; i < children.size(); ++i) {
                        CheckBoxTreeNode node = (CheckBoxTreeNode) children.get(i);
                        if (node.isSelected() != _isSelected) {
                            node.setSelected(_isSelected);
                        }
                    }
                }
            }

            //向上取消，只要存在一个子节点不是选上的，那么父节点就不应该不选中
            CheckBoxTreeNode pNode = (CheckBoxTreeNode) parent;
            if (pNode != null && pNode.isSelected() != _isSelected) {
                pNode.setSelected(_isSelected);
            }
        }
    }
}
