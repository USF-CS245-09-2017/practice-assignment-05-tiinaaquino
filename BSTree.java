import java.util.ArrayList;

public class BSTree {
	
	class BSTNode {
		private String data;
		private BSTNode right;
		private BSTNode left;
		
		// constructor for the inner BSTNode class
		private BSTNode(String data) {
			this.data = data;
			right = null;
			left = null;
		}
	}
	
	private BSTNode root;
	private ArrayList<String> inOrder;
	private ArrayList<String> preOrder;
	
	// constructor
	public BSTree() {
		root = null;
		inOrder = new ArrayList<String>();
		preOrder = new ArrayList<String>();
	}
	
	/**
	 * adds a Comparable instance to the BST
	 * 
	 * @param input
	 * 			node to add
	 */
	public void insert(String input) {
		if (root == null) {
			root = new BSTNode(input);
			return;
		}
		BSTNode newNode = new BSTNode(input);
		BSTNode tempNode = root;
		boolean value = true;
		
		while (value) {
			if (tempNode.data.compareTo(input) >= 0) {
				if (tempNode.left == null) {
					tempNode.left = newNode;
					value = false;
				}
				tempNode = tempNode.left;
			}
			else {
				if (tempNode.right == null) {
					tempNode.right = newNode;
					value = false;
				}
				tempNode = tempNode.right;
			}
		}
	}
	
	/**
	 * 
	 * finds a Comparable instance in the BST
	 * 
	 * @param input
	 * 		target node to search for in the BST
	 * @return
	 * 		"true" if input is found in the BST,
	 * 		"false" if input is not found in the BST
	 */
	public boolean find(String input) {
		BSTNode tempNode = root;
		while (tempNode != null) {
			if (tempNode.data.compareTo(input) == 0) {
				return true;
			}
			else if (tempNode.data.compareTo(input) > 0) {
				tempNode = tempNode.left;
			}
			else {
				tempNode = tempNode.right;
			}
		}
		return false;
	}
	
	
	/**
	 * removes a Comparable instance from the BST
	 * 
	 * @param input
	 * 		node to remove from the BST
	 */
	public void delete(String input) {
		BSTNode before = null;
		BSTNode after = null;
		BSTNode parent = null;
		BSTNode tempRoot = root;
		
		if (root.data.compareTo(input) == 0) {
			if (root.left != null) {
				BSTNode tempRightRoot = tempRoot.right;
				root = tempRoot.left;
				tempRoot = root;
				while (tempRoot.right != null) {
					tempRoot = tempRoot.right;
				}
				tempRoot.right = tempRightRoot;
			}
			else {
				if (root.right == null) {
					root = null;
				}
				else {
					root = root.right;
					root.left = null;
				}
			}
			
			return;
		}
		
		while (tempRoot != null) {
			if (tempRoot.data.compareTo(input) > 0) {
				while (tempRoot.left != null) {
					before = tempRoot;
					tempRoot = tempRoot.left;
					if (tempRoot.data == input) {
						after = tempRoot.left;
						if (tempRoot.right != null) {
							tempRoot = tempRoot.right;
							parent = tempRoot;
							
							while (tempRoot.left != null) {
								tempRoot = tempRoot.left;
							}
							
							before.left = parent;
							tempRoot.left = after;
						}
						else {
							before.left = after;
						}
						
						return;
					}
				}
			}
			
			else {
				while (tempRoot.right != null) {
					before = tempRoot;
					tempRoot = tempRoot.right;
					if (tempRoot.data == input) {
						after = tempRoot.right;
						
						if (tempRoot.left != null) {
							tempRoot = tempRoot.left;
							parent = tempRoot;
							
							while (tempRoot.right != null) {
								tempRoot = tempRoot.right;
							}
							
							before.right = parent;
							tempRoot.right = after;
							
						}
						
						else {
							before.right = after;
						}
						
						return;
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @return
	 * 		a space-separated copy of the contents stored in the
	 * 		BST in (sorted) order
	 */
	public String toStringInOrder() {
		String result = "";
		String spaces = " ";
		printInOrder(root);
		
		for (int i = 0; i < inOrder.size(); i++) {
			if (i == inOrder.size() - 1)
				spaces = "";
			result += inOrder.get(i) + spaces;
		}
		
		return result;
	}
	
	public void printInOrder(BSTNode root) {
		if (root == null)
			return;
		
		printInOrder(root.left);
		inOrder.add(root.data);
		printInOrder(root.right);
	}
	
	/**
	 * 
	 * @return
	 * 		a space separated copy of the contents stored in
	 * 		the BST in pre-order: the contents of the root node,
	 * 		followed by the contents of the left child and the
	 * 		contents of the right child
	 */
	public String toStringPreOrder() {
		String result = "";
		String spaces = " ";
		printPreOrder(root);
		
		for (int i = 0; i < preOrder.size(); i++) {
			if (i == inOrder.size() - 1)
				spaces = "";
			result += preOrder.get(i) + spaces;
		}
		
		return result;
	}
	
	public void printPreOrder(BSTNode root) {
		if (root == null)
			return;
		
		preOrder.add(root.data);
		printPreOrder(root.left);
		printPreOrder(root.right);
	}
	
	
}