package org.algos.util;

import java.util.Random;

public final class sortutils {
    private static final Random rand = new Random();

    private sortutils() { /* запрет создания экземпляров */ }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        if (i < 0 || j < 0 || i >= arr.length || j >= arr.length)
            throw new IndexOutOfBoundsException("Swap indices out of bounds");
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        // простой partition по последнему элементу
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // дополнительные проверки
    public static void guardNotNull(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Array cannot be null");
    }

    public static void guardNonEmpty(int[] arr) {
        guardNotNull(arr);
        if (arr.length == 0) throw new IllegalArgumentException("Array cannot be empty");
    }
}
