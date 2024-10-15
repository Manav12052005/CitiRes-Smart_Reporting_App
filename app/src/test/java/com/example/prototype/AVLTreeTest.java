package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class AVLTreeTest {
    AVLTree<String> avlTree;

    @Before
    public void setup() {
        avlTree = new AVLTree<>();
        assertTrue(avlTree.isEmpty());
        for (int i = 1; i <= 20; i++) {
            avlTree.put(i, i + "");
        }
    }

    @Test
    public void testInitialTreeEmpty() {
        AVLTree<String> emptyTree = new AVLTree<>();
        assertTrue(emptyTree.isEmpty());
    }

    @Test
    public void testGet() {
        for (int i = 1; i <= 20; i++) {
            assertEquals("" + i, avlTree.get(i));
        }
    }

    @Test
    public void testPutNewKey() {
        assertEquals(20, avlTree.size());
        avlTree.put(21, "twenty-one");
        assertEquals("twenty-one", avlTree.get(21));
        assertEquals(21, avlTree.size());
    }

    @Test
    public void testPutExistingKey() {
        avlTree.put(1, "one");
        assertEquals("one", avlTree.get(1));
        assertEquals(20, avlTree.size());
    }

    @Test
    public void testRemoveExistingKey() {
        avlTree.remove(20);
        assertEquals(19, avlTree.size());
        assertNull(avlTree.get(20));
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertThrows(NoSuchElementException.class, () -> { avlTree.remove(25); });
        assertEquals(20, avlTree.size());
    }

    @Test
    public void testRemoveOnEmptyTree() {
        AVLTree<String> emptyTree = new AVLTree<>();
        assertThrows(NoSuchElementException.class, () -> { emptyTree.remove(1); });
        assertEquals(0, emptyTree.size());
    }


    @Test
    public void testSortedOrderFromSmallToLarge() {
        assertEquals(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"),
                avlTree.fromSmallToLarge());
    }

    @Test
    public void testSortedOrderFromLargeToSmall() {
        assertEquals(List.of("20", "19", "18", "17", "16", "15", "14", "13", "12", "11",
                        "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"),
                avlTree.fromLargeToSmall());
    }

    @Test
    public void testIsEmpty() {
        for (int i = 1; i <= 20; i++) {
            avlTree.remove(i);
        }
        assertTrue(avlTree.isEmpty());
    }

    @Test
    public void testRemoveAllAndVerifyEmpty() {
        for (int i = 1; i <= 20; i++) {
            avlTree.remove(i);
        }
        assertTrue(avlTree.isEmpty());
    }

    @Test
    public void testPutAndRemoveSequence() {
        avlTree.put(21, "twenty-one");
        assertEquals("twenty-one", avlTree.get(21));
        avlTree.remove(21);
        assertNull(avlTree.get(21));
    }

    @Test
    public void testSizeAfterPutAndRemove() {
        assertEquals(20, avlTree.size());
        avlTree.put(21, "twenty-one");
        assertEquals(21, avlTree.size());
        avlTree.remove(21);
        assertEquals(20, avlTree.size());
    }

    @After
    public void tearDown() {
        avlTree = null;
    }
}
