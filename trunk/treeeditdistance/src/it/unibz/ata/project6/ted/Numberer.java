package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.TreeNode;

public class Numberer<T> {
	
	private int curId_ = 0;
	
	public TreeNode<NumberedObjectContainer<T>> doPostorderNumbering(TreeNode<T> root) {
		curId_ = 0;
		return doPostorderNumberingTraversal(root);
	}
	
	private TreeNode<NumberedObjectContainer<T>> doPostorderNumberingTraversal(TreeNode<T> node) {
		for (TreeNode<T> c : node.getChildren()) {
				doPostorderNumberingTraversal(c);
		}
		TreeNode<NumberedObjectContainer<T>> nnode = (TreeNode<NumberedObjectContainer<T>>)node;
		nnode.setUserObject(new NumberedObjectContainer<T>(curId_++, node.getUserObject()));
		return nnode;
	}

}
