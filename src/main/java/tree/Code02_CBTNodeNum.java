package tree;
 
/**
 * 求一颗完全二叉树节点的个数
 * 以X为头节点的完全二叉树个数，先向左遍历，遍历到左树的最大深度，全局变量，再找到右树上最左节点的深度，进行判断，递归相加。
 */
public class Code02_CBTNodeNum {
 
    class Node{
        int value;
        Node left;
        Node right;
 
        public Node(int value){this.value = value;}
    }
 
    public int getCBTNodeNum(Node head){
        if (head == null){
            return 0;
        }
        return process(head,1,mostleftLevel(head,1));
    }
 
    //以当前X为头节点的完全二叉树的个数
    // level 当前X节点再哪一层  height x左树的高度（不变）
    public int process(Node X,int level,int height){
        if(level == height){
            return 1;
        }
        if(mostleftLevel(X.right,level + 1) == height){ //X的右树的最左节点的深度和左树深度相同，说明X的左树是满二叉树
            // 以x为头节点的节点个数 = 2^(height - level) - 1 + 1 + ?(X的右树的节点个数)
            return (1 << (height - level)) + process(X.right,level + 1,height);
        } else { //X的右树的最左节点的深度和左树深度不同，说明X右树的右树是满二叉树
            // 以x为头节点的节点个数 = 2^(height - level - 1) - 1 + 1 + ?(X的左树的节点个数)
            return (1 << (height - level - 1)) + process(X.left,level + 1,height);
        }
    }
 
    //求以node为头的子树的最大深度，level表示node在整棵树的level层
    //以node为头的子树一定是完全二叉树
    public int mostleftLevel(Node node,int level){
        while (node != null){
            level++;
            node = node.left;
        }
        return level - 1;
    }
}