package dynamic_programming;

/**
 * 跳马问题
 * 棋盘9*10， 右上角的坐标为(8,9), 现在从(0,0) 到 (a,b) 跳K步到达，共有多少种跳法
 * @author wangcg
 * @date 2022/9/26 10:29
 */
public class HorseJump {

    public static void main(String[] args) {
        int a = 8;
        int b = 9;
        int K = 7;
        int ways = process1(a,b,K,0,0);
        System.out.println("【暴力递归】跳法共有："+ways);

        int ways2 = process2(a,b,K);
        System.out.println("【动态规划】跳法共有："+ways2);
    }


    private static int process1(int a, int b, int rest, int x, int y) {
        if(x<0 || x>8 || y<0 || y>9){
            return 0;
        }
        if(rest == 0){
            //当步数用完时，判断是否抵达了目标位置
            return (x==a && y==b) ? 1 : 0;
        }

        int ways = 0;
        //(x,y)可以从8个位置跳向目标位置，需要对8种情况的跳法求和
        ways += process1(a, b, rest-1, x-1, y-2);
        ways += process1(a, b, rest-1, x-2, y-1);
        ways += process1(a, b, rest-1, x-2, y+1);
        ways += process1(a, b, rest-1, x-1, y+2);
        ways += process1(a, b, rest-1, x+1, y+2);
        ways += process1(a, b, rest-1, x+2, y+1);
        ways += process1(a, b, rest-1, x+2, y-1);
        ways += process1(a, b, rest-1, x+1, y-2);

        return ways;
    }



    private static int process2(int a, int b, int k) {

        //构建一个三维数组
        int[][][] dp = new int[9][10][k+1];
        //x, y 是二维平面， z轴是高度
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 10; y++) {

                    int ways = 0;
                    //(x,y)可以从8个位置跳向目标位置，需要对8种情况的跳法求和
                    ways += filterWay(dp, rest-1, x-1, y-2);
                    ways += filterWay(dp, rest-1, x-2, y-1);
                    ways += filterWay(dp, rest-1, x-2, y+1);
                    ways += filterWay(dp, rest-1, x-1, y+2);
                    ways += filterWay(dp, rest-1, x+1, y+2);
                    ways += filterWay(dp, rest-1, x+2, y+1);
                    ways += filterWay(dp, rest-1, x+2, y-1);
                    ways += filterWay(dp, rest-1, x+1, y-2);

                    dp[x][y][rest] = ways;
                }
            }
        }

        return dp[0][0][k];
    }

    private static int filterWay(int[][][] dp, int rest, int x, int y){
        if(x<0 || x>8 || y<0 || y>9){
            return 0;
        }
        return dp[x][y][rest];
    }


}