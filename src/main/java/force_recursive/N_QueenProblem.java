package force_recursive;

/**
 * N皇后问题
 *   时间复杂度：N的N次方  N^N
 * @author admin
 * @date 2022/9/4 14:24
 */
public class N_QueenProblem {

    public static void main(String[] args) {
        System.out.println(num2(8));
    }

    public static int num1(int n){
        if(n < 1){
            return 0;
        }
        //用来存放各行的皇后分别放在哪一列的位置上
        //record[i] == j 表示第i行的皇后放在了第j列上
        int[] record = new int[n];
        return process1(0, record, n);
    }

    /**
     * 计算N皇后问题的合理摆法共有多少种
     * @param i 目前来到了第i行
     * @param record record[0...i-1]表示之前放过的皇后位置
     * @param n 整体一共有多少行
     * @return 合理的摆法有多少种
     */
    private static int process1(int i, int[] record, int n) {
        //如果来到了终止行，那么就表示该种试法是合理的
        if(i == n){
            return 1;
        }

        //子问题： 当前行各列都做一下尝试， 将各列的尝试结果做累加
        int res = 0;
        for (int j = 0; j < n; j++) {
            if(isValid(i, j, record)){
                record[i] = j;
                res += process1(i+1, record, n);
            }
        }
        return res;
    }

    /**
     * 校验第i行的皇后摆放在第j列是否合法
     * @param i
     * @param j
     * @param record 记录了0~i-1行的皇后摆法位置
     * @return 是否合法
     */
    private static boolean isValid(int i, int j, int[] record) {
        //不共行是无需校验的
        //需要检查是否跟之前的皇后是否共列、共斜线(行坐标差 == 列坐标差)
        for (int k = 0; k < i; k++) {
            if(record[k] == j || Math.abs(i-k) == Math.abs(j-record[k])){
                return false;
            }
        }
        return true;
    }


    /**
     * 使用位运算对N皇后问题进行优化（常数时间上的优化）
     * @param n  请不要超过32皇后问题
     * @return 合理的摆法数量
     */
    public static int num2(int n){
        if(n < 1 || n > 32){
            return 0;
        }
        //利用limit的右侧n位来标识皇后可以摆放的位置
        int limit = n==32 ? -1: (1<<n)-1;
        return process2(limit , 0, 0, 0);
    }

    /**
     * 计算N皇后问题合理的摆放数量
     * @param limit 区间设定
     * @param colLim 列的限制：  1的位置不能放皇后，0的位置可以放皇后
     * @param leftDiaLim 左斜线的限制：  1的位置不能放皇后，0的位置可以放皇后
     * @param rightDiaLim 右斜线的限制：  1的位置不能放皇后，0的位置可以放皇后
     * @return 合理的摆放数量
     */
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if(colLim == limit){
            return 1;
        }

        int res = 0;

        //当前可选的皇后位置, 三种限制或运算后，按位取反后，与limit求与，得到结果，结果中为1的位置表示可以尝试
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        while(pos != 0){
            //提取候选位置中最右侧的位置
            int mostRightOne = pos & (~pos+1);
            pos -= mostRightOne;
            res += process2(limit
                    , colLim | mostRightOne
                    , (leftDiaLim | mostRightOne) << 1
                    , (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;

    }
}


