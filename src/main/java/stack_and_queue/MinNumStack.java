package stack_and_queue;
 
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
 
import java.util.Stack;
 
/**
 * 实现一个特殊的栈，在实现栈的基本功能上，再实现返回栈中最小元素的操作。
 * 要求：1.pop、push、getMin操作的时间复杂度都是O(1)，2.设计的栈类型可以使用现成的栈结构。
 */
public class MinNumStack {
 
    Stack<Integer> numstack;  //数据栈
    Stack<Integer> minstack;  //最小值栈
    private int min;
 
    public MinNumStack(){
        numstack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }
 
    public Integer poll() throws Exception{
        if (!numstack.isEmpty()){
            minstack.pop();
            return numstack.pop();
        }else {
            throw new RuntimeException("空栈");
        }
    }
 
    public void push(Integer num){
        min = Math.min(min,num);
        numstack.push(num);
        minstack.push(min);
    }
 
    public Integer getMin() throws Exception{
        if(!minstack.isEmpty()){
            return minstack.peek();
        }else {
            throw new RuntimeException("栈空，没有最小值");
        }
    }
}