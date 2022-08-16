package tree;

/**
 * 递归方式实现树的前中后序遍历
 * @Author admin
 * @Date 2022/8/17
 */
public class PreInPosTraversal {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node4.right = node7;

        node3.left = node6;

        node6.left = node8;
        node6.right = node9;

        System.out.println("====前序遍历====");
        preOrderRecur(node1);
        System.out.println("====中序遍历====");
        inOrderRecur(node1);
        System.out.println("====后序遍历====");
        postOrderRecur(node1);
    }

    /**
     * 递归方式： 前序（先序）遍历
     * @param head
     */
    public static void preOrderRecur(Node head){
        if(head == null){
            return;
        }

        System.out.print(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 递归方式： 中序遍历
     * @param head
     */
    public static void inOrderRecur(Node head){
        if(head == null){
            return;
        }

        inOrderRecur(head.left);
        System.out.print(head.value+" ");
        inOrderRecur(head.right);
    }

    /**
     * 递归方式： 后序遍历
     * @param head
     */
    public static void postOrderRecur(Node head){
        if(head == null){
            return;
        }

        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value+" ");
    }


}
