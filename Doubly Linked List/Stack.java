/**
 * Your stack implementation. Don't add any new public methods.
 *
 * @author Yuen Han Chan
 * @version 1.0
 */
public class Stack<T> implements StackInterface<T> {

    private LinkedListInterface<T> stack;

    public Stack() {
        stack = new DoublyLinkedList<T>();
    }

    @Override
    public void push(T t) {
        stack.addToFront(t);
    }

    @Override
    public T pop() {
        return stack.removeFromFront();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }



}
