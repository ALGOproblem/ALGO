import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 백준9081_단어맞추기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            char[] word = sc.next().toCharArray();
            if (nextPerm(word)) {
                System.out.println(String.valueOf(word));
            } else {
                System.out.println(String.valueOf(word)); // 마지막 단어면 그대로 출력
            }
        }// tc
    }// main

    // 사전 다음 단어 : 단어 뒤에서부터 오름차순 관계 하나를 내림차순으로 swap하기
    // 이때 swap할 단어가 같으면 안된다.
    // abc -> acb, abzc -> aczb
    static boolean nextPerm(char[] arr) {
        int n = arr.length;

        // 뒤에서부터 오름차순 관계 하나 찾기 -> 이때 없으면 zoo처럼 다음 단어가 없는 것
        // 1. 뒤에서부터 비교하면서 처음으로 arr[k] < arr[k + 1] 인 위치 찾기
        int k = n - 2;
        while (k >= 0 && arr[k] >= arr[k + 1]) {
            k--;
        }
        if (k < 0) return false;

        // 2. 다시 뒤에서부터 arr[k] < arr[l] 인 l 위치 찾기
        int l = n - 1;
        while (arr[k] >= arr[l]) l--;

        // 3. k와 l이랑 자리 바꾸기
        swap(arr, k, l);

        // k 이후는 내림차순인걸 1에서 확인함
        // 4. k+1부터 끝까지 뒤집기 (오름차순으로 만들기)
        reverse(arr, k + 1, n - 1);

        return true;
    }

    static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}
