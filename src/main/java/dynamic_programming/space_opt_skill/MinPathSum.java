package dynamic_programming.space_opt_skill;

/**
 * 空间压缩技巧适用于邻近依赖的情况，根据依赖情况可以逆序遍历或增加临时变量
 * 1. 空间压缩技巧只是一个小技巧，不做也无伤大雅
 * 2. 空间压缩技巧可以对dp表降维，可以帮你识别大佬的“降维”装B
 * 3. 如果通过空间压缩技巧把二维降到了一维，那么可以先比较一下行和列的大小，哪个更小用哪个作为dp表
 * @author admin
 * @date 2022/10/10 9:18
 */
public class MinPathSum {

    public static void main(String[] args) {

        int[][] matrix =
                {
                    {2, 4,  7, 2,  9},
                    {1, 0,  8, 4,  11},
                    {5, 10, 3, 12, 1},
                    {6, 3,  5, 15, 7}
                };
        int minPathSum = process(matrix, matrix.length-1, matrix[0].length-1);
        System.out.println("[暴力递归]最短路径和为:"+minPathSum);

        int minPathSumDp= processDp(matrix);
        System.out.println("[动态规划]最短路径和为:"+minPathSumDp);

        int minPathSumDpSpaceOpt= processDpSpaceOpt(matrix);
        System.out.println("[动态规划-空间压缩技巧]最短路径和为:"+minPathSumDpSpaceOpt);


    }

    private static int processDpSpaceOpt(int[][] matrix) {
        int ROW = matrix.length;
        int COL = matrix[0].length;
        int[] dp = new int[COL];
        dp[0] = matrix[0][0];
        //第0行
        for(int j=1; j<COL; j++){
            dp[j] = dp[j-1] + matrix[0][j];
        }

        for (int i = 1; i < ROW; i++) {
            //对第i行第0列 专门赋值
            dp[0] += matrix[i][0];
            //对第i行第j列的一般位置赋值
            for (int j = 1; j < COL; j++) {
                dp[j] = Math.min(dp[j],dp[j-1]) + matrix[i][j];
            }
        }

        return dp[COL-1];
    }

    private static int processDp(int[][] matrix) {
        int ROW = matrix.length;
        int COL = matrix[0].length;
        int[][] dp = new int[ROW][COL];
        dp[0][0] = matrix[0][0];
        //第0行
        for(int j=1; j<COL; j++){
            dp[0][j] = dp[0][j-1] + matrix[0][j];
        }

        for (int i = 1; i < ROW; i++) {
            //对第i行第0列 专门赋值
            dp[i][0] = dp[i-1][0] + matrix[i][0];
            //对第i行第j列的一般位置赋值
            for (int j = 1; j < COL; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + matrix[i][j];
            }
        }

        return dp[ROW-1][COL-1];
    }


    /**
     * 从(0,0) 到 (row,col)的最短路径和
     * @param matrix
     * @param row
     * @param col
     * @return
     */
    private static int process(int[][] matrix, int row, int col) {
        //第0行 第0列上的位置 求和时别无选择
        if(row == 0 && col == 0){
            return matrix[0][0];
        }

        if(row == 0){
            return process(matrix, 0, col-1) + matrix[0][col];
        }

        if(col == 0){
            return process(matrix, row-1, 0) + matrix[row][0];
        }


        //一般位置，取上邻  和 左邻 的最小值, 然后加上当前位置
        return Math.min(process(matrix, row-1, col),process(matrix, row, col-1)) + matrix[row][col];
    }


}
