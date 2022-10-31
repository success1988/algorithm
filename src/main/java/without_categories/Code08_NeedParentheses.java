package without_categories;
 
/**
 * 牛牛有一个括号字符串s，现在需要在其中任意位置尽量少地添加括号，将其转化为一个完整的括号字符串请问牛牛至少需要添加多少个括号。
 */
public class Code08_NeedParentheses {
 
    public static int needParentheses(String str){
        int count = 0;
        int needSolveRight = 0;
        for(int i = 0;i < str.length();i++){
            if(str.charAt(i) == '('){
                count++;
            }else {  //遇到的是')'
                if(count == 0){ //左右括号已经匹配了，又碰到了右括号')'
                    needSolveRight++;
                }else {
                    count--;
                }
            }
        }
        return count + needSolveRight;
    }
}