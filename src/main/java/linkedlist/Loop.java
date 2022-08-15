package linkedlist;

/**
 * https://blog.csdn.net/Lvxueqinga/article/details/124548950
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null。
 * 【要求】如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)
 * @Author admin
 * @Date 2022/8/15
 *
 * 判断是否有环：快慢指针是否会相遇
 * 找到入环节点：在快慢指针第一次相遇处，让慢指针继续，同时再用另外一个慢指针从head出发，这两个慢指针相遇处就是入环节点
 *
 * 分类讨论两个单链表相交的情况：
 * 两个单链表都是无环，那么如果相交，则会呈现“Y”型
 * 两个单链表一个无环一个有环，是不可能相交的
 * 两个单链表都是有环，那么如果相交，可能是在入环前相交(具有相同的入环点)，也可能是在入环后相交(具有不同的入环点)
 */
public class Loop {



    public static Node getFirstCommonNode(Node head1, Node head2){
        Node in1 = getLoop(head1);
        Node in2 = getLoop(head2);

        if(in1 == null && in2 == null){
            return noloop(head1, head2);
        }else if(in1 != null && in2 != null){
            return getIntersection(head1,in1,head2,in2);
        }else{
            return null;
        }
    }


    private static Node getLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node fast = head.next.next;
        Node slow = head.next;

        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        //快慢指针相遇后， 其中一个从头开始走， 每次一步， 两个节点再次相遇时则为入环点
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;

        }
        return slow;
    }


    /**
     * 两个单链表都是无环，那么如果相交，则会呈现“Y”型
     * @param head1
     * @param head2
     * @return
     */
    private static Node noloop(Node head1, Node head2){
        int lencha = 0; //链表1和链表2的长度差

        //循环链表1
        Node start1 = head1;
        while (start1.next!=null){
            lencha ++;
            start1 = start1.next;
        }


        //循环链表2
        Node start2 = head2;
        while (start2.next!=null){
            lencha --;
            start2 = start2.next;
        }

        //如果两个无环单链表相交，那么最后一个节点必然是同一个节点（内存地址相同）
        if (start1!=start2){
            return null;
        }

        //currA 代表长的链表， currB指向短的链表
        Node currA ;
        Node currB ;
        currA = lencha >0 ? head1 : head2;
        currB = currA==head1? head2: head1;
        lencha = Math.abs(lencha);
        //因为是同一个尾节点，所以长的 先走 两个链表长度差 步
        while (lencha>0){
            lencha--;
            currA = currA.next;
        }
        //A和B一起往后走，直到遇到一个相等的
        while (currA!=currB){
            currA = currA.next;
            currB = currB.next;
        }
        return currA;
    }


    /**
     * 两个单链表都是有环，那么如果相交，可能是在入环前相交(具有相同的入环点)，也可能是在入环后相交(具有不同的入环点)
     * @param head1
     * @param in1
     * @param head2
     * @param in2
     * @return
     */
    private static Node getIntersection(Node head1, Node in1, Node head2, Node in2){

        if (in1 == in2){
            // （情况1）入环点相同,则同两无环链表取交点
            Node curr1 = head1;
            Node curr2 = head2;
            int lenn = 0;
            while (curr1.next != in1){
                lenn ++;
                curr1 = curr1.next;
            }
            while (curr2.next != in2){
                lenn --;
                curr2 = curr2.next;
            }
            curr1 = lenn > 0 ? head1 : head1;
            curr2 = curr1 == head1 ? head2 : head1;
            lenn = Math.abs(lenn);
            while (lenn!=0){
                curr1 = curr1.next;
                lenn--;
            }
            while (curr1!=curr2){
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            return curr1;



        }else {
            //（情况2、情况3）入环点不同， 则判断往后走是否可遇到对方的入环点
            Node curr = in1.next;
            while (curr != in1){
                if (curr == in2){
                    return in2;
                }
                curr = curr.next;
            }
            return null;
        }

    }


}
