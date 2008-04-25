package it.unibz.ata.project6.ted;

import javax.swing.plaf.TreeUI;

import it.unibz.apeer.thesis.TreeNode;
import it.unibz.apeer.thesis.TreeUtil;


public class Tester {

	public static void main(String[] args) {
		
		if (args.length < 3) {
			System.out.println("Usage: <fanout> <height> <number of labels>");
			return;
		}
		
		int fanout = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int nLabels = Integer.parseInt(args[2]);
		
		TreeNode<String> tree1;
		TreeNode<String> tree2;
		
		Numberer<String> numberer = new Numberer<String>();
		TreeEditDistance<String> ted = new TreeEditDistance<String>();		
		TreeGenerator<String> generator = new TreeGenerator<String>();
		
		String[] labels = LabelGenerator.generate(nLabels);
		tree1 = generator.generate(fanout, height, labels);
		tree2 = generator.generate(fanout, height, labels);
		
		System.out.println(TreeUtil.calculateSize(tree1));
		System.out.println(TreeUtil.calculateSize(tree2));
		
		long startTime = System.currentTimeMillis();
		String preOrderTree1 = TreeOrder.preOrder(tree1);
		String preOrderTree2 = TreeOrder.preOrder(tree2);
		int preOrderEd = EditDistance.ed(preOrderTree1,preOrderTree2);
		System.out.println("Edit distance preorder: " + preOrderEd);
		
		String postOrderTree1 = TreeOrder.postOrder(tree1);
		String postOrderTree2 = TreeOrder.postOrder(tree2);
		int postOrderEd = EditDistance.ed(postOrderTree1,postOrderTree2);
		System.out.println("Edit distance postorder: " + postOrderEd);
		
		int lowerBound = Math.max(preOrderEd, postOrderEd);
		System.out.println("Lower bound: " + lowerBound);
		long endTime = System.currentTimeMillis();
		System.out.println("Time to compute the filter: " + (endTime - startTime) / 1000f);
		
		startTime = System.currentTimeMillis();
		int d = ted.treeEditDistance(numberer.doPostorderNumbering(tree1), numberer.doPostorderNumbering(tree2));
		System.out.println("Tree Edit Distance: " + d);
		endTime = System.currentTimeMillis();
		System.out.println("Time to compute the tree edit distance: " + (endTime - startTime) / 1000f);
	}
}
