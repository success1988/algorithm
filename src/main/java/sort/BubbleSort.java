package sort;

import java.util.Arrays;

/**
 * @Title：冒泡排序
 * @Author：wangchenggong
 * @Date 2021/11/20 10:32
 * @Description
 * @Version
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {2,1,5,3,4};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    public static  void bubbleSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }

        //轮数 i+1代表第几轮    i+1 <= arr.length-1,那么i+1 < arr.length
        for (int i = 0; i < arr.length-1; i++) {
            //j+1代表比较的次数， i+1 +  j+1 = arr.length
            for (int j = 0; j < arr.length-i-1; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

    }

}
