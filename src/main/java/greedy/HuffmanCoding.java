package greedy;

import java.util.PriorityQueue;

/**
 * 按照权重升序排列
 *
 * @author admin
 * @date 2022/9/4 6:48
 */
public class HuffmanCoding {

    /**
     * 哈夫曼（Huffma）树又称最优二叉树。它是ｎ个带权叶子结点构成的所有二叉树中，带权路径长度WTL最小的二叉树。因为构造这种树的算法是最早由哈夫曼于1952年提出的，所以被称为哈夫曼树，相应的算法称为哈夫曼算法。
     * 从树中某一结点到另一个节点所经过的分支构成这两个节点之间的路径。路径的分支数目叫做这两个节点之间的路径长度。
     * 树的路径长度是从树根到树中每个结点之间路径长度之和。这种路径长度最短的是完全二叉树。
     * 从根结点到某结点之间的路径长度与该结点所带的权值的乘积，称为该结点的带权路径长度，计作WkLk,其中Lk为结点K的路径长度，Wk为结点K的权值。
     * 树的带权路径长度是树中所有带权结点的路径长度之和。
     *
     *
     * @param arr
     * @return
     */


    public static int leastMoneySplitGold(int[] arr){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> a.compareTo(b));

        for (int i = 0; i < arr.length; i++) {
            minHeap.offer(arr[i]);
        }

        int sum = 0;
        int cur = 0;
        while(minHeap.size() > 1){
            cur = minHeap.poll() + minHeap.poll();
            sum += cur;
            minHeap.offer(cur);
        }

        return sum;
    }


    public static void main(String[] args) {
        System.out.println(leastMoneySplitGold(new int[]{10, 20, 30}));
    }


}
