package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.TreeNode;

public class TreeOrder {
	
	public static String preOrder(TreeNode<String> root) {
		String result = root.getUserObject();
		for (TreeNode<String> c : root.getChildren()) {
			result += preOrder(c);
		}
		return result;
	}
	
	public static String postOrder(TreeNode<String> root) {
		String result = "";
		for (TreeNode<String> c : root.getChildren()) {
			result += postOrder(c);
		}
		return result + root.getUserObject();
	}
}
