package sort.ext;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 一个基本有序的数组，移动距离都小于等于k, 如何高效地排序呢?
 *  这个问题类似于滑动窗口
 * @Author admin
 * @Date 2022/8/14
 */
public class HeapSortDistanceK {

    public static void main(String[] args) {

        int[] arr = {1,4,3,5,6,8,7,9,10,12,11,14,16,19,18,17};
        sortDistanceLessK(arr,2);
        System.out.println(Arrays.toString(arr));
    }


    public static void sortDistanceLessK(int[] arr, int k){

        //这里边有两个索引， 一个是遍历源数组的索引， 一个是排序后赋值源数组的索引
        int srcIndex = 0;
        int orderIndex = 0;

        PriorityQueue<Integer> minRootHeap = new PriorityQueue<>(k+1);
        for (; srcIndex < Math.min(arr.length, k); srcIndex++) {
            minRootHeap.add(arr[srcIndex]);
        }

        for (; srcIndex < arr.length; srcIndex++,orderIndex++) {
            minRootHeap.add(arr[srcIndex]);
            arr[orderIndex] = minRootHeap.poll();
        }

        while (!minRootHeap.isEmpty()){
            arr[orderIndex++] = minRootHeap.poll();
        }
    }
}
