package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Ryan Chisholm
 * @author David Brown
 * @version 2023-11-20
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance item of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {
	int height_r = 0;
	int height_l = 0;
	int height = 0;
	int max = 0;

	if (node != null) {
	    height = 1;
	    // find height of both left and right sides of node
	    height_r = (node.getRight() != null) ? this.balanceAux(node.getRight(), height, max) : 0;
	    height_l = (node.getLeft() != null) ? this.balanceAux(node.getLeft(), height, max) : 0;
	}

	int balance = height_l - height_r; // determine balance value

	return balance;
    }

    /**
     * Returns the height and max height of the branches checked so far
     * 
     * @param node,   the current node being checked
     * @param height, the current height of the current traversal
     * @param max,    the maximum subtree height found so far
     * @return max, the maximum subtree height that was found
     */
    protected int balanceAux(final TreeNode<T> node, int height, int max) {
	if (node.getRight() != null) { // find height of right branches
	    height += 1;
	    this.balanceAux(node.getRight(), height, max);
	}

	else {
	    if (height > max) {
		max = height;
	    }
	}

	if (node.getLeft() != null) { // find height of left branches
	    height += 1;
	    this.balanceAux(node.getLeft(), height, max);
	}

	else {
	    if (height > max) {
		max = height;
	    }
	}

	return max;
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {
	int balance = this.balance(node);
	TreeNode<T> replacement = null;

	if (balance > 1) {
	    replacement = this.rotateRight(node);

	}

	else if (balance < -1) {
	    replacement = this.rotateLeft(node);
	}

	return replacement;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {
	TreeNode<T> root = node.getRight();
	TreeNode<T> left = (root != null) ? root.getLeft() : null;
	if (root != null) {
	    root.setLeft(node);
	}
	node.setRight(left);

	return root;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {
	TreeNode<T> root = node.getLeft();
	TreeNode<T> right = (root != null) ? root.getRight() : null;
	if (root != null) {
	    root.setRight(node);
	}
	node.setLeft(right);

	return root;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {
	if (node == null) { // if tree is empty, or end of branch is reached, create new node
	    node = new TreeNode<T>(data);
	    node.getdata().incrementCount();
	    this.size += 1;
	}

	else {
	    // Determine which branch to proceed down for insertion
	    if (node.getdata().compareTo(data) > 0) {
		node.setLeft(this.insertAux(node.getLeft(), data));
	    }

	    else if (node.getdata().compareTo(data) < 0) {
		node.setRight(this.insertAux(node.getRight(), data));
	    }

	    else {
		node.getdata().incrementCount();
	    }
	}

	node.updateHeight();

	// balancing and rotating the tree

	int balance = this.balance(node);

	if (balance > 1 && (node.getLeft().getdata().compareTo(data) > 0)) {
	    node = this.rotateRight(node);
	}

	if (balance < -1 && (node.getRight().getdata().compareTo(data) < 0)) {
	    node = this.rotateLeft(node);
	}

	if (balance > 1 && (node.getLeft().getdata().compareTo(data) < 0)) {
	    node.setLeft(this.rotateLeft(node.getLeft()));
	    node = this.rotateRight(node);
	}

	if (balance < -1 && (node.getRight().getdata().compareTo(data) > 0)) {
	    node.setRight(this.rotateRight(node.getRight()));
	    node = this.rotateLeft(node);
	}

	return node;

    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
	int balance = this.balance(node);
	boolean isvalid = false;

	if (balance < 1 && balance > -1) {

	    if (node.getLeft() == null && node.getRight() == null) {
		// both children are null
		isvalid = true;
	    }

	    else if (node.getLeft() == null || node.getRight() == null) {
		// one child null
		boolean isvalid_r = true;
		boolean isvalid_l = true;

		if (node.getRight() != null) {
		    if (node.getRight().getdata().compareTo(node.getdata()) > 0) {
			isvalid_r = this.isValidAux(node.getRight(), null, null);
		    } else {
			isvalid = false;
		    }
		}

		if (node.getLeft() != null) {
		    if (node.getLeft().getdata().compareTo(node.getdata()) < 0) {
			isvalid_l = this.isValidAux(node.getLeft(), null, null);
		    } else {
			isvalid = false;
		    }
		}

		if (isvalid_r && isvalid_l) { // both left and right subtree's are valid
		    isvalid = true;
		} else {
		    isvalid = false;
		}
	    }

	    else {
		// neither child is null
		if (node.getLeft().getdata().compareTo(node.getdata()) < 0
			&& node.getRight().getdata().compareTo(node.getdata()) > 0) {
		    boolean isvalid_r = true;
		    boolean isvalid_l = true;

		    isvalid_r = this.isValidAux(node.getRight(), null, null);

		    isvalid_l = this.isValidAux(node.getLeft(), null, null);

		    if (isvalid_r && isvalid_l) { // both left and right subtree's are valid
			isvalid = true;
		    }

		}

		else { // end has not been reached, and condition not met
		    isvalid = false;
		}
	    }
	}

	return isvalid;
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

}
