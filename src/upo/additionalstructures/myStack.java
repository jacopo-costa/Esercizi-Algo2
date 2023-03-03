package upo.additionalstructures;

import java.util.Stack;

/**
 * Stack per la visita DFS.
 *
 * @param <E> Tipo da aggiungere alla frangia
 */
public class myStack<E> implements Fringe<E> {

    private final Stack<E> myStack = new Stack<>();

    @Override
    public void add(E e) {
        myStack.push(e);
    }

    @Override
    public E first() {
        return myStack.peek();
    }

    @Override
    public void removeFirst() {
        myStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return myStack.isEmpty();
    }
}
