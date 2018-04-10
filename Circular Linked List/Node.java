/**
* The node class. Remember that this is a circular linked list.
*
* @author CS 1332 TAs
*/
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this(data, null);
    }
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    public Node<T> getNext() {
        return next;
    }
    
    public void setNext(Node<T> node) {
        next = node;
    }
    
    public T getData() {
        return data;
    }
}
