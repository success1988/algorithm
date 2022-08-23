package tree.dynamic_plan;

/**
 * 最大的搜索子树（节点数量最多）的头结点
 *   左子树最大的搜索子树的头结点、子树最大的搜索子树的头结点
 * @author wangcg
 * @create 2022/8/23 9:43
 */
public class MaxSearchHead {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(10);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node4.right = node7;

        node3.left = node8;

        node8.left = node6;
        node8.right = node9;

        Info info = process(node1);
        System.out.println("最大的搜索子树的头结点:"+ (info == null? null: info.maxSearchHead));
    }

    public static class Info{
        private int min;
        private int max;
        private int maxSearchNodes;
        public boolean isAllSearch;
        public Node maxSearchHead;

        public Info(int min, int max, int maxSearchNodes, boolean isAllSearch, Node maxSearchHead) {
            this.min = min;
            this.max = max;
            this.maxSearchNodes = maxSearchNodes;
            this.isAllSearch = isAllSearch;
            this.maxSearchHead = maxSearchHead;
        }
    }

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static Info process(Node head){
        if(head == null){
            return null;
        }

        Info leftData = process(head.left);
        Info rightData = process(head.right);


        int min = head.value;
        int max = head.value;
        if(leftData != null){
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if(rightData != null){
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }

        int maxSearchNodes = 0;
        Node maxSearchHead = null;
        if(leftData != null){
            maxSearchNodes = leftData.maxSearchNodes;
            maxSearchHead = leftData.maxSearchHead;
        }
        if(rightData != null){
            if(rightData.maxSearchNodes > maxSearchNodes){
                maxSearchNodes = rightData.maxSearchNodes;
                maxSearchHead = rightData.maxSearchHead;
            }
        }


        boolean isAllSearch = false;
        if(
                (leftData == null ? true: leftData.isAllSearch)
                && (rightData == null ? true: rightData.isAllSearch)
                && (leftData == null ? true: leftData.max < head.value)
                && (rightData == null ? true: rightData.min > head.value)
        ){
            isAllSearch = true;
            maxSearchHead = head;
            maxSearchNodes = (leftData == null ? 0: leftData.maxSearchNodes) + (rightData == null ? 0: rightData.maxSearchNodes) + 1;
        }
        return new Info(min, max, maxSearchNodes, isAllSearch, maxSearchHead);
    }
}