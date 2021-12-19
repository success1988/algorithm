package sort.n2;

import java.util.Arrays;

/**
 * @Title：选择排序
 * @Author：wangchenggong
 * @Date 2021/11/21 21:03
 * @Description
 * @Version
 */
public class SelectionSort {


    public static void main(String[] args) {
        int[] arr = {2,1,5,3,4};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    public static  void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            //交换索引为i处的元素  和 i之后的最小元素
            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
