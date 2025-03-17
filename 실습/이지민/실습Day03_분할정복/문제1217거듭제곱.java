package 실습.이지민.실습Day03_분할정복;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 문제1217거듭제곱 {
	static int[] arr;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/문제1217거듭제곱.txt"));
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i<10; i++) {
			int test = sc.nextInt();
			int A = sc.nextInt();
			int N = sc.nextInt();
			arr = new int[N];
			int answer = pow(A, N);
			System.out.println("#"+test+" "+answer);
		}
	}
	public static int pow(int A, int N) {
		if (N==0) return 1;
		if (N%2 == 0) {
			int tmp = pow(A, N/2);
			return tmp*tmp;
		} else {
			int tmp = pow(A, (N-1)/2);
			return tmp*tmp*A;
		}
	}

}
