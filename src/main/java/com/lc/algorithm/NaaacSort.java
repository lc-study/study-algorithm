package com.lc.algorithm;

public class NaaacSort {
    /**
     * 冒泡排序
     *
     * @param array 待排序数组
     */
    public void bubbleSort(int[] array) {
        boolean change;
        int length = array.length;
        do {
            change = false;
            length -= 1;
            for (int i = 0; i < length; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    change = true;
                }
            }
        } while (change);
    }

    /**
     * 插入排序
     *
     * @param array 待排序数组
     */
    public void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }


    /**
     * 快速排序（划分交换排序）
     * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。
     * 步骤为：
     * 1.从数列中挑出一个元素，称为“基准”（pivot）;
     * 2.重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任何一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * 3.递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * 注：递归到最底部时，数列的大小是零或一，也就是已经排序好了。这个算法一定会结束，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
     *
     * @param array
     * @param head
     * @param tail
     */
    public void quickSort(int[] array, int head, int tail) {
        if (head >= tail || array == null || array.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = array[(head + tail) / 2];
        while (i <= j) {
            while (array[i] < pivot) {
                ++i;
            }
            while (array[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        quickSort(array, head, j);
        quickSort(array, i, tail);
    }

    /**
     * 归并排序（递归版）: 文档里的图理解
     *
     * @param arr
     * @param result
     * @param start
     * @param end
     */
    public void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end - start;
        int mid = (len >>> 1) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
    }

    /**
     * 归并排序（迭代版）
     *
     * @param arr
     */
    public void mergeSortIteration(int[] arr) {
        int[] orderedArr = new int[arr.length];
        for (int i = 2; i < arr.length * 2; i *= 2) {
            for (int j = 0; j < (arr.length + i - 1) / i; j++) {
                int left = i * j;
                int mid = left + i / 2 >= arr.length ? (arr.length - 1) : (left + i / 2);
                int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (arr[l] < arr[m]) {
                        orderedArr[start++] = arr[l++];
                    } else {
                        orderedArr[start++] = arr[m++];
                    }
                }
                while (l < mid) {
                    orderedArr[start++] = arr[l++];
                }
                while (m <= right) {
                    orderedArr[start++] = arr[m++];
                }
                System.arraycopy(orderedArr, left, arr, left, right - left + 1);
            }
        }
    }
}
