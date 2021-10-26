package com.dbikzigitov.Task2Test;

import com.dbikzigitov.task2.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> testList;
    private DoublyLinkedList<Integer> emptyList;
    private int testSize;

    @BeforeEach
    public void setUp() {
        testList = new DoublyLinkedList<>();
        emptyList = new DoublyLinkedList<>();
        testSize = 3;
        for (int i = 0; i < testSize; i++) {
            testList.add(i);
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyList.isEmpty());
        assertFalse(testList.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(testList.size(), testSize);
    }

    @Test
    public void testAddNull() {
        assertFalse(testList.add(null));
    }

    @Test
    public void testGetFromEmptyList() {
        assertNull(emptyList.get(0));
    }

    @Test
    public void testAddByIndex() {
        assertFalse(testList.add(null, 0));
        assertFalse(testList.add((Integer) 3, -2));
        assertTrue(testList.add((Integer) 3, 3));
    }

    @Test
    public void testContains() {
        assertTrue(testList.contains(testSize - 2));
    }

    @Test
    public void testContainsNull() {
        assertFalse(testList.contains(null));
    }

    @Test
    public void testRemove() {
        assertEquals(testList.remove(0), 0);
        assertEquals(testList.remove(testSize - 2), testSize - 1);
        assertNull(testList.remove(testSize));
    }

    @Test
    public void testIterator() {
        assertThrows(UnsupportedOperationException.class, () -> testList.iterator());
    }

    @Test
    public void testToArray() {
        assertThrows(UnsupportedOperationException.class, () -> testList.toArray());
    }

    @Test
    public void testContainsAll() {
        assertThrows(UnsupportedOperationException.class, () -> testList.containsAll(emptyList));
    }

    @Test
    public void testAddAll() {
        assertThrows(UnsupportedOperationException.class, () -> testList.addAll(emptyList));
    }

    @Test
    public void testAddAllByIndex() {
        assertThrows(UnsupportedOperationException.class, () -> testList.addAll(0, emptyList));
    }

    @Test
    public void testRemoveAll() {
        assertThrows(UnsupportedOperationException.class, () -> testList.removeAll(emptyList));
    }

    @Test
    public void testRetainAll() {
        assertThrows(UnsupportedOperationException.class, () -> testList.retainAll(emptyList));
    }

    @Test
    public void testClear() {
        assertThrows(UnsupportedOperationException.class, () -> testList.clear());
    }

    @Test
    public void testListIterator() {
        assertThrows(UnsupportedOperationException.class, () -> testList.listIterator());
    }

    @Test
    public void testListIteratorByIndex() {
        assertThrows(UnsupportedOperationException.class, () -> testList.listIterator(0));
    }

    @Test
    public void testSubList() {
        assertThrows(UnsupportedOperationException.class, () -> testList.subList(0, testSize));
    }
}