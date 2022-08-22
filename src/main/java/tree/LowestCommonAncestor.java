package tree;

/**
 * 最低公共祖先
 * @Author admin
 * @Date 2022/8/23
 */
public class LowestCommonAncestor {



    public static Node findLca(Node head, Node node1, Node node2){
        //base case
        if(head == null || node1 == head || node2 == head){
            return head;
        }

        Node left = findLca(head.left, node1, node2);
        Node right = findLca(head.right, node1, node2);

        if(left != null && right != null){
            return head;
        }
        //左右两棵子树，并不都有返回值
        return left != null ? left : right;
    }

}
