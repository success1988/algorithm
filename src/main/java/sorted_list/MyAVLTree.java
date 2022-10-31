package sorted_list;

/**
 * 有序表之平衡二叉树
 * 二叉树的左旋和右旋(头节点倒向哪边，就是什么旋)
 *
 *  在二叉树在保证搜索二叉树的前提下，二叉树的左子树或者右子树可能会过长，就无法保证该二叉树是一颗平衡二叉树，因此对过长的那一颗子树进行左旋或者右旋。
 * LL型，进行右旋；RR型，进行左旋。对于LR型，先左旋再右旋；RL型，先右旋再左旋
 */
public class MyAVLTree {
 
    //平衡二叉树节点属性
    static class AVLNode<K extends Comparable<K>,V>{
        //封装平衡二叉树的节点信息
        public K key;
        public V value;
        //二叉树的左右孩子
        public AVLNode<K,V> left;
        public AVLNode<K,V> right;
 
        public int height;  //树的高度
 
        public AVLNode(K key,V value){
            this.key = key;
            this.value = value;
            height = 1;
        }
    }
 
    //有序表之平衡二叉树
    public static class AVLTreeMap<K extends Comparable<K>,V>{
        public AVLNode<K,V> root;  //整个有序表的根节点
        public int size;  //已经加入的key的个数
 
        public AVLTreeMap(){
            root = null;
            size = 0;
        }
 
        //获得node树的高度
        private int height(AVLNode<K,V> node) {
            return node == null ? 0 : node.height;
        }
 
        //更新node树的高度
        private void updateNodeHeight(AVLNode<K,V> node){
            if (node != null) {
                node.height = Math.max(height(node.left),height(node.right)) + 1;
            }
        }
 
        //对cur节点的整个树进行左旋
        // 返回的是当前节点的右节点
        // 当前cur节点的右节点 为 cur右节点的左节点
        // 当前cur节点 为 之前cur右节点的左节点
        private AVLNode<K,V> leftRotate(AVLNode<K,V> cur){ //左旋转后，当前节点cur 和 cur.right的高度都发生变化
            AVLNode<K,V> temp = cur.right;
            cur.right = temp.left;
            temp.left = cur;
            //左旋过后，cur变成子树，一定要先调整子树的高度
            updateNodeHeight(cur);
            updateNodeHeight(temp);
            return temp;
        }
 
        //对cur节点的整棵树进行右旋
        // 返回的是当前节点的左节点
        // 当前节点cur的左节点  为 cur左节点的右节点
        // 当前cur 节点 为 之前cur左节点的右节点
        private AVLNode<K,V> rightRotate(AVLNode<K,V> cur){
            AVLNode<K,V> temp = cur.left;
            cur.left = temp.right;
            temp.right = cur;
            //右旋过后，cur变为子树，先调整子树的高度
            updateNodeHeight(cur);
            updateNodeHeight(temp);
            return temp;
        }
 
        //平衡这颗二叉树，并返回平衡后的二叉树的根节点
        private AVLNode<K,V> rebalance(AVLNode<K,V> node){
            if(node == null){
                return null;
            }
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            int nodeBalance = rightHeight - leftHeight;
            if(nodeBalance > 1){  // R
                if(node.right.right != null){  // R    RR 左移一次即可
                    node = leftRotate(node);
                }else {  // L   RL 先对右节点右移，再对整棵树左移
                    node.right = rightRotate(node.right);
                    node = leftRotate(node);
                }
            }else if(nodeBalance < -1){  // L
                if(node.left.left != null){ // L   LL 右移一次即可
                    node = rightRotate(node);
                }else {
                    node.left = leftRotate(node.left);
                    node = rightRotate(node);
                }
            }
            return node;
        }
 
        //添加节点
        // 在以cur为头的整颗子树上，加记录，并且把整棵树的头结点返回
        public AVLNode<K,V> add(AVLNode<K,V> cur,K key,V value){
            if(cur == null){
                return new AVLNode(key,value);
            }else {
                if(key.compareTo(cur.key) < 0){
                    cur.left = add(cur.left,key,value);
                }else {
                    cur.right = add(cur.right,key,value);
                }
            }
            updateNodeHeight(cur);//更新cur节点树的高度
            //每次添加，都需要进行一次二叉树的平衡
            return rebalance(cur);
        }
 
        // 在cur这棵树上，删掉key所代表的节点
        // 返回cur这棵树的新头部
        public AVLNode<K,V> delete(AVLNode<K,V> cur,K key){
            if(key.compareTo(cur.key) > 0){
                cur.right = delete(cur.right,key);
            }else if(key.compareTo(cur.key) < 0){
                cur.left = delete(cur.left,key);
            }else {
                if(cur.left == null && cur.right == null){
                    cur = null;
                }else if(cur.left == null && cur.right != null) {
                    cur = cur.right;
                }else if(cur.left != null && cur.right == null){
                    cur = cur.left;
                }else {  //不是叶子节点，用右树上的最左节点代替
                    // 找到右树上的最左结点
                    AVLNode<K,V> des=cur.right;
                    while (des.left!=null){
                        des=des.left;
                    }
                    // 先在右树调一个delete()方法删掉最左结点，完成右树的平衡调整，
                    // 然后得到最左结点，替换要删除的结点，然后依次往上检查平衡性
                    cur.right=delete(cur.right,des.key);
                    des.left=cur.left;
                    des.right=cur.right;
                    cur=des;
                }
            }
            updateNodeHeight(cur);
            // cur会从要删除的节点处回退到根节点，每次要保证子树是平衡的
            return rebalance(cur);
        }
    }
}