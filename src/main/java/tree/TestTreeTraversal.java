package tree;

/**
 * @Author admin
 * @Date 2022/8/20
 */
public class TestTreeTraversal {
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

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node4.right = node7;

        node3.left = node6;
        node3.right = node10;

        node6.left = node8;
        node6.right = node9;

        //直观地打印一棵树
        PrintTreeUtil.printTree(node1);

        /*System.out.println("====递归方式：前序遍历====");
        PreInPosTraversal.preOrderRecur(node1);
        System.out.println("====递归方式：中序遍历====");
        PreInPosTraversal.inOrderRecur(node1);
        System.out.println("====递归方式：后序遍历====");
        PreInPosTraversal.postOrderRecur(node1);*/

        System.out.println("******非递归方式：前序遍历******");
        PreInPosTraversal.preOrderWithoutRecur(node1);
        System.out.println("******非递归方式：中序遍历******");
        PreInPosTraversal.inOrderWithoutRecur(node1);
        System.out.println("******非递归方式：后序遍历******");
        PreInPosTraversal.postOrderWithoutRecur(node1);


        System.out.println("******层级遍历******");
        LevelTraversal.levelOrderTraversal(node1);


        //int widthOfTree = LevelWidth.getWidthOfTree(node1);
        int widthOfTree = LevelWidth.getWidthOfTreeNoUseMap(node1);
        System.out.println("树的宽度为:"+widthOfTree);

    }


}
