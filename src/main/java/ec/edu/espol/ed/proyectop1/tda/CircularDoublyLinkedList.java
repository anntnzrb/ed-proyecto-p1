package ec.edu.espol.ed.proyectop1.tda;

import java.util.Iterator;

public class CircularDoublyLinkedList<E> implements List<E> {
    /**
     * Puntero al último nodo.
     */
    private Node<E> last;

    /**
     * Cantidad de elementos presentes en la colección.
     */
    private int size;

    /* constructores */
    public CircularDoublyLinkedList() {
        last = null;
        size = 0;
    }

    /* getters & setters */
    private Node<E> getFirst() {
        return last.getNext();
    }

    private void setFirst(final Node<E> n) {
        last.setNext(n);
    }

    private Node<E> getLast() {
        return last;
    }

    private void setLast(final Node<E> n) {
        last = n;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public boolean isEmpty() {
        return getLast() == null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public void clear() {
        if (isEmpty()) { return; }

        // FIXME :: NPE
        for (Node<E> n = getFirst(); n != getLast(); n = n.getNext()) {
            final Node<E> nextNode = n.getNext();
            n.setData(null);
            n.setPrev(null);
            n.setNext(null);
            n = nextNode;
        }
        last = null;
    }

    /**
     * Verifica si el índice se encuentra en el rango de la colección.
     * Este método se emplea previamente a cualquier función que accede a
     * los índices de una colección.
     * <p>
     * Complejidad: O(1)
     *
     * @param idx índice a verificar
     */
    private void checkRange(final int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException(idx);
        }
    }

    /**
     * Retorna el nodo en el índice indicado.
     * <p>
     * Complejidad: O(n)
     *
     * @param idx índice del nodo a buscar
     * @return nodo en el índice indicado
     */
    private Node<E> node(final int idx) {
        checkRange(idx);

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            return getFirst();
        } else if (idx == (size - 1)) {
            return getLast();
        }

        /* índice menor que mitad de colección -> buscar desde inicio
         * caso contrario -> buscar desde el final
         */
        Node<E> n;
        if (idx < (size >> 1)) {
            n = getFirst();
            for (int i = 0; i < idx; ++i) {
                n = n != null ? n.getNext() : null;
            }
        } else {
            n = getLast();
            for (int i = (size - 1); i > idx; --i) {
                n = n != null ? n.getPrev() : null;
            }
        }

        return n;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addFirst(final E e) {
        if (e == null) {
            return false;
        }

        /* newNode === newFirst */
        final Node<E> newNode = new Node<>(e);

        if (isEmpty()) {
            setLast(newNode);
            setFirst(newNode);
            ++size;
            return true;
        }

        newNode.setPrev(getLast());
        newNode.setNext(getFirst());
        getFirst().setPrev(newNode);
        setFirst(newNode);

        ++size;
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean addLast(final E e) {
        if (e == null) {
            return false;
        }

        /* newNode === newLast */
        final Node<E> newNode = new Node<>(e);

        if (isEmpty()) {
            setLast(newNode);
            setFirst(newNode);
            ++size;
            return true;
        }

        newNode.setPrev(getLast());
        newNode.setNext(getFirst());
        getFirst().setPrev(newNode);
        setFirst(newNode);
        setLast(newNode);

        ++size;
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final void add(final int idx, final E e) {
        checkRange(idx);

        if (e == null) {
            return;
        }

        /* addFirst() y addLast() tienen complejidad O(1) */
        if (idx == 0) {
            addFirst(e);
        } else if (idx == (size - 1)) {
            addLast(e);
        }

        final Node<E> newNode = new Node<>(e);
        Node<E> p = getFirst();
        for (int i = 1; i < idx; i++) {
            p = p.getNext();
        }

        newNode.setPrev(p);
        newNode.setNext(p.getNext());
        p.setNext(newNode);
        newNode.getNext().setPrev(newNode);

        ++size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final E removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (getFirst() == getLast()) {
            final E oldVal = getFirst().getData();
            getFirst().setData(null);
            setFirst(null);
            setLast(null);

            --size;

            return oldVal;
        }

        final E oldVal = getFirst().getData();
        final Node<E> newFirst = getFirst().getNext();
        getFirst().setData(null);
        getFirst().setNext(null);
        setFirst(newFirst);
        getLast().setNext(newFirst);
        newFirst.setPrev(getLast());

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final E removeLast() {
        if (isEmpty()) {
            return null;
        } else if (getFirst() == getLast()) {
            final E oldVal = getLast().getData();
            getLast().setData(null);
            setFirst(null);
            setLast(null);

            --size;

            return oldVal;
        }

        final E oldVal = getLast().getData();
        final Node<E> newLast = getLast().getPrev();
        newLast.setNext(getLast().getNext());
        getLast().getNext().setPrev(newLast);
        getLast().setData(null);
        getLast().setNext(null);
        getLast().setPrev(null);
        setLast(newLast);

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E remove(final int idx) {
        checkRange(idx);

        /* reducir complejidad */
        if (isEmpty()) {
            return null;
        } else if (idx == 0) {
            removeFirst();
        } else if (idx == (size - 1)) {
            removeLast();
        }

        Node<E> p = getFirst();
        for (int i = 0; i < idx; ++i) {
            p = p.getNext();
        }
        p.getPrev().setNext(p.getNext());
        p.getNext().setPrev(p.getPrev());

        final E oldVal = p.getData();
        p.setData(null);
        p.setPrev(null);
        p.setNext(null);

        --size;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E get(final int idx) {
        return node(idx).getData();
    }

    @Override
    public E set(int idx, E e) {
        // TODO

        return null;
    }

    // cll = ['p', 'e', 'r', 'r', 'o'. 'x', 'y']
    // cll mod = ['y', 'p', 'e', 'r', 'r', 'o', 'x']

    public void desplazarNodosDerecha() {
        for (Node<E> n = getFirst(); n != getLast(); n = n.getNext()) {
            System.out.println(n.getData());
            n.setNext(n);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final String toString() {
        if (isEmpty()) {
            return "[]";
        } else if (size == 1) {
            return "[" + getLast().getData() + "]";
        }

        final StringBuilder str = new StringBuilder();

        str.append("[");
        for (Node<E> n = getFirst(); n != getLast(); n = n.getNext()) {
            str.append(n.getData()).append(", ");
        }
        str.append(getLast().getData());
        str.append("]");

        return str.toString();
    }

    /**
     * Retorna un Iterator de elementos de {@code E}.
     * <p>
     * Complejidad: O(1)
     *
     * @return un Iterator.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private Node<E> ptr = getFirst();
            private boolean isStarted = false;

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public boolean hasNext() {
                isStarted = (!isEmpty() && !isStarted) || ptr != getFirst();

                return isStarted;
            }

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public E next() {
                final E elem = ptr.getData();
                ptr = ptr.getNext();

                return elem;
            }
        };
    }
}
