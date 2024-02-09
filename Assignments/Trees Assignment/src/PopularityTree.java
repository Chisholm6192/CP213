package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Ryan Chisholm
 * @author David Brown
 * @version 2023-11-20
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node item is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The item to search for. Count is updated to count in matching
     *             node item if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedItem<T> key) {

	TreeNode<T> value = null;

	if (node.getdata().compareTo(key) == 0) { // if key is found, get matching
	    this.comparisons++;
	    value = node;
	    value.getdata().incrementCount();
	}

	else { // keep traversing
	       // check if children are null, ie. if end is reached
	    if (node.getRight() != null && node.getdata().compareTo(key) < 0) {
		this.comparisons++;
		value = this.retrieveAux(node.getRight(), key);
	    }

	    if (node.getLeft() != null && node.getdata().compareTo(key) > 0) {
		this.comparisons++;
		value = this.retrieveAux(node.getLeft(), key);
	    }
	}

	int leftCount = (node.getLeft() != null) ? node.getLeft().getdata().getCount() : 0;
	int rightCount = (node.getRight() != null) ? node.getRight().getdata().getCount() : 0;

	if (leftCount > node.getdata().getCount() || rightCount > node.getdata().getCount()) {
	    this.comparisons++;

	    if (leftCount > rightCount) {
		node = this.rotateRight(node);
	    } else {
		node = this.rotateLeft(node);
	    }

	}

	return value;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {
	TreeNode<T> right = parent.getRight();
	right.setLeft(this.root);
	this.root.setRight(parent);
	parent.setRight(null);
	this.root = right;

	return right;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {
	TreeNode<T> left = parent.getLeft();
	left.setRight(this.root);
	this.root.setLeft(parent);
	this.root = left;

	return left;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {
	if (node == null) { // if tree is empty, or end of branch is reached, create new node
	    node = new TreeNode<T>(data);
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
	}

	node.updateHeight();

	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
	boolean isvalid = false;

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

	    if ((node.getdata().getCount() > node.getRight().getdata().getCount())
		    && (node.getdata().getCount() > node.getRight().getdata().getCount())) {
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

	    else {
		isvalid = false;
	    }
	}

	return isvalid;

    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, item, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedItem<T> retrieve(CountedItem<T> key) {
	TreeNode<T> node = this.root;
	node = this.retrieveAux(node, key);
	CountedItem<T> data = node.getdata();
	return data;
    }

}
