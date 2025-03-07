package 실습Day01_부분집합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 정수 N, M 이 주어질 때, M의 이진수 표현의 마지막 N 비트가 모두 1로 켜져 있는지 아닌지를 판별하여 출력하라.
 */

public class 문제10726이진수표현 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/문제10726이진수표현.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int N = Integer.parseInt(st.nextToken()); // 1<=N<=30 뒤에서부터 모두 1이어야 하는 비트의 개수
			int M = Integer.parseInt(st.nextToken()); // 1<=M<=10^8 숫자
			String m = Integer.toBinaryString(M);
			boolean yes = true;
			if (m.length() < N) yes = false;
			else {	
				for (int i = m.length()-N; i<m.length(); i++) {
					if (m.charAt(i) != '1' ) {
						yes = false;
						break;
					}
				}
			}
			if (yes) System.out.println("#"+test_case+" ON");
			else System.out.println("#"+test_case+" OFF");

		}

	}

}
