package linkedlist;
 
import java.util.List;
 
/**
 * 双向链表节点结构和二叉树结点结构是一样的，如果你把last认为是left，next认为是next的话。
 * 给定一个搜索二叉树的头节点head，请转换成一条有序的双向链表，并返回链表的头节点。
 */
public class Code02_BSTChangeToListNode {
 
    //搜索二叉树结点
    class Node{
        int value;
        Node left;
        Node right;
 
        public Node(int value){
            this.value = value;
        }
    }
 
    //搜索二叉树结点转换成双向链表结点
    //使用递归套路  左子树要头和尾 右子树要头和尾
    public Node BSTNodeChangeToListNode(Node head){
        return process(head).start;
    }
 
    class Info{
        Node start;
        Node end;
 
        public Info(Node start,Node end){
            this.start = start;
            this.end = end;
        }
    }
 
    public Info process(Node X){
        if(X == null){
            return new Info(null,null);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        if(leftInfo.end != null){
            leftInfo.end.right = X;
        }
        X.left = leftInfo.end;
        X.right = rightInfo.start;
        if (rightInfo.start != null){
            rightInfo.start.left = X;
        }
        return new Info(leftInfo.start != null ? leftInfo.start : X,
                rightInfo.end != null ? rightInfo.end : X);
    }
}