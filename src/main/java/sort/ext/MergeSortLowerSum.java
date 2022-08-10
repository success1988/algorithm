package sort.ext;

import sort.CheckMethodUtil;

import java.util.Arrays;

/**
 * 归并排序变形1：小和问题
 * @Author wangcg
 * @create 2022/8/10 10:35
 */
public class MergeSortLowerSum {

    public static void main(String[] args) {
        int[] arr = CheckMethodUtil.generateRandomArray(5, 50);
        int[] copyArray = CheckMethodUtil.copyArray(arr);
        int lowerSum = process(copyArray, 0, arr.length-1);
        System.out.println("数组"+ Arrays.toString(arr)+"中的小和为："+lowerSum);
    }

    public static int process(int[] arr, int left, int right) {
        if(left >= right){
            return 0;
        }

        /**
         * 小和由三部分组成，一部分是左侧数组的小和，一部分是右侧数组的小和，
         * 还有一部分是merge的时候产生的小和，这三种小和累加才是最后的最终小和
         */
        int mid = left + ((right-left) >> 1);
        return process(arr, left, mid)
                + process(arr, mid+1, right)
                + merge(arr, left, mid, right);
    }


    public static int merge(int[] arr, int left, int mid, int right){
        //从left~mid已经有序
        //从mid+1~right已经有序

        int currentLowerSum = 0;
        int leftIndex = left;
        int rightIndex = mid+1;

        //声明一个辅助空间
        int[] help = new int[right+1-left];
        //辅助空间的索引
        int i = 0;
        while (leftIndex <= mid && rightIndex <= right){
            currentLowerSum += (arr[leftIndex]<arr[rightIndex] ? arr[leftIndex]*(right+1-rightIndex) : 0);
            //这里谁小取谁，默认从右边取
            help[i++] = (arr[leftIndex]<arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++]);
        }

        while (leftIndex <= mid){
            help[i++] = arr[leftIndex++];
        }

        while (rightIndex <= right){
            help[i++] = arr[rightIndex++];
        }

        //将辅助空间中的排序结果给拷贝回来, 索引的偏移量为left
        for (int j = 0; j < help.length ; j++) {
            arr[left+j] = help[j];
        }

        return currentLowerSum;
    }

}