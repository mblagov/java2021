package com.dbikzigitov.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;


/**
 * Queue on DoublyLinkedList.
 *
 * @param <E> - item
 */
public class QueueOnDoublyLinkedList<E> implements Queue<E> {

    private List<E> items;

    public QueueOnDoublyLinkedList() {
        items = new DoublyLinkedList<>();
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public E poll() {
        return items.remove(0);
    }

    public boolean add(E item) {
        return items.add(item);
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException("This operation is unsupported!");
    }
}
