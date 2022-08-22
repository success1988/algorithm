package tree.dynamic_plan;

import tree.Node;

/**
 * 搜索二叉树
 *   左子树、右子树都是搜索二叉树
 *   左子树的最大值 < 根节点 < 右子树的最小值
 * @Author admin
 * @Date 2022/8/21
 */
public class SearchBinaryTree {

    public static class ReturnType{
        public boolean isSearch;
        public int minValue;
        public int maxValue;

        public ReturnType(boolean isSearch, int minValue, int maxValue) {
            this.isSearch = isSearch;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public static boolean isSearchTree(Node head){
        return process(head).isSearch;
    }

    private static ReturnType process(Node x) {
        //base case
        if(x == null){
            return null;
        }

        ReturnType leftResult = process(x.left);
        ReturnType rightResult = process(x.right);

        int min = x.value;
        int max = x.value;
        if(leftResult != null){
            min = Math.min(min, leftResult.minValue);
            max = Math.max(max, leftResult.maxValue);
        }
        if(rightResult != null){
            min = Math.min(min, rightResult.minValue);
            max = Math.max(max, rightResult.maxValue);
        }

        boolean isBST = true;
        if(leftResult != null && (leftResult.isSearch == false || leftResult.maxValue >= x.value)){
            isBST = false;
        }
        if(rightResult != null && (rightResult.isSearch == false || rightResult.minValue <= x.value)){
            isBST = false;
        }
        return new ReturnType(isBST, min, max);
    }



}
