package sorted_list;

import java.util.TreeSet;

/**
 *
 *哈希表的简单介绍
 *     1>哈希表在使用层面可以理解为一种集合结构。
 *     2>如果只有key，没有伴随数据value，可以使用HashSet结构
 *     3>如果既有key，又有伴随数据value，可以使用HashMap结构
 *     4>有无伴随数据，是HashSet和HashMap唯一区别，底层的实际结构是一回事
 *     5>使用哈希表增(put)、删(remove)、改(put)和查(get)的操作，可以认为时间复杂度为O(1)，但是常数时间比较大
 *     6>放入哈希表的东西，如果是基础类型，内部按值传递，内存占用就是这个东西的大小
 *     7>放入哈希表的东西，如果不是基础类型，内部按引用传递，内存占用是这个东西内存地址的大小（占用8byte）
 *
 * 有序表的简单介绍
 *     1>有序表在使用层面上可以理解为一种集合结构
 *     2>如果只有key，没有伴随数据value，可以使用TreeSet结构
 *     3>如果既有key，又有伴随数据value，可以使用TreeMap结构
 *     4>有无伴随数据，是TreeSet和TreeMap唯一的区别，底层的实际结构是一回事
 *     5>有序表和哈希表的区别是，有序表把key按照顺序组织起来，而哈希表完全不组织
 *     6>红黑树、AVL树，size-balance-tree和跳表都属于有序表结构，只是底层具体实现不同
 *     AVL树本质上还是一棵二叉查找树，它的特点是：
 *              本身首先是一棵二叉查找树。
 *              带有平衡条件：每个结点的左右子树的高度之差的绝对值（平衡因子）最多为1。
 *     7>放入哈希表的东西，如果是基础类型，内部按值传递，内存占用就是这个东西的大小
 *     8>放入哈希表的东西，如果不是基础类型，必须提供比较器，内部按引用传递，内存占用是这个东西内存地址的大小
 *     9>不管是什么底层具体实现，只要是有序表，都有一下固定的基本功能和固定的时间复杂度
 * @author wangcg
 * @date 2022/10/31 10:31
 */
public class SortedListApi {

    public static void main(String[] args) {

        TreeSet<Integer> priceSet = new TreeSet<>();
        priceSet.add(10);
        priceSet.add(15);
        priceSet.add(1);
        priceSet.add(3);
        priceSet.add(7);
        priceSet.add(8);

        //价格区间
        System.out.println("价格区间："+priceSet.first()+"~"+priceSet.last());
        System.out.println("floor:"+priceSet.floor(5));
        System.out.println("ceiling:"+priceSet.ceiling(5));

    }

}
