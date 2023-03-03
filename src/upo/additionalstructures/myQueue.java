package upo.additionalstructures;

import java.util.LinkedList;

/**
 * Coda per la visita BFS
 *
 * @param <E> Tipo da aggiungere alla frangia
 */
public class myQueue<E> implements Fringe<E> {

    private final LinkedList<E> queue = new LinkedList<>();

    @Override
    public void add(E e) {
        queue.add(e);
    }

    @Override
    public E first() {
        return queue.peek();
    }

    @Override
    public void removeFirst() {
        queue.remove();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
