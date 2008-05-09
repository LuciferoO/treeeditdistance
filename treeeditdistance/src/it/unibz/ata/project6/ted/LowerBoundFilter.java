package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.TreeNode;

public class LowerBoundFilter {
	
	public static int calculate(TreeNode<NumberedObjectContainer<String>> tree1, TreeNode<NumberedObjectContainer<String>> tree2) {
		String preOrderTree1 = TreeOrder.preOrder(tree1);
		String preOrderTree2 = TreeOrder.preOrder(tree2);
		int preOrderEd = EditDistance.ed(preOrderTree1,preOrderTree2);
		
		String postOrderTree1 = TreeOrder.postOrder(tree1);
		String postOrderTree2 = TreeOrder.postOrder(tree2);
		int postOrderEd = EditDistance.ed(postOrderTree1,postOrderTree2);
		
		return Math.max(preOrderEd, postOrderEd);
	}
}
