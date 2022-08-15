package linkedlist;

//带有随机指针的节点结构
public class RandomNode {

    public int value;
    public RandomNode random;
    public RandomNode next;

    public RandomNode(int value) {
        this.value = value;
    }
}