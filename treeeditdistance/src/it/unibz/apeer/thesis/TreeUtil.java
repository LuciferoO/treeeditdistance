package it.unibz.apeer.thesis;

import it.unibz.apeer.thesis.TreeNode;

public class TreeUtil {
	
	public static void printTree(TreeNode<?> node) {
		printTree(node, "");
	}
	
	private static void printTree(TreeNode<?> node, String prefix) {
		System.out.println(prefix + node.getUserObject());
		if (!node.isLeaf()) {
			for (TreeNode<?> child : node.getChildren()) {
				printTree(child, prefix + " ");
			}
		}
	}
	
	public static int calculateSize(TreeNode<?> node) {
		int s = 1;
		for (TreeNode<?> c : node.getChildren()) {
			s += calculateSize(c);
		}
		return s;
	}
	
	public static int calculateNumberOfLeaves(TreeNode<?> node) {
		if (node.isLeaf()) {
			return 1;
		} else {
			int s = 0;
			for (TreeNode<?> c : node.getChildren()) {
				s += calculateNumberOfLeaves(c);
			}
			return s;
		}
	}
}
