package com.example.prototype;

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

