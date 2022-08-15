package linkedlist;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *
 * 构造这个链表的 深拷贝
 * @Author admin
 * @Date 2022/8/15
 */
public class CopyListWithRandom {


    public static RandomNode copyListWithRand(RandomNode head) {
        if(head == null) {
            return null;
        }
        RandomNode cur = head;
        RandomNode next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = new RandomNode(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        RandomNode curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random != null ? cur.random.next : null;
            cur = next;

        }
        RandomNode res = head.next;
        cur = head;
        while(cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
