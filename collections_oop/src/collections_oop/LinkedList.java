package collections_oop;

public class LinkedList implements List {

	private class Node {
		/**
		 * @invar | previous != null
		 * @invar | (element == null) == (this == sentinel)
		 * @invar | next != null
		 * @invar | previous.next == this
		 * @invar | next.previous == this
		 * 
		 * @peerObject
		 */
		private Node previous;
		private Object element;
		/**
		 * @peerObject
		 */
		private Node next;
		
		private int getLength() {
			return this == sentinel ? 0 : 1 + next.getLength();
		}
	}
	
	/**
	 * @invar | sentinel != null
	 * @invar | size == sentinel.next.getLength()
	 * @representationObject
	 */
	private Node sentinel;
	private int size;
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (Node n = sentinel.next; n != sentinel; n = n.next)
			result[i++] = n.element;
		return result;
	}

	@Override
	public int size() {
		return size;
	}
	
	private Node getNode(int index) {
		if (index < size / 2) {
			Node n = sentinel.next;
			while (index > 0) {
				n = n.next;
				index--;
			}
			return n;
		} else {
			Node n = sentinel;
			while (index < size) {
				n = n.previous;
				index++;
			}
			return n;
		}
	}

	@Override
	public Object get(int index) {
		return getNode(index).element;
	}

	@Override
	public boolean contains(Object element) {
		for (Node n = sentinel.next; n != sentinel; n = n.next)
			if (n.element.equals(element))
				return true;
		return false;
	}
	
	@Override
	public void add(int index, Object element) {
		Node n = getNode(index);
		Node newNode = new Node();
		newNode.element = element;
		newNode.previous = n.previous;
		newNode.next = n;
		newNode.previous.next = newNode;
		newNode.next.previous = newNode;
		size++;
	}

	@Override
	public void remove(int index) {
		Node n = getNode(index);
		n.previous.next = n.next;
		n.next.previous = n.previous;
		size--;
	}
	
	@Override
	public void set(int index, Object element) {
		getNode(index).element = element;
	}

}
