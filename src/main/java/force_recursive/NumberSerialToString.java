package force_recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 1对应A, 2对应B。。。26对应Z
 * 那么11可以看做是AA， 也可以看做是K
 * 现给定一个纯数字组成的字符串，要求转换为大写字母组成的字符串
 * @author admin
 * @date 2022/9/5 5:57
 */
public class NumberSerialToString {

    /**
     * 大写字母与数字（3~9）之间的偏移量
     */
    private static final int OFFSET_NUMBER_LETTER_3_9 = 'A'-'1';
    private static final int OFFSET_NUMBER_LETTER_TEN = 'J'-'0';
    private static final int OFFSET_NUMBER_LETTER_TWENTY = 'Z'-'6';

    public static void main(String[] args) {
       String str = "11238927";
       List<String> res = new ArrayList<>();
       process(str.toCharArray(), 0, res);
       System.out.println("组合方式详情:"+res);

       int count = processNum(str.toCharArray(), 0);
       System.out.println("组合方式共有"+count+"种");
    }


    public static void process(char[] chars, int i, List<String> res){
        if(i == chars.length){
            res.add(String.valueOf(chars));
            return;
        }

        char currChar = chars[i];
        boolean isEnd = (i == chars.length-1);
        switch (currChar){
            case '0':
                break;
            case '1':
                chars[i] = 'A';
                process(chars, i+1, res);
                chars[i] = currChar;
                if(!isEnd){
                    char nextChar = chars[i+1];
                    chars[i] = 0;
                    chars[i+1] = (char) (nextChar+OFFSET_NUMBER_LETTER_TEN);
                    process(chars, i+2, res);
                    chars[i] = currChar;
                    chars[i+1] = nextChar;
                }
                break;
            case '2':
                chars[i] = 'B';
                process(chars, i+1, res);
                chars[i] = '2';
                if(!isEnd){
                    char nextChar = chars[i+1];
                    if(nextChar <= '6'){
                        chars[i] = 0;
                        chars[i+1] = (char) (nextChar+OFFSET_NUMBER_LETTER_TWENTY);
                        process(chars, i+2, res);
                        chars[i] = currChar;
                        chars[i+1] = nextChar;
                    }
                }
                break;
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                chars[i] = (char) (currChar+OFFSET_NUMBER_LETTER_3_9);
                process(chars, i+1, res);
                chars[i] = currChar;
                break;
        }
    }


    public static int processNum(char[] chars, int i){
        if(i == chars.length){
            return 1;
        }

        int count = 0;
        char currChar = chars[i];
        boolean isEnd = (i == chars.length-1);
        switch (currChar){
            case '0':
                break;
            case '1':
                if(!isEnd){
                   count = processNum(chars, i+1) +  processNum(chars, i+2);
                }else {
                   count = processNum(chars, i+1);
                }
                break;
            case '2':
                if(!isEnd && chars[i+1] <= '6'){
                    count = processNum(chars, i+1) +  processNum(chars, i+2);
                }else {
                    count = processNum(chars, i+1);
                }
                break;
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                count =  processNum(chars, i+1);
                break;
        }
        return count;
    }
}
