package force_recursive;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 *   子串中的字符在字符串中都是连续在一起
 *   但是子序列中的字符在字符串中不一定是连在一起的
 * @author wangcg
 * @create 2022/9/1 9:49
 */
public class FindSubSerial {

    public static void main(String[] args) {
        String str = "abcde";
        //printSubSerial(str.toCharArray(),0);
        printSubSerial2(str, 0, "");
    }


    private static void printSubSerial(char[] chars, int i){
        if(i == chars.length){
            System.out.println(String.valueOf(chars));
            return;
        }
        char currentChar = chars[i];
        //要i的情况
        printSubSerial(chars, i+1);
        //不要i的情况  0表示空格（在控制台上输出为空字符）
        chars[i] = 0;
        printSubSerial(chars, i+1);
        chars[i] = currentChar;
    }


    private static void printSubSerial2(String str, int i, String res){
        if(i == str.length()){
            System.out.println(res);
            return;
        }
        //要i的情况
        printSubSerial2(str, i+1, res+str.charAt(i));
        //不要i的情况  0表示空字符
        printSubSerial2(str, i+1, res);
    }
}