package linkedlist;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * @author wangcg
 * @create 2022/8/15 19:20
 */
public class OrderLinkedListThreePart {

    public static void main(String[] args) {

        // 1,4,3,6,2,8   pivot=4
        Node node1 = new Node(1);
        Node node2 = new Node(4);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        Node node5 = new Node(2);
        Node node6 = new Node(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        Node newHead = listPartition(node1, 4);
        while(newHead != null){
            System.out.println(newHead.value);
            newHead = newHead.next;
        }

    }


    public static Node listPartition(Node head, int pivot) {
        // 小于pivot的链表头尾节点
        Node lessSt = null;
        Node lessEnd = null;
        // 等于pivot的链表头尾节点
        Node eqSt = null;
        Node eqEnd = null;
        // 大于pivot的链表头尾节点
        Node moreSt = null;
        Node moreEnd = null;
        // 用于保存next指针
        Node next = null;
        // 遍历链表
        while(head != null) {
            next = head.next;
            // 为了不污染小于，等于，大于的指针next，这里清空
            head.next = null;
            if(head.value < pivot) {
                if(lessSt == null) {
                    lessSt = head;
                    lessEnd = head;
                }else {
                    lessEnd.next = head;
                    lessEnd = head;
                }
            }else if(head.value == pivot) {
                if(eqSt == null) {
                    eqSt = head;
                    eqEnd = head;
                }else {
                    eqEnd.next = head;
                    eqEnd = head;
                }
            }else {
                if(moreSt == null) {
                    moreSt = head;
                    moreEnd = head;
                }else {
                    moreEnd.next = head;
                    moreEnd = head;
                }
            }
            head = next;
        }
        // 小于和等于区域相连
        if(lessEnd != null) {
            lessEnd.next = eqSt;
            // 判断equal的结尾指针是否为空
            eqEnd = eqEnd == null ? lessEnd : eqEnd;
        }
        // 最后等于，大于区域相连
        if (eqEnd != null) {
            eqEnd.next = moreSt;
        }
        if (lessSt != null) {
            return lessSt;
        }
        if (eqSt != null) {
            return eqSt;
        }
        if (moreSt != null) {
            return moreSt;
        }
        return null;
    }

}
