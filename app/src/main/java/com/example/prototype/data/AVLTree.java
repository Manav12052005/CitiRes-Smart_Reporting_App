package com.example.prototype.data;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The implementation of AVLTree to store data.
 *
 * @param <R>
 * @author Yuan Shi u7787385
 *
 */
public class AVLTree<R> {

    class AVLNode {
        int key;
        R value;
        AVLNode left;
        AVLNode right;
        int height = 1;

        public AVLNode(int key, R value) {
            if (value == null) {
                throw new IllegalArgumentException("Value cannot be null.");
            }
            this.key = key;
            this.value = value;
        }
    }

    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * Update the height of the node after insertion, deletion and rotation
     *
     * @param node the node whose height is to be updated
     */
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * calculate the balance-factor of the node
     *
     * @param node the node to be calculated
     * @return the balance-factor
     */
    private int balance_factor(AVLNode node) {
        return height(node.left) - height(node.right);
    }

    /**
     * right rotate
     *
     * @param red the node to be right rotated
     * @return new root
     */
    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        AVLNode green = yellow.right;
        yellow.right = red;
        red.left = green;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     * left rotate
     *
     * @param red
     * @return
     */
    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;
        red.right = green;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    /**
     * balance the tree after operations
     *
     * @param node the node to start balancing
     * @return the balanced node
     */
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int comp = balance_factor(node);
        if (comp > 1 && balance_factor(node.left) >= 0) {
            return rightRotate(node);
        } else if (comp > 1 && balance_factor(node.left) < 0) {
            return leftRightRotate(node);
        } else if (comp < -1 && balance_factor(node.right) > 0) {
            return rightLeftRotate(node);
        } else if (comp < -1 && balance_factor(node.right) <= 0) {
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;

    public void put(int key, R value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, R value) {
        if (node == null) {
            return new AVLNode(key, value);
        }
        if (key == node.key) {
            node.value = value;
            return node;
        }
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        updateHeight(node);
        return balance(node);
    }

    public R get(int key) {
        R value = getRec(root, key);
        return value;
    }

    private R getRec(AVLNode node, int key) {
        if (node == null) {
            return null; // Key not found
        }
        if (key < node.key) {
            return getRec(node.left, key);
        } else if (key > node.key) {
            return getRec(node.right, key);
        } else {
            return node.value;
        }
    }

    public void reverseInOrder(AVLNode node, List<R> result) {
        if (node == null) {
            return;
        }
        reverseInOrder(node.right, result);
        result.add(node.value);
        reverseInOrder(node.left, result);
    }

    // traverse the tree from largest key to the smallest key
    public List<R> fromLargeToSmall() {
        List<R> result = new ArrayList<>();
        reverseInOrder(root, result);
        return result;
    }

    public List<R> fromSmallToLarge() {
        List<R> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(AVLNode node, List<R> result) {
        if (node == null) {
            return;
        }
        inOrder(node.left, result);
        result.add(node.value);
        inOrder(node.right, result);
    }

    public void remove(int key) {
        if (!contains(key)) {
            throw new NoSuchElementException("Key not found: " + key);
        }
        root = removeRec(root, key);
    }

    public boolean contains(int key) {
        return getRec(root, key) != null;
    }

    private AVLNode removeRec(AVLNode node, int key) {
        //1.
        if (node == null) {
            return null;
        }
        //2.
        if (key < node.key) {
            node.left = removeRec(node.left, key);
        } else if (key > node.key) {
            node.right = removeRec(node.right, key);
        } else {
            //3.
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                s.right = removeRec(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        updateHeight(node);
        return balance(node);
    }

    public int size() {
        return this.fromLargeToSmall().size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void empty() {
        root = null;
    }
}
