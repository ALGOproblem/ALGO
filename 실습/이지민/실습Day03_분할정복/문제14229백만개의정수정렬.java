package 실습.이지민.실습Day03_분할정복;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/* 공백 구분된 백만개의 정수
 * 오름차순으로 정렬한 후 배열 A에 저장하고 배열 A[500000] 을 출력하라
 * N <=1000000
 * 
 */

public class 문제14229백만개의정수정렬 {
	static int[] A;
	static int[] A_copy;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/문제14229백만개의정수정렬.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		A = new int[1000001];
		A_copy = new int[1000001];
		int end = 0;
		while (st.hasMoreTokens()) {
			A[end++] = Integer.parseInt(st.nextToken());
		}
//		mergeSort(0, end-1);
//		System.out.println(Arrays.toString(A));
		quickSort(0, end-1);
		System.out.println(A[500000]);
	}

	static void mergeSort(int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSort(start, mid);
			mergeSort(mid + 1, end);
			// 병합하는 메서드 호출
			merge(start, mid, end);
		}
	}

	static void merge(int start, int mid, int end) {
		int left = start;
		int right = mid + 1;
		int idx = start; // A_copy의 임시 저장 공간
		// 여기서 틀림
		// idx = 0으로 했다가 틀리고 while(left<=start로 했다가 틀림)
		while (left <= mid && right <= end) {
			if(A[left]<=A[right]) A_copy[idx++] = A[left++];
			else A_copy[idx++] = A[right++];
		}
		if (left<=mid) {
			for (int i = left; i<=mid; i++) {
				A_copy[idx++] = A[i];
			}
		} else {
			for (int i = right; i<=end; i++) {
				A_copy[idx++] = A[i];
			}
		}
		for (int i = start; i<=end; i++) {
			A[i] = A_copy[i];
		}//원본에 덮기
	}
	static void quickSort(int start, int end) {
		if(start<end) { // start < end조건 빼먹음 
			int pivot = partition_hore(start, end); // 파티션을 나누고 pivot의 위치를 얻음
			quickSort(start, pivot-1); // 왼쪽 부분 정렬
			quickSort(pivot+1, end); // 오른쪽 부분 정렬
		}
	}
	static int partition_hore(int start, int end) {
		int pivot = A[start]; // 여기도 틀림 pivot = start라고 했지만 pivot은 A[start]// 가장 왼쪽에 있는 것을 가운데로 하기로 결정
		int i = start+1;
		int j = end;
		while(i<=j) { // while (i<j && 이 조건 빼먹음)
			while(i <= j && A[i]<=pivot) i++; // = 조건 빼먹음
			while(i <= j && A[j]>pivot) j--; // i<=j 조건 빼먹음
			if (i < j) { // if (i < j)도 빼먹음
				int tmp = A[i];
				A[i] = A[j];
				A[j] = tmp;
			}
		}
		// 마지막에 R과 pivot의 위치를 교환
		int tmp = A[start];
		A[start] = A[j];
		A[j] = tmp;
		return j; // 피봇의 위치
	}

}
