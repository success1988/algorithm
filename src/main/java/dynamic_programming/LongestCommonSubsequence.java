package dynamic_programming;

/**
 * 求两个字符串的最长公共子序列的长度
 * @author admin
 * @date 2022/9/25 9:12
 */
public class LongestCommonSubsequence{

    public static void main(String[] args){
        String s1 = "khfosuoiuweorjadfs";
        String s2 = "mjvxoyhiofrewrosvsfs";

        long start1 = System.currentTimeMillis();
        int resultLength = process1(s1, s2, s1.length()-1, s2.length()-1);
        long end1 = System.currentTimeMillis();
        System.out.println("[暴力递归]两个字符串的最长公共子序列的长度为："+resultLength+",耗时："+(end1-start1));

        long start2 = System.currentTimeMillis();
        int resultLength2 = process2(s1, s2);
        long end2 = System.currentTimeMillis();
        System.out.println("[动态规划]两个字符串的最长公共子序列的长度为："+resultLength2+",耗时："+(end2-start2));


    }


    // s1[0,i] 与 s2[0,j] 最大公共子序列的长度
    private static int process1(String s1, String s2, int i, int j) {
        if(i == 0 && j == 0){
            return s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        }else if(i == 0){
            return s1.charAt(0) == s2.charAt(j) ? 1 : process1(s1, s2, i, j-1);
        }else if(j == 0){
            return s1.charAt(i) == s2.charAt(0) ? 1 : process1(s1, s2, i-1, j);
        }else{
            if(s1.charAt(i) == s2.charAt(j)){
                return 1+ process1(s1, s2, i-1, j-1);
            }else{
                return Math.max(process1(s1, s2, i-1, j), process1(s1, s2, i, j-1));
            }
        }
    }


    private static int process2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m][n];
        //对0行、0列赋值
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        //对第0行赋值
        for (int j = 1; j < n; j++) {
            dp[0][j] = s1.charAt(0) == s2.charAt(j) ? 1 : dp[0][j-1];
        }
        //对第0列赋值
        for (int i = 1; i < m; i++) {
            dp[i][0] = s1.charAt(i) == s2.charAt(0) ? 1 : dp[i-1][0];
        }
        //对一般情况
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = 1+ dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m-1][n-1];
    }



}
