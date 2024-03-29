package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.TreeNode;

import java.util.List;
import java.util.Vector;

public class TreeOrder {
	
	public static String preOrder(TreeNode<?> root) {
		String result = "";
		result += stringHash(root.getUserObject());
		for (TreeNode<?> c : root.getChildren()) {
			result += preOrder(c);
		}
		return result;
	}
	
	public static String postOrder(TreeNode<?> root) {
		String result = "";
		for (TreeNode<?> c : root.getChildren()) {
			result += postOrder(c);
		}
		return result + stringHash(root.getUserObject());
	}
	
	private static Character stringHash(Object o) {
		return Character.toChars(Math.abs(((o.toString().hashCode()) + 1) % (int)Math.pow(2, 16)))[0];
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
