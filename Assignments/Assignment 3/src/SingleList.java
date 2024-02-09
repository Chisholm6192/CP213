package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author Ryan Chisholm, 169027577, chisi7577@mylaurier.ca
 * @version 2023-10-31
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The value to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {
	SingleNode<T> pointer = new SingleNode<T>(null, null); // new pointer node

	if (this.contains(key)) {
	    // don't search unless key exists
	    SingleNode<T> current = this.front;
	    SingleNode<T> previous = this.front;

	    // search until key is located
	    while (current.getItem().compareTo(key) != 0) {

		if (current.getNext() != null) {
		    previous = current;
		    current = current.getNext();
		}

	    }

	    pointer = previous;
	}

	return pointer;
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The value to append.
     */
    public void append(final T data) {
	SingleNode<T> node = new SingleNode<T>(data, null);

	if (this.isEmpty()) {
	    // if empty, new node is only node
	    this.front = node;
	    this.rear = this.front;
	}

	else {
	    // add onto rear
	    this.rear.setNext(node);
	    this.rear = node;
	}

	this.length += 1;

	return;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each value formerly present in this SingleList. The first occurrence of
     * each value is preserved.
     */
    public void clean() {
	SingleNode<T> current = this.front;

	while (current != null) {
	    // get key and count of key
	    T key = current.getItem();
	    int count = this.count(key);

	    SingleNode<T> curr = current.getNext(); // go to next, to skip first occurrence
	    SingleNode<T> prev = current;

	    while (count > 1) {
		if (curr.getItem().equals(key)) {
		    // if key is found, remove it
		    prev.setNext(curr.getNext());
		    curr = prev.getNext();
		    this.length -= 1;
		    count -= 1;
		}

		else {
		    // go to next value, only if no value was removed
		    prev = curr;
		    curr = curr.getNext();
		}
	    }
	    current = current.getNext();
	}

	return;

    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	while (!left.isEmpty() && !right.isEmpty()) {
	    this.moveFrontToRear(left);
	    this.moveFrontToRear(right);
	}

	while (!left.isEmpty()) {
	    this.moveFrontToRear(left);
	}

	while (!right.isEmpty()) {
	    this.moveFrontToRear(right);
	}

	return;
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key value to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {
	SingleNode<T> current = this.front;
	boolean contains = false;

	if (!this.isEmpty()) {
	    int i = 0;

	    while ((i < this.length) && (current.getItem().compareTo(key) != 0)) {
		// search until key is located, or end of list is reached
		if (current.getNext() != null) {
		    current = current.getNext();
		}

		i += 1;

	    }

	    if (i < this.length) {
		// if value was located
		contains = true;
	    }
	}

	return contains;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The value to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {
	int count = 0;

	if (this.contains(key)) {
	    int i = 0;
	    SingleNode<T> current = this.front;

	    while (i < this.length) {
		if (current.getItem().compareTo(key) == 0) {
		    // increase count each time key is found
		    count += 1;
		}

		if (current.getNext() != null) {
		    current = current.getNext();
		}

		i += 1;
	    }
	}

	return count;
    }

    /**
     * Finds and returns the value in list that matches key.
     *
     * @param key The value to search for.
     * @return The value that matches key, null otherwise.
     */
    public T find(final T key) {
	T value = null;

	if (this.contains(key)) {
	    // search for value
	    SingleNode<T> pointer = this.linearSearch(key);
	    value = pointer.getNext().getItem();
	}

	return value;
    }

    /**
     * Get the nth item in this SingleList.
     *
     * @param n The index of the item to return.
     * @return The nth item in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {
	T value = null;
	SingleNode<T> current = this.front;
	int i = n;

	while (i > 0) {
	    // move i number of indexes into list
	    i -= 1;

	    if (current.getNext() != null) {
		current = current.getNext();
	    }

	}

	value = current.getItem();

	return value;
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same values in the same order as
     *         source, false otherwise.
     */
    public boolean identical(final SingleList<T> source) {
	boolean identical = false;

	if (this.length == source.length) {
	    // must be same length
	    SingleNode<T> current = this.front;
	    SingleNode<T> currentSource = source.front;
	    int i = 0;

	    while (i < this.length && current.getItem().compareTo(currentSource.getItem()) == 0) {
		// run until mismatch is found, or end is reached
		i += 1;

		if (current.getNext() != null) {
		    current = current.getNext();
		    currentSource = currentSource.getNext();
		}

	    }

	    if (i == this.length) {
		// if no mismatch is found, they are identical
		identical = true;
	    }

	}

	return identical;
    }

    /**
     * Finds the first location of a value by key in this SingleList.
     *
     * @param key The value to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {
	int index = -1;

	if (this.contains(key)) {
	    index = 0;
	    SingleNode<T> current = this.front;

	    while (current.getItem().compareTo(key) != 0) {
		// search until key is located
		if (current.getNext() != null) {
		    current = current.getNext();
		}

		index += 1;
	    }

	}

	return index;
    }

    /**
     * Inserts value into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i    The index to insert the new data at.
     * @param data The new value to insert into this SingleList.
     */
    public void insert(int i, final T data) {

	if (i < this.length) {
	    SingleNode<T> node = new SingleNode<T>(data, null);
	    SingleNode<T> current = this.front;
	    SingleNode<T> prev = this.front;

	    while (i > 0) {
		// move i number of places into the list

		if (current.getNext() != null) {
		    prev = current;
		    current = current.getNext();
		}

		i -= 1;
	    }

	    // insert new node in between previous and current
	    prev.setNext(node);
	    node.setNext(current);
	    this.length += 1;
	}

	else {
	    // if i is outside of range, append to end
	    this.append(data);
	}

	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then values from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {
	SingleNode<T> left_curr = left.front;

	while (left_curr != null) {
	    // searching left
	    SingleNode<T> right_curr = right.front;

	    while (right_curr != null) {
		// searching right
		if (left_curr.getItem().equals(right_curr.getItem())) {
		    // if values match, check to see if they already exist in local
		    if (!(this.contains(left_curr.getItem()))) {
			this.append(left_curr.getItem());
		    }
		}

		right_curr = right_curr.getNext();
	    }

	    left_curr = left_curr.getNext();
	}
	return;
    }

    /**
     * Finds the maximum value in this SingleList.
     *
     * @return The maximum value.
     */
    public T max() {
	T max = this.front.getItem();
	SingleNode<T> current = this.front;
	int i = 0;

	while (i < this.length) {
	    // look for new max value
	    if (current.getItem().compareTo(max) > 0) {
		max = current.getItem();
	    }

	    if (current.getNext() != null) {
		current = current.getNext();
	    }

	    i += 1;
	}

	return max;
    }

    /**
     * Finds the minimum value in this SingleList.
     *
     * @return The minimum value.
     */
    public T min() {
	T min = this.front.getItem();
	SingleNode<T> current = this.front;
	int i = 0;

	while (i < this.length) {
	    // look for new min value
	    if (current.getItem().compareTo(min) < 0) {
		min = current.getItem();
	    }

	    if (current.getNext() != null) {
		current = current.getNext();
	    }

	    i += 1;
	}

	return min;
    }

    /**
     * Inserts value into the front of this SingleList.
     *
     * @param data The value to insert into the front of this SingleList.
     */
    public void prepend(final T data) {
	if (this.isEmpty()) {
	    // if empty, new node is the only node
	    SingleNode<T> node = new SingleNode<T>(data, null);
	    this.front = node;
	    this.rear = this.front;
	    this.length += 1;
	}

	else {
	    // make new node point to front, making it the new front
	    SingleNode<T> node = new SingleNode<T>(data, this.front);
	    this.front = node;
	    this.length += 1;
	}

	return;
    }

    /**
     * Finds, removes, and returns the value in this SingleList that matches key.
     *
     * @param key The value to search for.
     * @return The value matching key, null otherwise.
     */
    public T remove(final T key) {
	T value = null;

	if (this.contains(key)) {
	    SingleNode<T> current = this.front;
	    SingleNode<T> prev = this.front;
	    int n = this.index(key); // get location of key

	    while (n > 0) {
		// traverse until location is found
		if (current.getNext() != null) {
		    prev = current;
		    current = current.getNext();
		}

		n -= 1;
	    }

	    value = current.getItem();
	    // set previous node to point to next node, removing node in between
	    prev.setNext(current.getNext());
	    this.length -= 1;
	}

	return value;
    }

    /**
     * Removes the value at the front of this SingleList.
     *
     * @return The value at the front of this SingleList.
     */
    public T removeFront() {
	T value = null;

	if (!this.isEmpty()) {
	    value = this.front.getItem();
	    // replace front with next node
	    this.front = this.front.getNext();
	    this.length -= 1;
	}

	return value;
    }

    /**
     * Finds and removes all values in this SingleList that match key.
     *
     * @param key The value to search for.
     */
    public void removeMany(final T key) {
	int count = this.count(key);
	SingleNode<T> current = this.front;
	SingleNode<T> prev = null;

	while (count > 0) {
	    if (current.getItem().compareTo(key) == 0) {
		// key is located

		if (prev == null) {
		    // if key is at the front, replace it with new front
		    this.front = this.front.getNext();
		}

		else {
		    // make previous node point to next node, skipping node in between
		    prev.setNext(current.getNext());
		}

		count -= 1;
		this.length -= 1;
	    }

	    else if (current.getNext() != null) {
		// proceed to next node, if none were removed
		prev = current;
		current = current.getNext();
	    }

	}

	return;
    }

    /**
     * Reverses the order of the values in this SingleList.
     */
    public void reverse() {
	if (!this.isEmpty()) {

	    int i = this.length - 1;

	    while (i > 0) {
		if (i == this.length) {
		    this.insert(i + 1, this.removeFront());
		}

		else {
		    this.insert(i, this.removeFront());
		}

		i -= 1;
	    }
	}

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * item than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {
	int size = (int) Math.floor(length / 2);
	int i = this.length;

	while (i > size) {
	    left.moveFrontToRear(this);
	    i -= 1;
	}

	while (i > 0) {
	    right.moveFrontToRear(this);
	    i -= 1;
	}

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	while (this.length > 1) {
	    left.moveFrontToRear(this);
	    right.moveFrontToRear(this);

	}

	if (this.length == 1) {
	    left.moveFrontToRear(this);
	}

	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies value
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then values from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> left_node = left.front;
	SingleNode<T> right_node = right.front;

	while (left_node != null) {
	    this.append(left_node.getItem());
	    left_node = left_node.getNext();
	}

	while (right_node != null) {
	    this.append(right_node.getItem());
	    right_node = right_node.getNext();
	}

	return;
    }
}
