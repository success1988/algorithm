package dynamic_programming;

import java.util.Arrays;

/**
 * 假设有排成一行的N个位置，记为1~N，（N>=2）,开始时机器人在start位置，有如下约束
 *
 * 机器人在1位置，下一步只能走到2位置
 * 机器人在N位置，下一步只能走到N-1位置
 * 机器人在其他位置，下一步能走左边，也能走右边
 * 求机器人从start位置经过k步到达target位置的方法数。
 * @author wangcg
 * @date 2022/9/19 19:36
 */
public class RobotMovement {

    public static void main(String[] args) {
        int N = 7;
        int K = 4;
        int start = 2;
        int target = 4;
        //方法1：暴力递归
        int wayNum1 = process1(start, K, target, N);
        System.out.println("暴力递归走法数目为:"+wayNum1);

        //方法2：暴力递归+记忆表(傻缓存)
        int wayNum2 = process2wrap(start, K, target, N);
        System.out.println("暴力递归+记忆表(缓存)走法数目为:"+wayNum2);

        //方法3：动态规划
        int wayNum3 = process3(start, K, target, N);
        System.out.println("动态规划走法数目为:"+wayNum3);
        

    }

    private static int process1(int curr, int rest, int target, int n) {
        if(rest == 0){
            return curr == target ? 1 : 0;
        }
        if(curr == 1){
            return process1(2, rest-1,  target, n);
        }else if(curr == n){
            return process1(n-1, rest-1,  target, n);
        }else {
            return process1(curr-1, rest-1,  target, n) + process1(curr+1, rest-1,  target, n);
        }
    }


    private static int process2wrap(int start, int K, int target, int n) {
        int[][] cache = new int[n+1][K+1];
        //初始化缓存表的值，用-1来表示没有计算过
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= K; j++) {
                cache[i][j] = -1;
            }
        }
        return process2(start, K, target, n, cache);

    }

    private static int process2(int curr, int rest, int target, int n, int[][] cache) {
        if(cache[curr][rest] != -1){
            return cache[curr][rest];
        }

        int answer;
        if(rest == 0){
            answer = (curr == target ? 1 : 0);
        }
        if(curr == 1){
            answer =  process1(2, rest-1,  target, n);
        }else if(curr == n){
            answer =  process1(n-1, rest-1,  target, n);
        }else {
            answer =  process1(curr-1, rest-1,  target, n) + process1(curr+1, rest-1,  target, n);
        }
        cache[curr][rest] = answer;
        return answer;
    }


    private static int process3(int start, int K, int target, int n) {
        int[][] answerTable = new int[n+1][K+1];
        answerTable[target][0] = 1;
        for (int i = 1; i <= K; i++) {
            answerTable[1][i] = answerTable[2][i-1];
            for (int j = 2; j <= n-1 ; j++) {
                answerTable[j][i] = answerTable[j-1][i-1]+answerTable[j+1][i-1];
            }
            answerTable[n][i] = answerTable[n-1][i-1];
        }
        printAnswerTable(answerTable);
        return answerTable[start][K];
    }

    private static void printAnswerTable(int[][] answerTable) {
        for (int i = 0; i < answerTable.length; i++) {
            System.out.println(Arrays.toString(answerTable[i]));
        }
    }
}