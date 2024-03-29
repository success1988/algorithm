package sort.nlogN;

import sort.CheckMethodUtil;

import java.util.Arrays;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2021/11/21 21:31
 * @Description
 * @Version
 */
public class MergeSort {

    //调用对数器验证排序方法的正确性
    public static void main(String[] args) {
        CheckMethodUtil.compareMethod(MergeSort::testMergeSort, Arrays::sort);
    }

    public static void testMergeSort(int[] arr){
        mergeSort(arr, 0, arr.length-1);
    }


    public static void  mergeSort(int[] arr,int left, int right){
        if(left >= right){
            return;
        }

        int mid = left+((right-left)>>1);
        mergeSort(arr, left, mid);
        mergeSort(arr,mid+1, right);
        merge(arr, left, mid, right);

    }



    public static void  merge(int[] arr, int left,int mid, int right){
        int leftIndex = left,rightIndex = mid+1;
        //声明一个临时数组，来存储合并结果
        int[] temp = new int[right+1-left];
        int i = 0;
        while(leftIndex <= mid && rightIndex <= right){
            if(arr[leftIndex] < arr[rightIndex]){
                temp[i++] = arr[leftIndex++];
            }else{
                temp[i++] = arr[rightIndex++];
            }
        }
        while(leftIndex <= mid){
            temp[i++] = arr[leftIndex++];
        }
        while(rightIndex <= right){
            temp[i++] = arr[rightIndex++];
        }
        //将temp中的元素全部拷贝到原数组中
        for(int j=0; j<temp.length; j++){
            arr[j+left] = temp[j];
        }
        //将合并排序的结果 放回到原始数组中
        //System.arraycopy(temp,0, arr, left, temp.length);
    }



}
