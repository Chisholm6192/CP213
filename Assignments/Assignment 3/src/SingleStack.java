package cp213;

/**
 * A single linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> value contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class. Note that the rear
 * attribute should be ignored as the rear is not used in a stack.
 *
 * @author Ryan Chisholm, 169027577, chis7577@mylaurier.ca
 * @version 2023-11-01
 * @param <T> the SingleStack data type.
 */
public class SingleStack<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleStacks into the current
     * SingleStack. Moves nodes only - does not refer to values in any way, or call
     * the high-level methods pop or push. left and right SingleStacks are empty
     * when done. Nodes are moved alternately from left and right to this
     * SingleStack.
     *
     * You have two source stacks named left and right. Move all nodes from these
     * two stacks to the current stack. It does not make a difference if the current
     * stack is empty or not, just get nodes from the right and left stacks and add
     * them to the current stack. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left stacks are of the same length.
     *
     * @param left  The first SingleStack to extract nodes from.
     * @param right The second SingleStack to extract nodes from.
     */
    public void combine(final SingleStack<T> left, final SingleStack<T> right) {
	// alternate between right and left stacks until one is empty
	while (!(left.isEmpty()) && !(right.isEmpty())) {
	    this.moveFrontToFront(left);
	    this.moveFrontToFront(right);
	}

	// if the stacks are of different lengths
	// that one stack must be added pushed to after the other is empty
	while (!(left.isEmpty())) {
	    this.moveFrontToFront(left);
	}

	while (!(right.isEmpty())) {
	    this.moveFrontToFront(right);
	}

	return;
    }

    /**
     * Returns the top value of the stack and removes that value from the stack. The
     * next node in the stack becomes the new top node. Decrements the stack length.
     *
     * @return The value at the top of the stack.
     */
    public T pop() {
	T value = null;

	if (!this.isEmpty()) {
	    value = this.front.getItem(); // value of item to be popped

	    this.front = this.front.getNext(); // new front
	    this.length -= 1;
	}

	return value;
    }

    /**
     * Adds data to the top of the stack. Increments the stack length.
     *
     * @param data The value to add to the top of the stack.
     */
    public void push(final T data) {
	SingleNode<T> node = new SingleNode<T>(data, null); // create new node to push

	if (this.isEmpty()) { // current stack is empty
	    this.front = node;
	}

	else { // non empty stack
	    node.setNext(this.front); // set current front to be next for new front
	    this.front = node;
	}

	this.length += 1;

	return;
    }

    /**
     * Splits the contents of the current SingleStack into the left and right
     * SingleStacks. Moves nodes only - does not move value or call the high-level
     * methods insert or remove. this SingleStack is empty when done. Nodes are
     * moved alternately from this SingleStack to left and right. left and right may
     * already contain values.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleStack to move nodes to.
     * @param right The second SingleStack to move nodes to.
     */
    public void splitAlternate(final SingleStack<T> left, final SingleStack<T> right) {
	while (this.length > 1) {
	    // take values from top of each stack and move them to top of local stack
	    left.moveFrontToFront(this);
	    right.moveFrontToFront(this);
	}

	if (this.length == 1) {
	    left.moveFrontToFront(this);
	}

	return;
    }
}