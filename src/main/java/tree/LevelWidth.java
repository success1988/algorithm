package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 树的宽度
 * @Author admin
 * @Date 2022/8/20
 */
public class LevelWidth {

    /**
     * 使用hashMap来计算树的宽度
     * @param head
     * @return
     */
    public static int getWidthOfTree(Node head){
        if(head == null){
            return 0;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(head);
        Map<Node,Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);

        //当前层级
        int curLevel = 1;
        //当前层级的节点数量
        int curLevelNodeNum = 0;
        //整棵树的最大宽度
        int maxLevelNodeNum = 1;

        while(!queue.isEmpty()){
            Node queueHead = queue.poll();
            int level = levelMap.get(queueHead);

            if(level == curLevel){
                curLevelNodeNum++;
            }else {
                maxLevelNodeNum = Math.max(maxLevelNodeNum, curLevelNodeNum);
                curLevel++;
                curLevelNodeNum = 1;
            }

            if(queueHead.left != null){
                levelMap.put(queueHead.left, level+1);
                queue.offer(queueHead.left);
            }
            if(queueHead.right != null){
                levelMap.put(queueHead.right, level+1);
                queue.offer(queueHead.right);
            }
        }
        return maxLevelNodeNum;
    }



    /**
     * 不使用hashMap来计算树的宽度
     * @param head
     * @return
     */
    public static int getWidthOfTreeNoUseMap(Node head){
        if(head == null){
            return 0;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(head);
        //当前层级的最后一个节点
        Node curLevelEndNode = head;
        //当前层级的下一层级的最后一个节点
        Node nextLevelEndNode = null;

        //当前层级的节点数量
        int curLevelNodeNum = 0;
        //整棵树的最大宽度
        int maxLevelNodeNum = 1;

        while(!queue.isEmpty()){
            Node queueHead = queue.poll();
            curLevelNodeNum++;

            if(queueHead.left != null){
                nextLevelEndNode = queueHead.left;
                queue.offer(queueHead.left);
            }
            if(queueHead.right != null){
                nextLevelEndNode = queueHead.right;
                queue.offer(queueHead.right);
            }

            //达到当前层级的最后一个节点时，就该结算当前层的节点数
            if(queueHead == curLevelEndNode){
                maxLevelNodeNum = Math.max(maxLevelNodeNum, curLevelNodeNum);

                //滚动更新当前层的节点数及当前层的最后一个节点
                curLevelNodeNum = 0;
                curLevelEndNode = nextLevelEndNode;
            }
        }
        return maxLevelNodeNum;
    }



}
