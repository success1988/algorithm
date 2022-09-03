package greedy;

import java.util.Arrays;

/**
 * 字符串拼接字典序最小
 * @author admin
 * @date 2022/9/3 21:38
 */
public class MinAlphabetOrder {


    public static String minAlphabetOrder(String[] strArr){
        if(strArr == null || strArr.length == 0){
            return "";
        }

        Arrays.sort(strArr, (x,y) -> (x+y).compareTo(y+x));
        String result = "";
        for (int i = 0; i < strArr.length; i++) {
            result += strArr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        String[] arr = {"bcs","asc","abc","b","cda","bbc","ba"};
        System.out.println(minAlphabetOrder(arr));
    }


}
