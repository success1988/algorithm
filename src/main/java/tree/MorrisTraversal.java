package tree;

/**
 * 一种遍历二叉树的方式，并且时间复杂度O(N),额外空间复杂度O(1)。
 * 通过利用原树中大量空闲指针的方式，达到节省空间的目的。
 * @author wangcg
 * @date 2022/10/31 17:18
 */
public class MorrisTraversal {

    /**
     * 假设来到当前节点cur，开始时cur来到头节点位置
     * 1.如果cur没有左孩子，cur向右移动(cur = cur.right)
     * 2.如果cur有左孩子，找到左子树上最右的节点mostRight:
     *     a.如果mostRight的右指针为空，让其指向cur，然后cur向左移动(cur = cur.left)
     *     b.如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur = cur.right)
     * 3.cur为空时遍历停止
     */
    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static void morris(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){ //如果有左子树，就找到左子树的最右节点，没有则直接向右移动
                while (mostRight.right != null && mostRight.right != cur){ //找到左树上的最右节点
                    mostRight = mostRight.right;
                }
                //mostRight变成了左树上的最右节点
                if(mostRight.right == null){ //第一次来到，使最右节点的右指针指向cur，然后cur继续左移找左子树的最右节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;  //第一次来到过后，会直接进入下一次循环
                }else {  //第二次来到
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }


    /**
     * 先序遍历：节点只经过一次的，直接打印，经过两次的，打印第一次
     * @param head
     */
    public static void morris_Pre(Node head){ //先序遍历：节点只来一次，直接打印，来两次，打印第一次
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){ //如果有左子树，就找到左子树的最右节点，没有则直接向右移动
                while (mostRight.right != null && mostRight.right != cur){ //找到左树上的最右节点
                    mostRight = mostRight.right;
                }
                //mostRight变成了左树上的最右节点
                if(mostRight.right == null){ //第一次来到，使最右节点的右指针指向cur，然后cur继续左移找左子树的最右节点
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;   //第一次来到过后，会直接进入下一次循环
                }else {  //第二次来到
                    mostRight.right = null;
                }
            }else {
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    /**
     * 中序遍历：节点只经过一次的，直接打印，经过两次的，打印第二次
     * @param head
     */
    public static void morris_Mid(Node head){ //中序遍历：节点只来一次，直接打印，来两次，打印第二次
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){ //如果有左子树，就找到左子树的最右节点，没有则直接向右移动
                while (mostRight.right != null && mostRight.right != cur){ //找到左树上的最右节点
                    mostRight = mostRight.right;
                }
                //mostRight变成了左树上的最右节点
                if(mostRight.right == null){ //第一次来到，使最右节点的右指针指向cur，然后cur继续左移找左子树的最右节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;  //第一次来到过后，会直接进入下一次循环
                }else {  //第二次来到
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }


    /**
     * 后序遍历：打印第二次经过节点的左树的右边界(逆序打印)
     * @param head
     */
    public static void morris_Pos(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){ //如果有左子树，就找到左子树的最右节点，没有则直接向右移动
                while (mostRight.right != null && mostRight.right != cur){ //找到左树上的最右节点
                    mostRight = mostRight.right;
                }
                //mostRight变成了左树上的最右节点
                if(mostRight.right == null){ //第一次来到，使最右节点的右指针指向cur，然后cur继续左移找左子树的最右节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;  //第一次来到过后，会直接进入下一次循环
                }else {  //第二次来到
                    mostRight.right = null;
                    printEdge(cur.left);//打印第二次经过节点的左数的右边界(逆序)
                }
            }
            cur = cur.right;
        }
        printEdge(head);//整颗树跑完，单独打印整棵树左树的右边界(逆序)
        System.out.println();
    }

    //以node为头的树，逆序打印这棵树的右边界
    public static void printEdge(Node node){
        Node tail = reverseEdge(node);
        Node cur = tail;
        while (cur != null){
            System.out.print(cur.value + "\t");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node node){
        Node pre = null;
        Node next = null;
        while(node != null){
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
