package sort.nlogN;

import sort.CheckMethodUtil;

import java.util.Arrays;

/**
 *
 * 父：  (i-1)/2
 * 左孩子： 2*i+1
 * 右孩子： 2*i+2
 *
 * heapInsert过程  arr, index（index+1等同heapSize）
 * heapify过程   arr,  index , heapSize
 * @Author wangcg
 * @create 2022/8/11 9:50
 */
public class HeapSort {

    public static void main(String[] args) {
        CheckMethodUtil.compareMethod(HeapSort::sort, Arrays::sort);
    }

    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        //一、先构建大顶堆（可以初始化，也可以从叶子节点开始堆化）
        /*for (int i = 0; i < arr.length ; i++) {
            heapInsert(arr, i);
        }*/

        //用这种方式构建的时间复杂度为O(N)级别的
        for (int i = arr.length-1; i >= 0; i--) {
            heapify(arr, i ,arr.length);
        }

        //二、依次弹出大顶堆的最大值，把弹出的值放在arr[heapSize]处
        for (int i = arr.length-1; i >= 0; i--) {
            swap(arr, 0,  i);
            heapify(arr, 0 ,i);
        }

    }

    private static void heapInsert(int[] arr, int index) {
        //循环条件兼容了两种情况：没有父节点的值大会停止， 到达根节点时也会停止【因为(index-1)/2 == 0】
        while(arr[index] > arr[(index-1)/2]){
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }


    /**
     * 堆化
     */
    private static void heapify(int[] arr, int index, int heapSize) {

        int left = 2*index+1;

        while(left < heapSize){
            //比较两个孩子
            int largeIndexOfChildren = (left+1 < heapSize && arr[left+1] > arr[left] ) ? left+1 : left ;
            if(arr[largeIndexOfChildren] <= arr[index]){
                break;
            }

            swap(arr, index, largeIndexOfChildren);
            index = largeIndexOfChildren;
            left = 2*index+1;
        }

    }

    private static void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

}