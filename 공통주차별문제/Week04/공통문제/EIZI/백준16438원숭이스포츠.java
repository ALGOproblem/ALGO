
/*
 * 원숭이 스포츠
 * 1. N마리의 원숭이들이 있고 원숭이들에게 1~N 번호 붙임
 * 2. 7일간 원숭이들끼리 스포츠 경기 (A, B팀) -> 모든 원숭이 경기 참여 but 팀원 수 달라도 ok. but 최소 한 명의 원숭이
 * 3. 모든 두 원숭이에 대해서 한 번은 적으로 만나도록 대진표 구성
 */

/**헷갈렸던 점
 * 1. comb1(모든 원숭이 서로 맞대결?)과 comb2(한 팀에 적어도 하나의 원숭이?)의 탈출 조건을 명확하게 설정해야 하는데 둘을 같이 하려다가 꼬임
 * 2. 완전 탐색하면 시간 초과로 답이 안나옴.. ->  2^99 ->시간초과
 * 3. GPT -> 비트마스킹을 써라! (근데 솔직히 이해가 잘 ..)
 *      >>는 비트를 오른쪽으로 미는 것. (x>>K) & 1하면 X에서 K번째 비트가 1인지 0인지 보기
 *      
 *      A. (monkey>>day) & 1로 팀을 나누면 무슨 일이 생길까?
 *          i번 원숭이와 j번 원숭이는 둘이 binary로 표현했을 때, 다른 비트를 가진 자리가 무조건 존재함. 
 *          그걸 day가 계속 커지면서 비교하는 것. (어느 순간 반드시! 다른 자리를 가진 것을 보게 되고 팀이 달라져서 적이 됨)
 *          핵심 : 이진수로 다르게 생긴 숫자 두 개는 적어도 하나의 비트 위치가 다름. (그 자리를 기준으로 하루는 반드시 팀이 갈림)
 *          고급 : N<=99라면 숫자 99 -> 이진수 7bit 128이니깐 7일 안에 무조건 판별 가능. ! 
 * 
 *      B. 예시
            *== Day 0 (monkey >> 0) & 1 ==
            원숭이 번호 : 1 2 3 4
            이진수      : 0001 0010 0011 0100
            0번째 비트  :  1    0    1    0
            결과 팀     :  A    B    A    B
            ----------------------------
            == Day 1 (monkey >> 1) & 1 ==
            원숭이 번호 : 1 2 3 4
            이진수      : 0001 0010 0011 0100
            1번째 비트  :  0    1    1    0
            결과 팀     :  B    A    A    B
            ----------------------------
            == Day 2 (monkey >> 2) & 1 ==
            원숭이 번호 : 1 2 3 4
            이진수      : 0001 0010 0011 0100
            2번째 비트  :  0    0    0    1
            결과 팀     :  B    B    B    A
            ----------------------------
            == Day 3 (monkey >> 3) & 1 ==
            원숭이 번호 : 1 2 3 4
            이진수      : 0001 0010 0011 0100
            3번째 비트  :  0    0    0    0
            결과 팀     :  B    B    B    B (이건 무효! 전원 B)
            → 이럴 땐 무조건 한 마리를 A로 바꾸기..

 */
import java.util.Scanner;


public class 백준16438원숭이스포츠 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int day = 0; day < 7; day++) { // 7일 동안 보겠다 (= 2진수로 변환했을 때 7자리 수 안에 달라지는 수를 확인해 그 경우 팀을 다르게 배정하겠다다)
            StringBuilder sb = new StringBuilder();
            int countA = 0;
            int countB = 0;
            for (int monkey = 1; monkey <= N; monkey++) {// day비트가 1이면 A팀, 0이면 B팀에 배정하겠다
                if (((monkey >> day) & 1) == 1) {
                    sb.append('A');
                    countA++;
                } else {
                    sb.append('B');
                    countB++;
                }
            }

            // 한쪽 팀이 없는 경우 보정
            if (countA == 0) sb.setCharAt(0, 'A'); // 굳이 0번이 아니라도 다른 자리수를 바꿔도 괜찮다다
            if (countB == 0) sb.setCharAt(0, 'B');
            System.out.println(sb);
        }



        // sc.close();
        // arr = new char[7][N]; // 원숭이 배열
        // comb(0);




    }



    // static char[][] arr;
    // static int N;
    // static void comb(int i){
    //     if (i==7){
    //         if(isValidWeek()){
    //             for (int k = 0; k<7; k++){
    //                 for (int t = 0; t<N; t++){
    //                     System.out.print(arr[k][t]+" ");
    //                 }
    //                 System.out.println();
    //             }
    //             System.exit(0);// 문제에서 하나만 출력하라고 함
    //         }
    //         return;
    //     }
    //     comb2(i, 0); // 여기에서 실수함...

    // }
    // static void comb2(int i, int j){
    //     if (j==N){
    //         if (isValidTeam(i)){
    //             comb(i+1);
    //         }
    //         return;
    //     }
    //     arr[i][j] = 'A';
    //     comb2(i, j+1);
    //     arr[i][j] = 'B';
    //     comb2(i, j+1);
    // }
    // static boolean isValidTeam(int i){
    //     for (int j = 0; j<N-1; j++){
    //         if(arr[i][j]!=arr[i][j+1]){
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    // static boolean isValidWeek(){
    //     boolean[][] meet = new boolean[N][N]; // 1차원 배열을 여러번 새로 생성하면서 계산하려고 하다가 무한 재귀에 빠져버림..
    //     for (int i = 0; i<7; i++){ // 7일간의 경기 기록
    //         for (int k = 0; k<N; k++){ // 기준점 K
    //             for (int j = k+1; j<N; j++){ // K와 다른 원숭이들
    //                 if (arr[i][k]!=arr[i][j]){
    //                     meet[k][j] = true;
    //                     meet[j][k] = true;
    //                 }
    //             }
    //         }
    //     }
    //     for (int k = 0; k<N; k++){
    //         for (int j = k+1; j<N; j++){
    //             if (!meet[k][j]) return false;
    //         }
    //     }
    //     return true;
    // }
}