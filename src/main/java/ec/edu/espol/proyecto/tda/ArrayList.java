package ec.edu.espol.proyecto.tda;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ArrayList<E> implements List<E> {
    /**
     * Capacidad inicial de la colección por defecto.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Buffer de la colección en donde se almacenan los elementos del ArrayList.
     */
    private E[] elems;
    /**
     * Cantidad de elementos de la colección.
     */
    private int capacity;

    /**
     * Cantidad de elementos presentes en la colección (no su capacidad).
     */
    private int size;

    /* constructores */
    public ArrayList(final int minCapacity) {
        capacity = minCapacity;
        elems = (E[]) new Object[capacity];
        size = 0;
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final void clear() {
        if (isEmpty()) { return; }

        for (int i = 0; i < size; ++i) {
            elems[i] = null;
        }

        /* alternative usando Arrays.fill */
        //Arrays.fill(elems, 0);

        size = 0;
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
     * Verifica si la colección está llena.
     * <p>
     * Complejidad: O(1)
     *
     * @return {@code true} si la colección está llena
     */
    private boolean isFull() {
        return capacity == size;
    }

    /**
     * Aumenta la capacidad de la colección, multiplica su capacidad 1.5 veces.
     * <p>
     * Complejidad: O(n)
     */
    private void grow() {
        final int newCap = size + (size >> 1);
        final E[] tmpArr = (E[]) new Object[newCap];
        //noinspection ManualArrayCopy
        for (int idx = 0; idx < size; ++idx) {
            tmpArr[idx] = elems[idx];
        }
        elems = tmpArr;
        capacity = newCap;

        /* Alternativa usando System.arraycopy */
        //System.arraycopy(elems, 0, tmpArr, 0, size);
    }

    /**
     * Desplaza los nodos de la colección hacia la izquierda.
     * <p>
     * Complejidad: O(n)
     *
     * @param idx índice a partir del cual se iniciará el desplazamiento
     */
    private void shiftLeft(final int idx) {
        --size;
        System.arraycopy(elems, idx + 1, elems, idx, size - idx);

        /* implementación imperativa (menos eficiente) */
        //for (int i = idx; i < size; i++) {
        //    elems[i] = elems[i + 1];
        //}
    }

    /**
     * Desplaza los nodos de la colección hacia la derecha.
     * <p>
     * Complejidad: O(n)
     *
     * @param idx índice a partir del cual se iniciará el desplazamiento
     */
    private void shiftRight(final int idx) {
        ++size;
        System.arraycopy(elems, idx, elems, idx + 1, size - idx);

        /* implementación imperativa (menos eficiente) */
        //for (int i = (size - 1); i >= idx; --i) {
        //    elems[i + 1] = elems[i];
        //}

    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final boolean addFirst(final E e) {
        if (e == null) {
            return false;
        } else if (isEmpty()) {
            elems[size++] = e;
            return true;
        } else if (isFull()) {
            grow();
        }

        /* desplazar los nodos originales hacia la derecha */
        shiftRight(0);

        /* Alternativa usando System.arraycopy */
        //System.arraycopy(elems, 0, elems, 1, size - 1 + 1);

        /* Insertar el elemento en el primer índice & actualizar tamaño */
        elems[0] = e;

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
        } else if (isFull()) {
            grow();
        }

        elems[size++] = e;

        return true;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public void add(final int idx, final E e) {
        checkRange(idx);

        if (isFull()) {
            grow();
        }

        /* desplazar los nodos originales hacia la derecha */
        shiftRight(idx);

        elems[idx] = e;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(n)
     */
    @Override
    public final E removeFirst() {
        if (isEmpty()) { return null; }

        final E oldVal = elems[0];

        /* desplazar los nodos originales hacia la izquierda */
        shiftLeft(0);

        /* actualizar último elemento a null */
        // FIXME :: necesario?
        elems[size] = null;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final E removeLast() {
        if (isEmpty()) { return null; }

        final E oldVal = elems[--size];
        elems[size] = null;

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
        if (isEmpty()) { return null; }

        final E oldVal = elems[idx];

        /* desplazar los nodos originales hacia la izquierda */
        shiftLeft(idx);

        /* actualizar último elemento a null */
        // FIXME :: necesario?
        elems[size] = null;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final E get(final int idx) {
        checkRange(idx);

        return elems[idx];
    }

    /**
     * Reemplaza el elemento de la colección en el índice indicado.
     * <p>
     * Complejidad: O(1)
     *
     * @param idx índice del elemento a reemplazar
     * @param e   elemento a almacenar en el índice indicado
     * @return el elemento previo a ser reemplazado
     */
    @Override
    public final E set(final int idx, final E e) {
        checkRange(idx);

        final E oldVal = elems[idx];
        elems[idx] = e;

        return oldVal;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Complejidad: O(1)
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final ArrayList<?> arr = (ArrayList<?>) o;
        return capacity == arr.capacity && size == arr.size
                && Arrays.equals(elems, arr.elems);
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
            return "[" + elems[0] + "]";
        }

        final StringBuilder str = new StringBuilder();

        str.append("[");
        for (int idx = 0; idx < size; ++idx) {
            if (idx != (size - 1)) {
                str.append(elems[idx]).append(", ");
            } else {
                str.append(elems[idx]);
            }
        }
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
            private int cursor = 0;

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            /**
             * {@inheritDoc}
             * <p>
             * Complejidad: O(1)
             */
            @Override
            public E next() {
                return elems[cursor++];
            }
        };
    }
}
