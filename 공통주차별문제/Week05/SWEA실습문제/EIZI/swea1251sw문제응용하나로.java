
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 가중치가 있는 그래프를 연결하라
 * 
 */

public class swea1251sw문제응용하나로 {
	static class edge implements Comparable<edge>{
		int A;
		int B;
		double cost;
		public edge() {
		}
		public edge(int a, int b, double d) {
			A = a;
			B = b;
			cost = d;
		}
		@Override
		public String toString() {
			return "edges [A=" + A + ", B=" + B + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(edge o) {
			return (int) (this.cost - o.cost);
		}
		public int getA() {
			return A;
		}
		public void setA(int a) {
			A = a;
		}
		public int getB() {
			return B;
		}
		public void setB(int b) {
			B = b;
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
		
	}
	static PriorityQueue<edge> pq;
	static int[][] arr;
	static int[] arrConnected;
	static int[] level;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/swea1251sw문제응용하나로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine()); // N개의 집합 (<=1,000,000)
			arr = new int[2][N];
			arrConnected = new int[N];
			level = new int[N];
			pq = new PriorityQueue<>();
			// 첫 번째 줄: x 좌표
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
			    arr[0][j] = Integer.parseInt(st.nextToken());
			}
			// 두 번째 줄: y 좌표
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
			    arr[1][j] = Integer.parseInt(st.nextToken());
			}
			//조합
			for (int i = 0; i<N-1; i++) {
				for (int j = i+1; j<N; j++) {
					//a, b, cost를 어떻게 계산할 것이냐?
			        // 각 섬의 좌표 접근: 
			        int ax = arr[0][i];
			        int ay = arr[1][i];
			        int bx = arr[0][j];
			        int by = arr[1][j];
					double distance = Math.pow(ax - bx, 2) + Math.pow(ay - by, 2);
					pq.offer(new edge(i, j, distance));
				}
			}//거리계산
			for (int i = 0; i<N; i++) {
				arrConnected[i] = i;
			}//자기자신연결
			int cntConnect = 1;
			double money = 0;
			while(cntConnect<N) {
				edge e = pq.poll();
				if(combine(e.A, e.B)) {
					
					money += e.cost;
					cntConnect++;
				}
			}
			double d = Double.parseDouble(br.readLine());
			System.out.println("#"+test+" "+Math.round(d*money));
		}

	}
	static int findSet(int a) {
		if(arrConnected[a]!=a) arrConnected[a] = findSet(arrConnected[a]);
		return arrConnected[a];
	}
	static boolean combine(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a==b) return false;
		if (level[a]>level[b]) arrConnected[a] = b;
		else if (level[a]<level[b]) arrConnected[b] = a;
		else arrConnected[b] = a;
		return true;
	}

}
