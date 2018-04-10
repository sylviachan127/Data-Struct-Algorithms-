/**
 * CircularLinkedList implementation
 * 
 * @author Yuen Han Chan
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T> {
	private Node head = null, tail = null;
	private int count = 0;
	private T[] circularArrayList;

	@Override
	public void addAtIndex(int index, T data) {
		if ((index < 0) || (count < index)) {
			throw new IndexOutOfBoundsException(
					"The index is either negative or out of the list size");
		} else if (index == 0) {
			addToFront(data);
		} else if (index == count) {
			addToBack(data);
		} else {
			Node current = head;
			for (int start = 1; start < index; start++) {
				current = current.getNext();
			}
			Node newAdd = new Node(data, current.getNext());
			current.setNext(newAdd);
			count++;
		}
	}

	@Override
	public T get(int index) {
		if ((index < 0) || (count <= index)) {
			throw new IndexOutOfBoundsException(
					"The index is either negative or out of the list size");
		} else if (count == 0) {
			return null;
		} else if (index == 0) {
			return (T) head.getData();
		} else if (index == (count - 1)) {
			return (T) tail.getData();
		} else {
			Node current = head;
			for (int start = 0; start < index; start++) {
				current = current.getNext();
			}
			return (T) current.getData();
		}
	}

	@Override
	public T removeAtIndex(int index) {
		if ((index < 0) || (count <= index)) {
			throw new IndexOutOfBoundsException(
					"The index is either negative or out of the list size");
		} else {
			if (index == 0) {
				if (head == null) {
					return null;
				} else {
					Node current = head;
					T data = (T) current.getData();
					tail.setNext(current.getNext());
					head = current.getNext();
					count--;
					if (count == 0) {
						head = null;
					}
					return data;
				}
			} else {
				Node current = head;
				for (int start = 1; start < index; start++) {
					current = current.getNext();
				}
				T data = (T) current.getNext().getData();
				current.setNext(current.getNext().getNext());
				count--;
				if (count == 1) {
					tail = current;
				}
				if (count == 0) {
					head = null;
				}
				return data;
			}
		}
	}

	@Override
	public void addToFront(T t) {
		if (head == null) {
			head = new Node(t);
			tail = head;
			tail.setNext(head);
			count++;
		} else {
			head = new Node(t, head);
			count++;
		}
	}

	@Override
	public void addToBack(T t) {
		if (head == null) {
			addToFront(t);
		} else {
			Node current = new Node(t, head);
			tail.setNext(current);
			tail = current;
			count++;
		}
	}

	@Override
	public T removeFromFront() {
		if (head == null) {
			return null;
		} else {
			Node current = head;
			T data = (T) current.getData();
			tail.setNext(current.getNext());
			head = current.getNext();
			count--;
			if (count == 0) {
				head = null;
			}
			return data;
		}
	}

	@Override
	public T removeFromBack() {
		if ((head == null) || (count == 1)) {
			removeFromFront();
		} else {
			Node current = head;
			while (current.getNext() != head) {
				current = current.getNext();
			}
			T data = (T) current.getNext().getData();
			// T data = (T) current.getData();
			current.setNext(head);
			tail = current;
			count--;
			return data;
		}
		return null;
	}

	@Override
	public T[] toList() {
		circularArrayList = (T[]) new Object[count];
		Node current = head;
		for (int start = 0; start < count; start++) {
			if (current == null) {
				circularArrayList[start] = null;
			}
			circularArrayList[start] = (T) current.getData();
			current = current.getNext();
		}
		return circularArrayList;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public void clear() {
		count = 0;
		circularArrayList = (T[]) new Object[count];
	}

	/**
	 * Reference to the head node of the linked list. Normally, you would not do
	 * this, but we need it for grading your work.
	 * 
	 * @return Node representing the head of the linked list
	 */
	public Node<T> getHead() {
		if (head == null) {
			return null;
		} else {
			return head;
		}
	}

	/**
	 * Reference to the tail node of the linked list. Normally, you would not do
	 * this, but we need it for grading your work.
	 * 
	 * @return Node representing the tail of the linked list
	 */
	public Node<T> getTail() {
		if (tail == null) {
			return null;
		} else {
			return tail;
		}
	}

	/**
	 * This method is for your testing purposes. You may choose to implement it
	 * if you wish.
	 */
	@Override
	public String toString() {
		return "";
	}
}
