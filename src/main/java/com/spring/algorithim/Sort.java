package com.spring.algorithim;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author:独角兽zhuojl
 * @date :2017/12/12
 * @saysth:
 */
public class Sort {
    public static void main(String[] args) {

        System.out.println( String.format("%02d", 12));
        System.out.println(File.separator);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        Iterator<Integer> iterator = integers.iterator();
        while(iterator.hasNext()){
            iterator.remove();
        }


        int[] arr2 = {19, 13, 3, 4, 53, 5, 22, 14, 55, 43};
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * 久了不做，冒泡都要忘了
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if (null != arr) {
            int temp;
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    temp = arr[i];
                    if (arr[j] < temp) {
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    private static void selectSort(int[] arr) {
        if (null == arr) {
            return;
        }
        int index;
        int temp;
        for (int i = 0; i < arr.length; i++) {
            index = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[index] < arr[j]) {
                    index = j;
                }
            }
            if (index == i) {
                break;
            }
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertSort(int[] arr) {
        if (null == arr) {
            return;
        }
        int temp;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            int j = i;
            for (; j > 0; j--) {
                if (temp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = temp;
        }
    }

    public static void shellSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        //10,gap=5,i<2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                while (j >= gap && temp > arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (null == arr || start >= end) {
            return;
        }
        int index = quickSortDetail(arr, start, end);
        quickSort(arr, start, index - 1);
        quickSort(arr, index + 1, end);
    }

    //返回值边界的判断！
    public static int quickSortDetail(int arr[], int start, int end) {
        int i_start = start, i_end = end;
        int theData = arr[i_start];
        int temp;
        while (i_start < i_end) {
            while (arr[i_end] > theData && i_end > start) {
                i_end--;
            }
            while (arr[i_start] < theData && i_start < end) {
                i_start++;
            }
            temp = arr[i_start];
            arr[i_start] = arr[i_end];
            arr[i_end] = temp;
        }
        if (i_end < start) {
            return start;
        }
        return i_end;
    }

    public static void mergeSort(int[] arr) {

    }


}
