package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.SimpleTreeParser;
import it.unibz.apeer.thesis.TreeNode;
import it.unibz.apeer.thesis.util.TreeUtil;

import java.text.ParseException;

public class Tester {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		TreeNode<String> tree1 = SimpleTreeParser.parse(args[0]);
		TreeNode<String> tree2 = SimpleTreeParser.parse(args[1]);
		
		System.out.println("Tree1");
		TreeUtil.printTree(tree1);
		
		System.out.println("Tree2");
		TreeUtil.printTree(tree2);
		
		String preOrderTree1 = TreeOrder.preOrder(tree1);
		String postOrderTree1 = TreeOrder.postOrder(tree1);
		String preOrderTree2 = TreeOrder.preOrder(tree2);
		String postOrderTree2 = TreeOrder.postOrder(tree2);
		
		System.out.println("PreOrder tree1: " + preOrderTree1);
		System.out.println("PostOrder tree1: " + postOrderTree1);
		System.out.println("PreOrder tree2: " + preOrderTree2);
		System.out.println("PostOrder tree2: " + postOrderTree2);
		
		int preOrderEd = EditDistance.ed(preOrderTree1,preOrderTree2);
		int postOrderEd = EditDistance.ed(postOrderTree1,postOrderTree2);
		
		System.out.println("Edit distance preorder: " + preOrderEd);
		System.out.println("Edit distance postorder: " + postOrderEd);
		
		System.out.println("Max: " + Math.max(preOrderEd, postOrderEd));
	}

}