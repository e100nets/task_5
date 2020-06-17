package ilya.ignatov;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество первых чисел Фибоначчи: ");
        int count = scanner.nextInt();
        tree.fillFibonacci(count);
    }
}

