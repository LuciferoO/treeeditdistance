package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.SimpleTreeParser;
import it.unibz.apeer.thesis.TestfileReader;
import it.unibz.apeer.thesis.TreeNode;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

public class Main {
	
	public static void main(String args[]) throws IOException, ParseException {
		if (args.length < 2) {
			System.out.println("USAGE: <testfile1> <testfile2>");
			return;
		}
		TestfileReader reader = new TestfileReader(args[0]);
		String code;
		Numberer<String> numberer = new Numberer<String>();
		List<TreeNode<NumberedObjectContainer<String>>> trees1 = new Vector<TreeNode<NumberedObjectContainer<String>>>();
		while ((code = reader.nextTreeCode()) != null) {
			trees1.add(numberer.doPostorderNumbering(SimpleTreeParser.parse(code)));
		}
		System.out.println(trees1.size() + " trees parsed");
		reader = new TestfileReader(args[1]);
		List<TreeNode<NumberedObjectContainer<String>>> trees2 = new Vector<TreeNode<NumberedObjectContainer<String>>>();
		while ((code = reader.nextTreeCode()) != null) {
			trees2.add(numberer.doPostorderNumbering(SimpleTreeParser.parse(code)));
		}
		System.out.println(trees2.size() + " trees parsed");
		approxJoin(trees1, trees2, 5);
	}
	
	public static void approxJoin(List<TreeNode<NumberedObjectContainer<String>>> trees1, List<TreeNode<NumberedObjectContainer<String>>> trees2, int treshold) {
		TreeEditDistance<String> ted = new TreeEditDistance<String>();
		for (int i = 0; i <  trees1.size(); i++) {
			TreeNode<NumberedObjectContainer<String>> t1 = trees1.get(i);
			for (int j = 0; j < trees2.size(); j++) {
				TreeNode<NumberedObjectContainer<String>> t2 = trees2.get(j);
				int lb = LowerBoundFilter.calculate(t1, t2);
				System.out.print(i + ", " + j + ": " + lb);
				if (lb <= treshold) {
					System.out.print(" " + ted.treeEditDistance(t1, t2));
				}
				System.out.println();
			}
		}
	}
}
