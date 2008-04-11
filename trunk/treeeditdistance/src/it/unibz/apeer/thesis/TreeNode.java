package it.unibz.apeer.thesis;

import java.util.List;

public interface TreeNode<T> {
	
	public void setUserObject(T object);
	
	public T getUserObject();
	
	public void addChild(TreeNode<T> node);
	
	public List<TreeNode<T>> getChildren();
	
	public TreeNode<T> getParent();
	
	public boolean isLeaf();
}
