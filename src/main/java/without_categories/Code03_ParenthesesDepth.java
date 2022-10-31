package without_categories;

import org.junit.Test;

import java.util.Arrays;


/**
 * 牛牛现在给你一个合法的括号序列，需要你计算出其深度。
 * ①空串””的深度是0
 * ②如果字符串”X”的深度是x，字符串”Y”的深度是y，那么字符串”XY”的深度为max(x,y)
 * ③如果”X”的深度是x，那么字符串”(X)”的深度是x+1
 */
public class Code03_ParenthesesDepth {
 
    //str 是给定的一个合法的括号序列
    // 返回该括号序列的深度  相当于就是得到该序列的最大括号嵌套
    public static int parenthesesDepth(String str) {
        if (str == null) {
            return -1;
        }
        if (str == "") {
            return 0;
        }
        char[] chars = str.toCharArray();
        int maxDepth = Integer.MIN_VALUE;
        int count = 0;
        //遇到左括号就加，右括号就减，最大嵌套深度每次都要和count比较
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                count++;
                maxDepth = Math.max(maxDepth, count);
            } else {
                count--;
            }
        }
        return maxDepth;
    }
}