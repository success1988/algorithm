package tree;

/**
 * 寻找后继节点(中序遍历的下一个节点）
 *    1. X有右树时： X右树上的最左节点
 *    2. X没有右树时：向上找，直到找到能让X自己以左孩子身份出现的父节点
 *                  如果一直找到整棵树的根节点也没有让X成为左孩子，那么X就是中序遍历的最后一个节点，它的后继节点就是null
 * @Author admin
 * @Date 2022/8/23
 */
public class SuccessorNode {

    public static class NodeWithParent {
        public int value;
        public NodeWithParent left;
        public NodeWithParent right;
        public NodeWithParent parent;

        public NodeWithParent(int data){
            this.value = data;
        }
    }


    public static NodeWithParent getSuccessorNode(NodeWithParent node){
        if(node == null){
            return node;
        }

        if(node.right != null){
            //右子树的最左节点
            return getLeftMostNode(node.right);
        }else {
            NodeWithParent parent = node.parent;
            while(parent != null && node != parent.left){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static NodeWithParent getLeftMostNode(NodeWithParent node) {
        if(node == null){
            return node;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }


}
