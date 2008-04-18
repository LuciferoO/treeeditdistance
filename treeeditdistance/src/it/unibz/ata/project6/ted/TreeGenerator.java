package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.SimpleTreeNode;
import it.unibz.apeer.thesis.TreeNode;

public class TreeGenerator<T> {

	public TreeNode<T> generate(int fanout, int height, T[] labels) {
		if (fanout < 1 || height < 0) {
			return null;
		}
		TreeNode<T> node = new SimpleTreeNode<T>(labels[(int)(Math.random() * labels.length)]);
		if (height > 0) {
			for (int i = 0; i < Math.random() * fanout; i++) {
				node.addChild(generate(fanout, height - 1, labels));
			}
		}
		return node;
	}
}
