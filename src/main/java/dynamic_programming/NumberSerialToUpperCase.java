package dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * 1对应A, 2对应B。。。26对应Z
 * 那么11可以看做是AA， 也可以看做是K
 * 现给定一个纯数字组成的字符串，要求转换为大写字母组成的字符串
 * @author admin
 * @date 2022/9/5 5:57
 */
public class NumberSerialToUpperCase {

    public static void main(String[] args) {
       String str = "11238927";
       int count = processNum(str.toCharArray(), 0);
       System.out.println("[暴力递归]组合方式共有"+count+"种");

        int count2 = processDp(str.toCharArray());
        System.out.println("[动态规划]组合方式共有"+count2+"种");

    }

    private static int processDp(char[] chars) {
        int n = chars.length;
        int[] dp = new int[n+1];
        dp[n] = 1;

        for (int i = n-1; i >=0 ; i--) {
            //i没有走到最后，说明之前的决定有问题，此走法是无效的
            if(chars[i] == '0'){
                dp[i] = 0;
            }else {
                int count = dp[i+1];
                if(i+1 < chars.length  && (chars[i] - '0')*10 + chars[i+1]-'0' <= 26){
                    count += dp[i+2];
                }
                dp[i] = count;
            }
        }

        return dp[0];
    }


    public static int processNum(char[] chars, int i){
        if(i == chars.length){
            return 1;
        }

        //i没有走到最后，说明之前的决定有问题，此走法是无效的
        if(chars[i] == '0'){
            return 0;
        }

        int count = processNum(chars, i+1);
        if(i+1 < chars.length  && (chars[i] - '0')*10 + chars[i+1]-'0' <= 26){
            count += processNum(chars, i+2);
        }
        return count;
    }
}
