package sort.nlogN;

import java.util.Arrays;

/**
 * @Title： 归并排序--优化版本:原地排序
 * @Author：wangchenggong
 * @Date 2021/11/21 21:31
 * @Description
 * @Version
 */
public class MergeSort2 {

    public static void main(String[] args) {
        int[] arr = {3,4,6,8,5,7,1,9};
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }else if(left+1 == right){
            if(arr[left] > arr[right]){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
            return;
        }

        int mid = (left+right)/2;
        mergeSort(arr, left ,mid);
        mergeSort(arr, mid+1 ,right);

        merge(arr, left, mid ,right);
    }
    /**
     *  从left到bound有序，
     *  从bound+1到right有序，
     *  现在需求是原地排序，实现从left到right有序
     * @param arr
     * @param left
     * @param bound
     * @param right
     * @return
     */
    public static void merge(int[] arr, int left, int bound, int right) {
        //{3,4,6,8,5,7,1,7,9}
        //left:0
        //bound:3
        //right:5

        //插入排序的思路
        for (int i = bound+1; i <=right ; i++) {
            int value = arr[i];
            int j = i-1;
            for (; j >= left; j--) {
                if(arr[j] > value){
                    arr[j+1]= arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = value;
        }
        


    }

    

}
