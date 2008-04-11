package it.unibz.apeer.thesis;

import java.text.ParseException;

public class SimpleTreeParser {
	
	private String code_ = null;
	private int curIndex_ = 0;
	private TreeNode<String> tree_ = null;
	
	public static TreeNode<String> parse(String code) throws ParseException {
		SimpleTreeParser parser = new SimpleTreeParser(code);
		parser.parse();
		return parser.getTree();
	}
	
	public static String reconstruct(TreeNode<String> node) {
		String code = "";
		if (node != null) {
			code = "{" + node.getUserObject();
			for (TreeNode<String> child : node.getChildren()) {
				code += reconstruct(child);
			}
			code += "}";
		}
		return code;
	}
	
	public SimpleTreeParser(String code) {
		code_ = code;
	}
	
	public TreeNode<String> getTree() {
		return tree_;
	}
	
	public void parse() throws ParseException {
		if (code_.length() == 0) {
			return;
		} else {
			if (!nextToken().equals("{")) {
				throw new ParseException("'{' expected", curIndex_);
			}
			String token;
			token = nextToken();
			if (token.equals("{") || token.equals("}")) {
				throw new ParseException("Identifier expected", curIndex_);
			}
			tree_ = new SimpleTreeNode<String>(token);
			TreeNode<String> curNode = tree_;
			while ((token = nextToken()) != null) {
				if (token.equals("}")) {
					curNode = curNode.getParent();
				} else if (!token.equals("{")) {
					throw new ParseException("'{' expected", curIndex_);
				} else {
					token = nextToken();
					if (token.equals("{") || token.equals("}")) {
							token = "";
							--curIndex_;
					}
					TreeNode<String> newNode = new SimpleTreeNode<String>(token);
					curNode.addChild(newNode);
					curNode = newNode;
				}
			}
		}
	}
	
	private TreeNode<String> parse(int startIndex) throws ParseException {
		if (startIndex >= code_.length()) {
			return null;
		} else {
			if (code_.charAt(startIndex) != '{') {
				throw new ParseException("{ unexpected!", startIndex);
			}
			++curIndex_;
			String token = nextToken();
			TreeNode<String> newNode = new SimpleTreeNode<String>(token);
			if (code_.charAt(curIndex_) == '{') {
				newNode.addChild(parse(curIndex_));
			} else if (code_.charAt(curIndex_) == '}') {
				++curIndex_;
				return newNode;
			}
		}
		return null;
	}
	
	private String nextToken() {
		int oldIndex = curIndex_;
		while (curIndex_ < code_.length() && code_.charAt(curIndex_) != '{' && code_.charAt(curIndex_) != '}') {
			++curIndex_;
		}
		if (curIndex_ == code_.length()) {
			return null;
		}
		if (oldIndex == curIndex_) {
			++curIndex_;
		}
		return code_.substring(oldIndex, curIndex_);
	}
	
	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("Usage: <Tree in {} notation>");
			return;
		}
		/*try {
			TreeUtil.printTree(SimpleTreeParser.parse(args[0]));
		} catch (ParseException e) {
			System.out.println("Parsing error at " + e.getErrorOffset() + ": " + e.getLocalizedMessage());
		}*/
	}
}
