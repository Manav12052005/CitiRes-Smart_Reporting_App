package com.example.prototype.data;

import com.example.prototype.entity.Report;

/**
 * This class is intended to hold one instance of the AVLTree.
 * The instance was previously in MainActivity, but later moved to DataHolder. This was done
 * to increase accessibility for other activities.
 * Part of Feature - Tree Implementation
 * @author Manav Singh - This class - u7782612
 * @author Yuan - Made this into singleton DP*/

public class DataHolder {
    public static AVLTree<Report> avlTree = new AVLTree<>();
    private static DataHolder instance = null;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }
}

