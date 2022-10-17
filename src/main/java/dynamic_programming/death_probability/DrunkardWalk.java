package dynamic_programming.death_probability;

/**
 * 在N行M列的坐标区域内，右上角坐标为(N-1,M-1)
 * 当前醉汉的坐标为(row,col)
 * 醉汉等概率地上下左右移动，每次移动一步，超出坐标区域时直接死亡
 * 求k步之后，醉汉还存活的概率
 * @author admin
 * @date 2022/10/7 20:46
 */
public class DrunkardWalk {


    public static void main(String[] args) {
        int N = 10;
        int M = 9;
        int row = 6;
        int col = 5;
        int k = 7;

        double aliveProbability = getAliveProbability(N, M, row, col, k);
        System.out.println("[暴力递归]醉汉还存活的概率为:"+aliveProbability);

    }

    private static double getAliveProbability(int n, int m, int row, int col, int k) {
        int aliveWays = process(n, m, row, col, k);
        return (double)aliveWays/Math.pow(4, k);
    }

    private static int process(int n, int m, int row, int col, int rest) {
        if(row < 0 || row == n || col < 0 || col == m){
            return 0;
        }
        if(rest == 0){
            return 1;
        }
        int ways = 0;
        ways += process(n, m, row+1, col, rest-1);
        ways += process(n, m, row-1, col, rest-1);
        ways += process(n, m, row, col+1, rest-1);
        ways += process(n, m, row, col-1, rest-1);
        return ways;
    }


}
