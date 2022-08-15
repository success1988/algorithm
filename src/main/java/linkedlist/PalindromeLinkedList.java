package linkedlist;

/**
 * 判断一个链表是否是回文链表
 * @author wangcg
 * @create 2022/8/15 19:55
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {

        // 1,3,4,3,1
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(3);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        boolean isPailResult = isPail(node1);
        System.out.println(isPailResult);
    }


    public static boolean isPail(Node head) {
        // write code here
        if(head==null){
            return false;
        }
        Node slow=head;
        Node fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        Node q = reverse(slow.next);
        Node p=head;
        while(p!=null&&q!=null){
            if(p.value!=q.value){
                return false;
            }
            p=p.next;
            q=q.next;
        }
        return true;

    }
    public static Node reverse(Node head){
        if(head==null){
            return null;
        }
        Node dummyNode=new Node(0);
        Node p=head;
        while(p!=null){
            Node q=p.next;
            p.next=dummyNode.next;
            dummyNode.next=p;
            p=q;
        }
        return dummyNode.next;
    }

}
