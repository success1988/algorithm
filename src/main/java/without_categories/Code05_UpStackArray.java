package without_categories;
 
import org.junit.Test;
 
import java.awt.event.ItemEvent;
import java.util.Stack;
 
/**
 * 请编写一个程序，对一个栈里的整型数据，按升序进行排序(即排序前，栈里的数据是无序的)，
 * 排序后最大元素位于栈顶，要求最多只能使用一个额外的栈存放临时数据，但不得将元素复制到别的数据结构中。
 */
public class Code05_UpStackArray {
 
    public static Stack<Integer> upStackArray(Stack<Integer> stack){
        if(stack.isEmpty()){
            return stack;
        }
        Stack<Integer> temp = new Stack<>();  //辅助栈  要保证其是从大到小的，顶层最小
        temp.push(stack.pop());
        while (!stack.isEmpty()){
           int cur = stack.pop();
           while (!temp.isEmpty() && cur > temp.peek()){  //一致弹出，直到cur小于栈顶，可以进入
               stack.push(temp.pop());
           }
           temp.push(cur);
        }
        while (!temp.isEmpty()){
            stack.push(temp.pop());
        }
        return stack;
    }
}