package dynamic_programming;

/**
 * 最长回文子序列
 * 1. 原字符串与逆序后的字符串 的最大公共子序列的长度 即为结果
 * 2. 范围上做尝试
 * @author admin
 * @date 2022/9/25 12:52
 */
public class LongestPalindromicSubsequence {


    public static void main(String[] args) {
        String s = "a1ba";

        long start1 = System.currentTimeMillis();
        int resultLength = process1(s, 0, s.length()-1);
        long end1 = System.currentTimeMillis();
        System.out.println("[暴力递归]字符串的最长回文子序列的长度为："+resultLength+",耗时："+(end1-start1));

        long start2 = System.currentTimeMillis();
        int resultLength2 = process2(s);
        long end2 = System.currentTimeMillis();
        System.out.println("[动态规划]字符串的最长回文子序列的长度为："+resultLength2+",耗时："+(end2-start2));

    }


    private static int process1(String s, int L, int R) {
        if(L == R){
            return 1;
        }else{
            if(s.charAt(L) == s.charAt(R)){
                return 2 + process1(s, L+1, R-1);
            }else{
                return Math.max(process1(s, L+1, R), process1(s, L, R-1));
            }
        }
    }

    private static int process2(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }

        int n = s.length();

        //L取值范围 [0, N)
        //R取值范围 [0, N)

        int[][] dp = new int[n][n];
        //对角线都是1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        //沿着对角线遍历
        for (int i = 1; i < n; i++) {
            int L = 0;
            int R = i;
            while (R < n){
                if(s.charAt(L) == s.charAt(R)){
                    dp[L][R] = 2 + dp[L+1][R-1];
                }else{
                    dp[L][R] = Math.max(dp[L+1][R], dp[L][R-1]);
                }

                L++;
                R++;
            }
        }
        return dp[0][n-1];
    }



}
