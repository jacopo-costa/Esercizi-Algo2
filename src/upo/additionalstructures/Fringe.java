package upo.additionalstructures;

/**
 * Interfaccia per implementare in modo trasparente
 * le strutture per le visite
 *
 * @param <E> Tipo di valore da aggiungere
 */
public interface Fringe<E> {
    void add(E e);

    E first();

    void removeFirst();

    boolean isEmpty();

}
