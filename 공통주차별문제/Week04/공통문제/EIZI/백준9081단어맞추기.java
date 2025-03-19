package week4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/* 백준 9081 단어 맞추기
 * 알파벳으로 만들 수 있는 단어를 사전 순으로 정렬했을 때, 그 다음에 올 단어
 * - 단어는 A~Z대문자로만 주어짐. 공백 없는 연속된 알파벳
 * - 단어의 길이는 <=100
 * 
 * (알고리즘)
 * 1. ABCD char -> 대소 비교 가능
 * 2. 다음 순행
 * 		A. i는 오른쪽에서부터 arr[i-1] < arr[i] (오른쪽에 있는 i가 더 커야 한다)
 * 		B. j는 오른쪽에서부터 arr[j] > arr[i-1] i-1보다 작은 것을 찾아야 한다
 */

public class 백준9081단어맞추기 {
	static char[] arr;
	public static boolean nextPermutation() {
		int i = arr.length-1;
		while(i>0 && arr[i-1]<arr[i]) i--;
		if (i==0) return false;
		int j = arr.length-1;
		while(j>0 && arr[j]>arr[i-1])j--;
		if (j==0) return false;
		swap(i-1, j);
		reverse(i);
		return true;
	}

	private static void reverse(int i) {
		int end = arr.length-1;
		while(i < end) {
			swap(i, end);
			i++;
			end--;
		}
		
	}

	private static void swap(int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("res/백준9081단어맞추기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			arr = br.readLine().toCharArray();
			// 오름차순이 될 때 
			nextPermutation();
			System.out.println(String.valueOf(arr));
		}
	}

}
