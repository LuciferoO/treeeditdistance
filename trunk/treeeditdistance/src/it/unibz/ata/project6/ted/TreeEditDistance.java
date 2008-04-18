package it.unibz.ata.project6.ted;

import it.unibz.apeer.thesis.TreeNode;
import it.unibz.apeer.thesis.util.TreeUtil;

import java.util.Arrays;

public class TreeEditDistance<T> {

	private static int W_DEL = 1;
	private static int W_INS = 1;
	private static int W_REN = 1;
	private static int W_NOP = 0;

	public int treeEditDistance(TreeNode<NumberedObjectContainer<T>> T1,
			TreeNode<NumberedObjectContainer<T>> T2) {

		int td[][] = new int[TreeUtil.calculateSize(T1)][TreeUtil
				.calculateSize(T2)];
		int[] l1 = lmld(T1, new int[TreeUtil.calculateSize(T1)]);
		int[] kr1 = kr(l1, TreeUtil.calculateNumberOfLeaves(T1));
		int[] l2 = lmld(T2, new int[TreeUtil.calculateSize(T2)]);
		int[] kr2 = kr(l2, TreeUtil.calculateNumberOfLeaves(T2));
		for (int x = 0; x < kr1.length; x++) {
			for (int y = 0; y < kr2.length; y++) {
				forestDist(kr1[x], kr2[y], l1, l2, td);
			}
		}
		for (int[] a : td) {
			for (int b : a) {
				System.out.print(b + "\t");
			}
			System.out.println();
		}
		return td[td.length - 1][td[td.length - 1].length - 1];
	}

	private int[] lmld(TreeNode<NumberedObjectContainer<T>> v, int[] l) {
		for (TreeNode<NumberedObjectContainer<T>> c : v.getChildren()) {
			l = lmld(c, l);
		}
		if (v.isLeaf()) {
			l[v.getUserObject().getId()] = v.getUserObject().getId();
		} else {
			TreeNode<NumberedObjectContainer<T>> c1 = v.getChildren().get(0);
			l[v.getUserObject().getId()] = l[c1.getUserObject().getId()];
		}
		return l;
	}

	private int[] kr(int[] l, int lc) {
		int kr[] = new int[lc];
		boolean visited[] = new boolean[l.length];
		for (boolean b : visited) {
			b = false;
		}
		int k = kr.length - 1;
		int i = l.length - 1;
		while (k >= 0) {
			if (!visited[l[i]]) {
				kr[k--] = i;
				visited[l[i]] = true;
			}
			i--;
		}
		Arrays.sort(kr);
		return kr;
	}

	private void forestDist(int i, int j, int[] l1, int[] l2, int[][] td) {
		Displaced2DIntegerArray fd = new Displaced2DIntegerArray(l1[i] - 1, i,
				l2[j] - 1, j);
		fd.set(l1[i] - 1, l2[j] - 1, 0);
		for (int di = l1[i]; di <= i; di++)
			fd.set(di, l2[j] - 1, fd.get(di - 1, l2[j] - 1) + W_DEL);
		for (int dj = l2[j]; dj <= j; dj++)
			fd.set(l1[i] - 1, dj, fd.get(l1[i] - 1, dj - 1) + W_INS);
		for (int di = l1[i]; di <= i; di++)
			for (int dj = l2[j]; dj <= j; dj++) {
				int w_ren;
				if (true)
					w_ren = W_NOP;
				else
					w_ren = W_REN;
				if (l1[di] == l1[i] && l2[dj] == l2[j]) { //if (l[di] == l[i] && l[dj] == l[j]) {
					fd.set(di, dj, min(fd.get(di - 1, dj) + W_DEL, fd.get(di,
							dj - 1)
							+ W_INS, fd.get(di - 1, dj - 1) + w_ren));
					td[di][dj] = fd.get(di, dj); //td.set(di , dj, f[di, dj];
				} else {
					fd.set(di, dj, min(fd.get(di - 1, dj) + W_DEL, fd.get(di,
							dj - 1)
							+ W_INS, fd.get(l1[di] - 1, l2[dj] - 1)
							+ td[di][dj])); //fd.get(l[di] - 1, l[dj] - 1) + td[di][dj]));
				}
			}

	}

	private int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
}
