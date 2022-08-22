package tree.dynamic_plan;

import tree.Node;

/**
 * 满二叉树：N = 2^n-1
 *      左右子树都是满二叉树,并不能推断出整棵树是满二叉树
 * @Author admin
 * @Date 2022/8/21
 */
public class FullBinaryTree {

    public static class Info{
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFullTree(Node head){
        Info result = process(head);
        int height = result.height;
        int nodes = result.nodes;

        boolean isFull = (1 << height) == (nodes+1);
        return isFull;
    }

    private static Info process(Node x) {
        //base case
        if(x == null){
            return new Info(0, 0);
        }

        Info leftData = process(x.left);
        Info rightData = process(x.right);

        int height = Math.max(leftData.height, rightData.height)+1;
        int nodes = leftData.nodes+rightData.nodes+1;
        return new Info(height, nodes);
    }


}
