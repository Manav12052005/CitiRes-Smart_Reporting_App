package com.example.prototype;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {

    AVLTree<String> avlTree;

    @Before
    public void setup() {
        avlTree = new AVLTree<>();
        for (int i = 1; i <= 20; i++) {
            avlTree.put(i, i + "");
        }
    }

    @Test
    public void test() {
        assertEquals(20, avlTree.size());
    }


}
