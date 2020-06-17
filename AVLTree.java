package ilya.ignatov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AVLTree {

    private class AVLTreeNode {
        private AVLTreeNode left;
        private AVLTreeNode right;
        private int value;
        private int height;

        private AVLTreeNode(AVLTreeNode left, AVLTreeNode right, int value, int height) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.height = height;
        }

        private AVLTreeNode(int value) {
            this(null, null, value, 0);
        }

        public void recalcHeight() {
            height = Math.max((left == null ? -1 : left.height), (right == null ? -1 : right.height)) + 1;
        }

        public int getHeightDiff() {
            return (left == null ? -1 : left.height) - (right == null ? -1 : right.height);
        }
    }

    private AVLTreeNode root = null;

    public void fillFibonacci (int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int count1 = 1, count2 = 0;
        int fibonacci;
        list.add(count1);
        for (int i = 0; i < k-1; i++) {
            fibonacci = count1 + count2;
            count2 = count1;
            count1 = fibonacci;
            list.add(fibonacci);
        }
        Integer[] array1 =  list.toArray(new Integer[list.size()]);
        Integer[] array2 = new Integer[array1.length];
        System.arraycopy(array1, 0, array2, 0, array1.length);
        Arrays.sort(array2, Collections.reverseOrder());

        for(int i = 0; i <array2.length; i++) {
            for (int j = 0; j < array1[i]; j++) {
                add(array2[i]);
            }
        }
    }

    private AVLTreeNode put(AVLTreeNode current, int value) {
        if (current == null) {
            return new AVLTreeNode(value);
        }
        if (current.value < value / 2) {
            current.left = put(current.left, value);
        } else {
            current.right = put(current.right, value);
        }
        current.recalcHeight();
        current = balance(current);
        return current;
    }

    public void add(int value) {
        root = put(root, value);
    }

    private AVLTreeNode balance(AVLTreeNode node) {
        if (node == null) {
            return node;
        }
        if (node.getHeightDiff() < -1) {
            if (node.right != null && node.right.getHeightDiff() > 0) {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            } else {
                node = leftRotate(node);
            }
        } else if (node.getHeightDiff() > 1) {
            if (node.left != null && node.left.getHeightDiff() < 0) {
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            } else {
                node = rightRotate(node);
            }
        }
        return node;
    }

    private AVLTreeNode leftRotate(AVLTreeNode node) {
        AVLTreeNode right = node.right;
        node.right = right.left;
        right.left = node;
        node.recalcHeight();
        right.recalcHeight();
        return right;
    }

    private AVLTreeNode rightRotate(AVLTreeNode node) {
        AVLTreeNode left = node.left;
        node.left = left.right;
        left.right = node;
        node.recalcHeight();
        left.recalcHeight();
        return left;
    }
}