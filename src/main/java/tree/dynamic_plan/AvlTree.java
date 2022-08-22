package tree.dynamic_plan;

import tree.Node;

/**
 * 平衡二叉树
 *     左右子树的高度差不大于1 并且 左右子树分别是平很二叉树
 * @Author admin
 * @Date 2022/8/21
 */
public class AvlTree {

    public static class ReturnType{
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    private static ReturnType process(Node x) {
        //base case
        if(x == null){
            return new ReturnType(true, 0);
        }

        ReturnType leftResult = process(x.left);
        ReturnType rightResult = process(x.right);

        boolean isBalanced = leftResult.isBalanced && rightResult.isBalanced && Math.abs(leftResult.height-rightResult.height) <= 1;
        int height = Math.max(leftResult.height, rightResult.height)+1;
        return new ReturnType(isBalanced, height);
    }


}
