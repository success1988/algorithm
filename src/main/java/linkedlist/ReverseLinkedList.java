package linkedlist;

/**
 * 反转链表
 * @Author admin
 * @Date 2022/8/15
 */
public class ReverseLinkedList {

    public static Node reverseSingleList(Node head){
        Node pre = null;
        Node next = null;

        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;

        while(head != null){
            next = head.next;
            head.next = pre;
            //与单链表反转的区别
            head.last = next;
            pre = head;
            head = next;
        }

        return pre;
    }


}
