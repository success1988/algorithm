package stack_and_queue;

import java.util.Stack;
/**
 * @author admin
 * @date 2022/10/31 22:48
 */
public class QueueByStack<E> {

        private Stack<E> in=new Stack<>();    //入队栈
        private Stack<E> out=new Stack<>();  //出队栈

        //入队方法
        public void offer(E e) {
            while(!out.isEmpty()) {   //判断出堆栈是否为空
                in.push(out.pop());	//出队栈中的元素依次出栈并压入入堆栈中
            }
            in.push(e);   //将元素添加到入堆栈
        }

        //出栈方法
        public E poll() {
            while(!in.isEmpty()) {     //判断入堆栈是否为空
                out.push(in.pop());   //入队栈中的元素依次出栈并压入出堆栈中
            }
            return out.pop();   //出堆栈栈顶元素出栈
        }

        //判断队列是否为空
        public boolean isEmpty() {
            return in.size() ==0 && out.size() == 0;
        }
}
