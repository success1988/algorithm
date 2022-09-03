package force_recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的全排列
 * @author admin
 * @date 2022/9/3 6:09
 */
public class StringAllRange {


    public static void main(String[] args) {
        String s = "abc";
        List<String> resList = new ArrayList<>();
        process(s.toCharArray(), 0, resList);
        System.out.println(resList);
    }

    /**
     * 寻找全排列
     * @param chars 字符
     * @param i 索引
     * @param res 用于存放排列结果
     */
    private static void process(char[] chars, int i, List<String> res){
        if(i == chars.length){
            res.add(String.valueOf(chars));
            return;
        }

        //这里j=i时表示不跟后边交换的情况
        //这里j>i时表示跟后边交换的情况
        for (int j = i; j < chars.length; j++) {
            swapChar(chars, i, j);
            process(chars, i+1, res);
            swapChar(chars, i, j);
        }
    }

    private static void swapChar(char[] arr, int x, int y){
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
