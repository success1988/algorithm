package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 *
 * 二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能会得到同样的中序序列
 * 比如：
 *        2               1
 *       /        和       \
 *      1                  2
 *      两者的中序序列都是 null, 1, null, 2 ,null
 * @Author admin
 * @Date 2022/8/23
 */
public class TreeSerialize {

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
        Node node10 = new Node(10);
        Node node11 = new Node(11);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node4.left = node11;
        node4.right = node7;

        node3.left = node6;
        node3.right = node10;

        node6.left = node8;
        node6.right = node9;

        String s = serialByPre(node1);
        System.out.println("先序遍历序列化："+s);

        Node newHead = deserialize(s);
        System.out.println(newHead.value);
    }


    public static String serialByPre(Node head){
        if(head == null){
            return "#_";
        }
        String res = head.value+"_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node deserialize(String preStr){
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length ; i++) {
            queue.offer(values[i]);
        }
        return deserializeQueue(queue);
    }

    private static Node deserializeQueue(Queue<String> queue) {
        String pollValue = queue.poll();
        if(pollValue.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(pollValue));
        head.left = deserializeQueue(queue);
        head.right = deserializeQueue(queue);
        return head;
    }


}
