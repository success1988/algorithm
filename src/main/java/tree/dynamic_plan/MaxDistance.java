package tree.dynamic_plan;

import tree.Node;

/**
 * 二叉树上两个节点之间的最大距离有两种可能性：
 *      1) 不经过X节点：  取左子树最大距离与右子树最大距离两者中的最大值
 *      2) 经过X节点：  左子树高度+1+右子树高度
 * @Author admin
 * @Date 2022/8/24
 */
public class MaxDistance {



    public static class Info{
        public int height;
        public int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }



    public static Info process(Node node){
        if(node == null){
            return new Info(0, 0);
        }
        Info leftData = process(node.left);
        Info rightData = process(node.right);

        int height = Math.max(leftData.height, rightData.height)+1;
        int maxDistance = Math.max(Math.max(leftData.maxDistance, rightData.maxDistance),leftData.height+1+rightData.height);
        return new Info(height, maxDistance);
    }



}
