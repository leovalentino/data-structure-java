package ds;

public class BasicBinaryTree<X extends Comparable<X>> {

	private Node root;
	private int size;
	
	public BasicBinaryTree() {
		this.root = null;
	}
	
	public int size() {
		return size;
	}
	
	public void add(X item) {
		Node node = new Node(item);
		
		//if this is the first item in the tree,set it as root
		if (root == null) {
			this.root = node;
			System.out.println("Set root: " + node.getItem());
			this.size++;
		} else {
			insert(this.root, node);
		}
	}
	
	public boolean contains(X item) {
		Node currentNode = getNode(item);
		
		if (currentNode == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean delete(X item) {
		boolean deleted = false;
		
		if (this.root == null) {
			return false;
		}
		
		//find the node to delete
		Node currentNode = getNode(item);
		
		if (currentNode != null) {
			// if the node to delete doesn't have any childre, just delete it
			if (currentNode.getLeft() == null && currentNode.getRigth() == null) {
				unlink(currentNode, null);
				deleted = true;
			}
			// if the node to delete only has a right child, remove it in the hierarchy
			else if (currentNode.getLeft() == null && currentNode.getRigth() != null) {
				unlink(currentNode, currentNode.getRigth());
				deleted = true;
			}
			// if the node to delete only has a left child, remove it in the hierarchy
			else if (currentNode.getLeft() != null && currentNode.getRigth() == null) {
				unlink(currentNode, currentNode.getLeft());
				deleted = true;
			} else {
				//you can swap out the node with the right most leaf node on the left side
				Node child = currentNode.getLeft();
				while (child.getRigth() != null && child.getLeft() != null) {
					child = child.getRigth();
				}
				
				//we have the right most leaf node. We can replace the current node with this node
				child.getParent().setRigth(null); //remove the leaf node from it's current position
				
				child.setLeft(currentNode.getLeft());
				child.setRigth(currentNode.getRigth());
				
				unlink(currentNode, child);
				deleted = true;
			}
		}
		
		if (deleted) {
			this.size--;
		}
		
		return deleted;
	}
	
	private void unlink(Node currentNode, Node newNode) {
		// if this is the root node, we replace that a little differently
		if (currentNode == this.root) {
			newNode.setLeft(currentNode.getLeft());
			newNode.setRigth(currentNode.getRigth());
			this.root = newNode;
		} else if (currentNode.getParent().getRigth() == currentNode) {
			currentNode.getParent().setRigth(newNode);
		} else {
			currentNode.getParent().setLeft(newNode);
		}
	}
	
	private Node getNode(X item) {
		Node currentNode = this.root;
		
		while (currentNode != null) {
			int val = item.compareTo(currentNode.getItem());
			
			//see if the node is a match
			if (val == 0) {
				return currentNode;
			}
			//if the val is less than 0 we got to the left side of the three
			else if (val < 0) {
				currentNode = currentNode.getLeft();
			} else {
				currentNode = currentNode.getRigth();
			}
		}
		return null;
	}
	
	private void insert(Node parent, Node child) {
		//if the child is less than the parent, it goes on the left
		if (child.getItem().compareTo(parent.getItem()) < 0) {
			//if the node is null, we've found our spot
			if (parent.getLeft() == null) {
				parent.setLeft(child);
				child.setParent(parent);
				this.size++;
			}
			//otherwise we need to call insert again and test for left or right recursion
			else {
				insert(parent.getLeft(), child);
			}
		} else if (child.getItem().compareTo(parent.getItem()) > 0) {
			//if the node is null, we've found our spot
			if (parent.getRigth() == null) {
				parent.setRigth(child);
				child.setParent(parent);
				this.size++;
			}
			//otherwise we need to call insert again and test for right or right recursion
			else {
				insert(parent.getRigth(), child);
			}
		}
	}
	
	private class Node {
		private Node left;
		private Node rigth;
		private Node parent;
		private X item;

		public Node(X item) {
			this.item = item;
			this.left = null;
			this.rigth = null;
			this.parent = null;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRigth() {
			return rigth;
		}

		public void setRigth(Node rigth) {
			this.rigth = rigth;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public X getItem() {
			return item;
		}

		public void setItem(X item) {
			this.item = item;
		}

	}

}
