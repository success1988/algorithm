package stack_and_queue;

import java.util.Stack;

/**
 * @author admin
 * @date 2022/10/31 23:21
 */
public class Computer {
    //每次计算，遇到括号，就把之前的结果压入栈，再压入括号之前的符号，
//括号结束，出栈，把 之前的结果 和 当前计算的结果 * 符号 加起来
    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<Integer>();

        int sign = 1;  // sign 代表正负
        int res = 0;  //存放结果
        int i = 0;//索引
        while (i < s.length()){
            if (s.charAt(i) == ' '){
                i++;
            }else if(s.charAt(i) == '+'){
                sign = 1;
                i++;
            }else if (s.charAt(i) == '-'){
                sign = -1;
                i++;
            }else if(s.charAt(i) == '('){
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
                i++;
            }else if (s.charAt(i) == ')'){
                res = stack.pop() * res + stack.pop(); //前一个为符号数，后一个为括号之前的结果
                i++;
            }else {
                int num = s.charAt(i) - '0';
                i++;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                res += sign * num;
            }
        }
        return res;
    }
}
