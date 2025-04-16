package 실습.MIRIM.실습Day03_분할정복;

import java.util.Scanner;

public class SortMilionsInteger_14229 {
    static int N = 1000000;
    static int[] arr = new int[N];
    static int[] temp = new int[N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        mergeSort(0, N - 1);
        System.out.println(arr[500000]);
    }

    static void mergeSort(int start, int end) {
        if (start >= end) return;

        int mid = (start + end) / 2;

        mergeSort(start, mid);
        mergeSort(mid + 1, end);
        merge(start, mid, end);
    }

    static void merge(int start, int mid, int end) {
        int left = start;
        int right = mid + 1;
        int idx = start;

        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                temp[idx++] = arr[left++];
            } else {
                temp[idx++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[idx++] = arr[left++];
        }

        while (right <= end) {
            temp[idx++] = arr[right++];
        }

        for (int i = start; i <= end; i++) {
            arr[i] = temp[i];
        }
    }
}
