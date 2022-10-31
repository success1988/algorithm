package stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author admin
 * @date 2022/10/31 22:48
 */
public class StackByQueue {

        Queue<Integer> queue1;  // 和栈中保持一样元素的队列
        Queue<Integer> queue2; // 辅助队列

        public StackByQueue() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }


        public void push(int x) {
            queue2.offer(x); // 先放进辅助队列中
            while (!queue1.isEmpty()){
                queue2.offer(queue1.poll());
            }
            Queue<Integer> queueTemp = queue1;
            queue1 = queue2;
            queue2 = queueTemp;  // 最后交换queue1和queue2， 将元素都放到queue1
        }


        public int pop() {
            return queue1.poll();  // 因为queue1中的元素和栈中保持一致，所以这个和下面两个的操作只看queue1即可

        }

        public int top() {
            return queue1.peek();

        }

        public boolean empty() {
            return queue1.isEmpty();
        }
}
