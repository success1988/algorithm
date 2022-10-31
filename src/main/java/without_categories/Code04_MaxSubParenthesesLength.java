package without_categories;
 
import org.junit.Test;
 
import java.util.Arrays;
 
/**
 * 最大的子括号字符串的长度
 */
public class Code04_MaxSubParenthesesLength {
 
 
    public static int getMaxSubParentesesLength(String str){
        if (str == null || str == ""){
            return 0;
        }
        int N = str.length();
        char[] chars = str.toCharArray();
        int[] maxLengthArr = new int[N];    // 记录以每个位置结尾的最大合法子括号字符串长度
        maxLengthArr[0] = 0;  //第一个位置肯定是0，该位置左侧什么都没有，无法构成
        // 以( 结尾肯定是 0
        int maxLength = Integer.MIN_VALUE;
        for(int i = 1;i < N;i++){
            if(chars[i] == '('){
                maxLengthArr[i] = 0;
            }else {
                //     ( ( ) ( ) ) ( ) ( )
                //     0 1 2 3 4 5 6 7 8 9
                //dp[] 0 0 2 0 4 6 0 8 0 10
                //   当前在i位置，其为 ) ,要知道i-1位置的括号长度x之前的一位是不是 (，
                //  即 i-1-x 的位置是不是 ),
                //  是的话至少长度位i-1位置的长度加2，并且要加(i-1-x)-1的长度(因为前面可能也是连续的并结束的)
                int cur = maxLengthArr[i - 1]; //获得前一个位置的合法括号长度
                if (i - 1 - cur - 1 >= 0 && chars[i - 1 - cur] == '('){
                    maxLengthArr[i] = maxLengthArr[i - 1] + 2 + maxLengthArr[i - 1 - cur - 1] ;
                }else{
                    maxLengthArr[i] = 0;
                }
            }
            maxLength = Math.max(maxLength,maxLengthArr[i]);
        }
        System.out.println(Arrays.toString(maxLengthArr));
        return maxLength;
    }
 
    @Test
    public void test(){
        String str = "()())(()())()))";
        System.out.println(getMaxSubParentesesLength(str));
 
    }
}