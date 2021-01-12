package leetcode.java;

import java.util.Arrays;

public class sort1 {

    /**
     * 冒泡排序
     */
    public static int[] BubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    swag(arr, j);
                }
            }

        }
        return arr;
    }

    private static void swag(int[] arr, int j) {
        int temp = arr[j + 1];
        arr[j + 1] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] a1 = {10, 2, 3, 8, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(BubbleSort(a)));

        System.out.println(Arrays.toString(BubbleSort(a1)));

    }
}
