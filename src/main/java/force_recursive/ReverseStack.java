package force_recursive;

import java.util.Stack;

/**
 * 递归技法——逆序一个栈
 * @author admin
 * @date 2022/9/5 5:27
 */
public class ReverseStack {


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

        System.out.println(stack);
        System.out.println("=======逆序============");
        reverse(stack);

        System.out.println(stack);
    }

    private static void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()){
            return;
        }
        int bottomElement = popBottom(stack);
        reverse(stack);
        stack.push(bottomElement);
    }

    /**
     * 弹出栈底的元素
     * @param stack
     * @return
     */
    private static int popBottom(Stack<Integer> stack) {
        int topElement = stack.pop();
        if(stack.isEmpty()){
            return topElement;
        }else{
            int currentBottom = popBottom(stack);
            stack.push(topElement);
            return currentBottom;
        }

    }


}
