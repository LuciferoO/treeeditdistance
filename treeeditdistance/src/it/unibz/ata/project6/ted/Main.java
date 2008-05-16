package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.SimpleTreeParser;
import it.unibz.apeer.thesis.TestfileReader;
import it.unibz.apeer.thesis.TreeCodeWithID;
import it.unibz.apeer.thesis.TreeNode;
import it.unibz.apeer.thesis.TreeUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

public class Main {
	
	public static void main(String args[]) throws IOException, ParseException {
		if (args.length < 3) {
			System.out.println("USAGE: <testfile1> <testfile2> <treshold for lowerbound in %>");
			return;
		}
		TestfileReader reader = new TestfileReader(args[0]);
		Numberer<String> numberer = new Numberer<String>();
		List<TreeNode<NumberedObjectContainer<String>>> trees1 = new Vector<TreeNode<NumberedObjectContainer<String>>>();
		List<Integer> idsTree1 = new Vector<Integer>();
		TreeCodeWithID code;
		while ((code = reader.nextTreeCode()) != null) {
			trees1.add(numberer.doPostorderNumbering(SimpleTreeParser.parse(code.code)));
			idsTree1.add(code.id);
		}
		System.out.println(trees1.size() + " trees parsed");
		reader = new TestfileReader(args[1]);
		List<TreeNode<NumberedObjectContainer<String>>> trees2 = new Vector<TreeNode<NumberedObjectContainer<String>>>();
		List<Integer> idsTree2 = new Vector<Integer>();
		while ((code = reader.nextTreeCode()) != null) {
			trees2.add(numberer.doPostorderNumbering(SimpleTreeParser.parse(code.code)));
			idsTree2.add(code.id);
		}
		System.out.println(trees2.size() + " trees parsed");
		double treshold = Double.parseDouble(args[2]);
		approxJoin(trees1, trees2, idsTree1, idsTree2, treshold);
	}
	
	public static void approxJoin(List<TreeNode<NumberedObjectContainer<String>>> trees1, List<TreeNode<NumberedObjectContainer<String>>> trees2,  List<Integer>idsTree1, List<Integer>idsTree2, double treshold) {
		TreeEditDistance<String> ted = new TreeEditDistance<String>();
		int nMatches = 0;
		int total = 0;
		for (int i = 0; i <  trees1.size(); i++) {
			TreeNode<NumberedObjectContainer<String>> t1 = trees1.get(i);
			int sizeT1 = TreeUtil.calculateSize(t1);
			for (int j = 0; j < trees2.size(); j++) {
				TreeNode<NumberedObjectContainer<String>> t2 = trees2.get(j);
				int lb = LowerBoundFilter.calculate(t1, t2);
				int sizeT2 = TreeUtil.calculateSize(t2);
				double normLb = lb / (double)(sizeT1 + sizeT2);
				//System.out.print(i + ", " + j + ": " + lb + "/(" + sizeT1 + " + " + sizeT2 + ") = " + normLb);
				if (normLb <= treshold) {
					++total;
					System.out.print(idsTree1.get(i) + ", " + idsTree2.get(j) + ": " + lb + "/(" + sizeT1 + " + " + sizeT2 + ") = " + normLb);
					System.out.print(" " + ted.treeEditDistance(t1, t2));
					System.out.println();
					if (idsTree1.get(i).equals(idsTree2.get(j))) {
						++nMatches;
					}
				}
				//System.out.println();
			}
		}
		System.out.println(nMatches + " out of " + total);
	}
}
