package tree;

import java.util.LinkedList;

/**
 * 二叉树的层级遍历
 * @Author admin
 * @Date 2022/8/20
 */
public class LevelTraversal {

    public static void levelOrderTraversal(Node head){

        if(head == null){
            return;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(head);
        while(!queue.isEmpty()){
            Node queueHead = queue.poll();
            System.out.println(queueHead.value);
            if(queueHead.left != null){
                queue.offer(queueHead.left);
            }
            if(queueHead.right != null){
                queue.offer(queueHead.right);
            }
        }
        
    }




}
