package tree;

/**
 * 直观打印一棵树
 * @Author admin
 * @Date 2022/8/20
 */
public class PrintTreeUtil {

    // 打印二叉树的入口函数
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    // 打印二叉树的主逻辑函数
    public static void printInOrder(Node head, int height, String to, int len) {
        /*
         * head 头节点
         * height 当前高度
         * to 代表是左子树还是右子树 => 为H时代表根节点
         * len 代表每个节点最多占的宽度
         * */
        if (head == null) {
            return;
        }

        // 继续打印右子树
        printInOrder(head.right, height + 1, "v", len);

        String val = to + head.value + to;
        int lenM = val.length();            // 节点(加上节点标志)的长度
        int lenL = (len - lenM) / 2;        // 节点左边的长度
        int lenR = len - lenM - lenL;        // 节点右边的长度
        val = getSpace(lenL) + val + getSpace(lenR);        // 为节点加上左右两边的空格
        System.out.println(getSpace(height * len) + val);    // 打印当前节点

        // 继续打印左子树
        printInOrder(head.left, height + 1, "^", len);
    }

    // 为节点加上左右两边的空格
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }


}
