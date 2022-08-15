package linkedlist;

/**
 * 给定两个有序链表的头指针head1和head2，打印两个链表的公共部分
 * @author wangcg
 * @create 2022/8/15 19:39
 */
public class TwoOrderedListCommonPart {

    public static void main(String[] args) {

        // 1,3,4,6,8
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(6);
        Node node5 = new Node(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Node nodeA = new Node(2);
        Node nodeB = new Node(4);
        Node nodeC = new Node(8);
        Node nodeD = new Node(10);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;


        printCommonPart(node1, nodeA);
    }

    public static void printCommonPart(Node head1, Node head2) {
        System.out.print("Common part: ");
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.printf("%d ", head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }
}
