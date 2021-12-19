package sort.n2;

import java.util.Arrays;

/**
 * @Title：插入排序
 * @Author：wangchenggong
 * @Date 2021/11/20 21:44
 * @Description
 * @Version
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {2,1,5,3,4};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    public static  void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            //每次取出未排序区的第一个元素
            int value = arr[i];
            int j = i-1;
            //倒着遍历，因为value的值被临时存储了
            for (; j >= 0; j--) {
                if(arr[j] > value){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = value;
        }
    }
}
