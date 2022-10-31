package sorted_list;

/**
 * 每颗子树的大小，不小于其兄弟的子树大小;即每颗叔叔树的大小，不小于其任何子树的大小
 *
 * SB树的四种违规：目前所在的节点为节点A
 *     LL违规：A节点的左孩子B的左孩子E的节点个数 ＞ A节点的右孩子C的节点个数
 *     LR违规：A节点的左孩子B的右孩子F的节点个数 ＞ A节点的右孩子C的节点个数
 *     RL违规：A节点的右孩子C的左孩子G的节点个数 ＞ A节点的左孩子B的节点个数
 *     RR违规：A节点的右孩子C的右孩子H的节点个数 ＞ A节点的左孩子B的节点个数
 * 不管是属于四种违规类型的哪种，调整方式跟AVL树一样，都是左旋或者右旋；唯一的区别就是旋转完后，哪个结点的孩子发生了变化，就要调到用平衡调整！
 */
public class MySizeBalancedTree{
    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;
        public int size; // 不同的key的数量
 
        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }
 
    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;
 
        //右旋转
        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            leftNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            return leftNode;
        }
 
        //左旋转
        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            return rightNode;
        }
 
        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left != null ? cur.left.size : 0;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightSize = cur.right != null ? cur.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            if (leftLeftSize > rightSize) {  //LL
                cur = rightRotate(cur);
                //旋转过后，那个节点上的子节点变化了，就需要进行平衡调整
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {  //LR
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                //旋转过后，返回的新的头节点以及头节点的左右孩子都发生变化
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {  //RR
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {  //RL
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }
 
        // 现在，以cur为头的树上，新增，加(key, value)这样的记录
        // 加完之后，会对cur做检查，该调整调整
        // 返回，调整完之后，整棵树的新头部
        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<>(key, value);
            } else {
                cur.size++;
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left, key, value);
                } else {
                    cur.right = add(cur.right, key, value);
                }
                return maintain(cur);
            }
        }
 
        // 在cur这棵树上，删掉key所代表的节点
        // 返回cur这棵树的新头部
        public SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            } else if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else {// 当前要删掉cur
                if (cur.left == null && cur.right == null) {
                    // free cur memory -> C++
                    cur = null;
                } else if (cur.left == null && cur.right != null) {
                    // free cur memory -> C++
                    cur = cur.right;
                } else if (cur.left != null && cur.right == null) {
                    // free cur memory -> C++
                    cur = cur.left;
                } else {// 左右孩子都有
                    // 找到cur的后继结点替换cur
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> des = cur.right;// des来到当前结点的右孩子
                    des.size--;
                    while (des.left != null) {
                        pre = des;
                        des = des.left;
                        des.size--;
                    }
                    // while循环结束后，des来到了cur结点的右树的最左孩子
                    // 并且此时的des是叶子结点，没有孩子了
                    // pre来到最左孩子的父亲结点
                    if (pre != null) {
                        pre.left = des.right;// 最左孩子的父亲断掉最左孩子
                        des.right = cur.right;// 最左孩子接管cur的右子树
                    }
                    des.left = cur.left;// 还是接管原来的左子树
                    des.size = des.left.size + (des.right == null ? 0 : des.right.size) + 1;
                    cur = des;
                }
            }
            // return maintain(cur);
            return cur;
        }
    }
       
}