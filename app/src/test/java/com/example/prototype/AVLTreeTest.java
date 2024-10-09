package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    public void testRemove(){

    }

    @Test
    public void integratedTest1() {
        assertEquals(20, avlTree.size());
        avlTree.put(20, "twenty");
        assertEquals("twenty", avlTree.get(20));
        avlTree.remove(20);
        assertNull(avlTree.get(20));
        assertEquals(19, avlTree.size());
        avlTree.put(21, "21");
        assertEquals("21", avlTree.get(21));
        for (int i = 5; i <= 21; i++) {
            avlTree.remove(i);
        }
        for (int i = 5; i <= 21; i++) {
            assertNull(avlTree.get(i));
        }
        assertEquals(4, avlTree.size());
        assertEquals(List.of("4", "3", "2", "1"), avlTree.fromLargeToSmall());
        assertEquals(List.of("1", "2", "3", "4"), avlTree.fromSmallToLarge());
        for (int i = 0; i < 5; i++) {
            avlTree.remove(i);
        }
        assertTrue(avlTree.isEmpty());
    }


}
