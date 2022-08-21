package tree;

import java.util.LinkedList;

/**
 * 完全二叉树
 *  采用层级遍历校验
 *  1） 如果一个节点有右孩子，却没有左孩子，那么不是完全二叉树
 *  2） 如果一个节点的左右孩子不双全，那么后边遇到的所有节点都应该是叶子节点，否则不是完全二叉树
 * @Author admin
 * @Date 2022/8/21
 */
public class CompleteBinaryTree {


    public static boolean isCBT(Node head){
        if(head == null){
            return true;
        }

        //是否遇到了左右孩子不双全的节点
        boolean isMeet = false;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(head);
        while(!queue.isEmpty()){
            Node queueHead = queue.poll();
            Node leftChild = queueHead.left;
            Node rightChild = queueHead.right;

            if(isMeet && (leftChild != null || rightChild != null)){
                return false;
            }

            if(leftChild == null || rightChild == null){
                if(leftChild == null && rightChild != null){
                    return false;
                }
                isMeet = true;
                if(leftChild != null){
                    queue.offer(leftChild);
                }
            }else{
                queue.offer(leftChild);
                queue.offer(rightChild);
            }
        }
        return true;
    }

}
