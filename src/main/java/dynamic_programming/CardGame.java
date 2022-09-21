package dynamic_programming;

import java.util.Arrays;

/**
 * 纸牌游戏
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 *
 * 先手sente 后手gote
 *
 * @author wangcg
 * @date 2022/9/21 11:48
 */
public class CardGame {


    public static void main(String[] args) {

        int[] arr = {1,4,2,5,7,4,8,10,23,15};
        int winScore1 = win1(arr, 0, arr.length-1);
        System.out.println("暴力递归 获胜者的分数为:"+winScore1);


        int winScore2 = win2(arr, 0, arr.length-1);
        System.out.println("暴力递归+傻缓存 获胜者的分数为:"+winScore2);

        int winScore3 = win3(arr);
        System.out.println("动态规划 获胜者的分数为:"+winScore3);

    }


    //=======================================1.暴力递归===========================================

    private static int win1(int[] arr, int L, int R) {
        int firstResult = f1(arr, L, R);
        int secondResult = g1(arr, L, R);
        return Math.max(firstResult, secondResult);
    }

    private static int f1(int[] arr, int L, int R) {
        if(L == R){
            return arr[L];
        }

        int p1 = arr[L] + g1(arr, L+1, R);
        int p2 = arr[R] + g1(arr, L, R-1);
        return Math.max(p1,p2);
    }

    private static int g1(int[] arr, int L, int R) {
        if(L == R){
            return 0;
        }

        int p1 = f1(arr, L+1, R);
        int p2 = f1(arr, L, R-1);
        return Math.min(p1,p2);
    }



    //=======================================2.暴力递归+傻缓存===========================================


    private static int win2(int[] arr, int L, int R) {
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }

        int firstResult = f2(arr, L, R, fmap, gmap);
        int secondResult = g2(arr, L, R, fmap, gmap);
        return Math.max(firstResult, secondResult);
    }

    private static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if(fmap[L][R] != -1){
            return fmap[L][R];
        }

        int answer;
        if(L == R){
            answer = arr[L];
        }else{
            int p1 = arr[L] + g2(arr, L+1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R-1, fmap, gmap);

            answer = Math.max(p1,p2);
        }

        fmap[L][R] = answer;
        return answer;
    }

    private static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if(gmap[L][R] != -1){
            return gmap[L][R];
        }
        int answer;
        if(L == R){
            answer = 0;
        }else {
            int p1 = f2(arr, L+1, R, fmap, gmap);
            int p2 = f2(arr, L, R-1, fmap, gmap);

            answer = Math.min(p1,p2);
        }


        gmap[L][R] = answer;
        return answer;
    }


    //=======================================3.动态规划===========================================

    private static int win3(int[] arr) {
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        //先将对角线赋值
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }


        //外层控制沿着斜线赋值的轮数
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            //内层沿着斜线依次对 先后手 状态表进行赋值
            do{
                fmap[L][R] = Math.max(arr[L] + gmap[L+1][R], arr[R] +  gmap[L][R-1]);
                gmap[L][R] = Math.min(fmap[L+1][R], fmap[L][R-1]);

                L++;
                R++;
            }while (R < N);
        }

        printMap(fmap);
        System.out.println("---------------------");
        printMap(gmap);

        int firstResult = fmap[0][N-1];
        int secondResult = gmap[0][N-1];
        return Math.max(firstResult, secondResult);
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

}