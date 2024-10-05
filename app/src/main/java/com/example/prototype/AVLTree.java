package com.example.prototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AVLTree<R> {
    static class AVLNode {
        int key;
        Report value;
        AVLNode left;
        AVLNode right;
        int height = 1;

        public AVLNode(int key, Report value) {
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
    private int bf(AVLNode node) {
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

    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) {
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) {
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) {
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) {
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;

    public void put(int key, Report value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Report value) {
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

    public void reverseInOrder(AVLNode node, List<Report> result) {
        if (node == null) {
            return;
        }
        reverseInOrder(node.right, result);
        result.add(node.value);
        reverseInOrder(node.left, result);
    }

    // traverse the tree from largest key to the smallest key
    public List<Report> fromLargeToSmall() {
        List<Report> result = new ArrayList<>();
        reverseInOrder(root, result);
        return result;
    }

    public void empty() {
        root = null;
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        //1.
        if (node == null) {
            return null;
        }
        //2.
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
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
                s.right = doRemove(node.right, s.key);
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
}
