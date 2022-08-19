package tree;

import java.util.Stack;

/**
 * 递归方式和非递归方式实现树的前中后序遍历
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

        /*System.out.println("====递归方式：前序遍历====");
        preOrderRecur(node1);
        System.out.println("====递归方式：中序遍历====");
        inOrderRecur(node1);
        System.out.println("====递归方式：后序遍历====");
        postOrderRecur(node1);*/

        System.out.println("******非递归方式：前序遍历******");
        preOrderWithoutRecur(node1);
        System.out.println("******非递归方式：中序遍历******");
        inOrderWithoutRecur(node1);
        System.out.println("******非递归方式：后序遍历******");
        postOrderWithoutRecur(node1);
    }

    /**
     * 递归方式： 前序（先序）遍历
     * @param head
     */
    public static void preOrderRecur(Node head){
        if(head == null){
            return;
        }

        System.out.println(head.value);
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 非递归方式： 前序（先序）遍历
     *   使用栈
     *       初始化，先压缩头节点
     *
     *       弹出一个节点，打印
     *       将弹出节点的右孩子和左孩子依次压入栈中
     * @param head
     */
    public static void preOrderWithoutRecur(Node head){
        if(head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while(!stack.isEmpty()){
            Node popNode = stack.pop();
            System.out.println(popNode.value);

            if(popNode.right != null){
                stack.push(popNode.right);
            }
            if(popNode.left != null){
                stack.push(popNode.left);
            }
        }
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
        System.out.println(head.value);
        inOrderRecur(head.right);
    }

    /**
     * 非递归方式： 中序遍历  左根右
     *   使用栈
     *       将根节点的左边界都压入栈中
     *       每弹出一个节点，就打印
     *       对弹出节点的右孩子做同样的操作
     * @param head
     */
    public static void inOrderWithoutRecur(Node head){
        if(head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        while(head != null || !stack.isEmpty()){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else{
                Node popNode = stack.pop();
                System.out.println(popNode.value);
                head = popNode.right;
            }
        }
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
        System.out.println(head.value);
    }


    /**
     * 非递归方式： 后序遍历
     *   使用两个栈，其中一个用来收集
     * @param head
     */
    public static void postOrderWithoutRecur(Node head){
        if(head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        Stack<Node> collectorStack = new Stack<>();
        stack.push(head);

        while(!stack.isEmpty()){
            Node popNode = stack.pop();
            collectorStack.push(popNode);

            if(popNode.right != null){
                stack.push(popNode.right);
            }
            if(popNode.left != null){
                stack.push(popNode.left);
            }
        }
        while(!collectorStack.isEmpty()){
            System.out.println(collectorStack.pop().value);
        }
    }



}
