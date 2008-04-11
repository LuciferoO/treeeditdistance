package it.unibz.apeer.thesis;

import java.util.List;
import java.util.Vector;

public class SimpleTreeNode<T> implements TreeNode<T> {
	
	private T object_;
	private SimpleTreeNode<T> parent_;
	private List<TreeNode<T>> children_;
	
	public SimpleTreeNode() {
		children_ = new Vector<TreeNode<T>>();
	}
	
	public SimpleTreeNode(T object) {
		this();
		object_ = object;
	}

	public void addChild(TreeNode<T> node) {
		children_.add(node);
		if (node instanceof SimpleTreeNode) {
			((SimpleTreeNode<T>)node).parent_ = this;
		}
	}

	public List<TreeNode<T>> getChildren() {
		return children_;
	}

	public TreeNode<T> getParent() {
		return parent_;
	}

	public T getUserObject() {
		return object_;
	}

	public boolean isLeaf() {
		return children_.size() == 0;
	}

	public void setUserObject(T object) {
		object_ = object;
	}

}
