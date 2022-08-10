package sort.ext;

import sort.CheckMethodUtil;

import java.util.Arrays;

/**
 * 归并排序变形2：求数组中逆序对的个数
 * @Author wangcg
 * @create 2022/8/10 10:34
 */
public class MergeSortInversePair {


    public static void main(String[] args) throws InterruptedException {
        int[] arr = CheckMethodUtil.generateRandomArray(7, 50);
        int[] copyArray = CheckMethodUtil.copyArray(arr);
        int pairNum = process(copyArray, 0, arr.length-1);
        System.out.println("数组"+ Arrays.toString(arr)+"中逆序对的个数为："+pairNum);
    }

    public static int process(int[] arr, int left, int right) {
        if(left >= right){
            return 0;
        }

        int mid = left + ((right-left) >> 1);
        return process(arr, left, mid)
                + process(arr, mid+1, right)
                + merge(arr, left, mid, right);
    }


    public static int merge(int[] arr, int left, int mid, int right){
        //从left~mid已经有序
        //从mid+1~right已经有序

        int currentPairSum = 0;
        int leftIndex = left;
        int rightIndex = mid+1;

        //声明一个辅助空间
        int[] help = new int[right+1-left];
        //辅助空间的索引  
        int i = 0;
        while (leftIndex <= mid && rightIndex <= right){
            currentPairSum += (arr[leftIndex]>arr[rightIndex] ? (right+1-rightIndex) : 0);
            //这里谁小取谁，默认从左边取
            help[i++] = (arr[leftIndex]<=arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++]);
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

        return currentPairSum;
    }


}