package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.prototype.data.AVLTree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Unit tests for AVLTree using String to replace R.
 * @author Yuan Shi u7787385
 */
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
    public void testEmptyTree() {
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
    public void testContain() {
        for (int i = 1; i <= 20; i++) {
            assertTrue(avlTree.contains(i));
        }
    }

    @Test
    public void testInsertNewKey() {
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
        assertNull(avlTree.get(20));
        assertEquals(19, avlTree.size());
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertThrows(NoSuchElementException.class, () -> {
            avlTree.remove(25);
        });
        assertEquals(20, avlTree.size());
    }

    @Test
    public void testRemoveOnEmptyTree() {
        AVLTree<String> emptyTree = new AVLTree<>();
        for (int i = 0; i < 100; i++) {
            int var = i;
            assertThrows(NoSuchElementException.class, () -> {
                emptyTree.remove(var);
            });
        }
        assertEquals(0, emptyTree.size());
    }

    @Test
    public void testSortedFromLargeToSmall() {
        assertEquals(List.of("20", "19", "18", "17", "16", "15", "14", "13", "12", "11",
                        "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"),
                avlTree.fromLargeToSmall());
    }

    @Test
    public void testSortedFromSmallToLarge() {
        assertEquals(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"),
                avlTree.fromSmallToLarge());
    }

    @Test
    public void testIsEmptyAfterRemoval() {
        for (int i = 1; i <= 20; i++) {
            avlTree.remove(i);
        }
        assertTrue(avlTree.isEmpty());
    }

    @Test
    public void testPutAndRemove() {
        avlTree.put(21, "twenty-one");
        assertEquals(21, avlTree.size());
        assertEquals("twenty-one", avlTree.get(21));
        avlTree.remove(21);
        assertNull(avlTree.get(21));
        assertEquals(20, avlTree.size());
    }

    @Test
    public void testLargeNumber() {
        for (int i = 0; i < 5000000; i++) {
            avlTree.put(i, i + "");
        }
        assertEquals(5000000, avlTree.size());
        for (int i = 0; i < 5000000; i++) {
            assertEquals(i+"", avlTree.get(i));
        }
    }

    @After
    public void tearDown() {
        avlTree = null;
    }
}
