package tree;

/**
 * @Author admin
 * @Date 2022/8/21
 */
public class TestTreeCheck {

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

        System.out.println("====判断一个树是否为满二叉树==");
        System.out.println(CompleteBinaryTree.isCBT(node1));
    }
}
