package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.TreeNode;

import java.util.List;
import java.util.Vector;

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
	
	public static <T> List<T> postOrderValues(TreeNode<T> root) {
		List<T> values = new Vector<T>();
		postOrderValues(root, values);
		return values;
	}
	
	private static <T> void postOrderValues(TreeNode<T> node, List<T> values) {
		for (TreeNode<T> c : node.getChildren()) {
			postOrderValues(c, values);
		}
		values.add(node.getUserObject());
	}
}
