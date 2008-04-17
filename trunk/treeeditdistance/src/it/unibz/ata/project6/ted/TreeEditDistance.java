package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.TreeNode;

public class TreeEditDistance<T> {
	
	public int treeEditDistance(TreeNode<NumberedObjectContainer<T>> T1, TreeNode<NumberedObjectContainer<T>> T2) {
		return 0;
	}

	private TreeNode<NumberedObjectContainer<T>>[] lmld(TreeNode<NumberedObjectContainer<T>> v, TreeNode<NumberedObjectContainer<T>>[] l) {
		for (TreeNode<NumberedObjectContainer<T>> c : v.getChildren()) {
			l = lmld(c, l);
		}
		if (v.isLeaf()) {
			
		}
		return null;
	}
	
	private TreeNode<NumberedObjectContainer<T>>[] kr(TreeNode<NumberedObjectContainer<T>>[] l, int lc) {
		return null;
	}
	
	private void forestDist(int i, int j, TreeNode<NumberedObjectContainer<T>> l1, TreeNode<NumberedObjectContainer<T>> l2, int[][] td) {
		
	}
}
