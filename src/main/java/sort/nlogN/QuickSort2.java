package sort.nlogN;

import sort.CheckMethodUtil;

import java.util.Arrays;

/**
 * 快排的空间复杂度是logN
 * @Author admin
 * @Date 2022/8/11
 */
public class QuickSort2 {

    public static void main(String[] args) {
        CheckMethodUtil.compareMethod(QuickSort2::sort, Arrays::sort);
    }

    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }


    public static void quickSort(int[] arr, int left, int right){
        if(left < right){
            /*if(left > right-60){
                //TODO:在arr[left...right]上使用插入排序，小样本量的时候，跑得快
                return;
            }*/
            int[] equalsZone = partition(arr, left, right);
            quickSort(arr, left, equalsZone[0]-1);
            quickSort(arr, equalsZone[1]+1, right);
        }
    }

    /**
     * 返回相等区域的左右边界索引位置
     */
    private static int[] partition(int[] arr, int left, int right) {

        swap(arr, left + (int)Math.random()*(right+1-left) , right);

        int less = left;
        int more = right;

        int target = arr[right];
        while(left < more){
            if(arr[left] < target){
                //小于时，跟小于区域的下一个交换，小于区域边界+1, 然后看下一个
                swap(arr, left++, less++);
            }else if(arr[left] > target){
                //小于时，跟大于区域的上一个交换，大于区域边界-1, 原地不动
                swap(arr, left, --more);
            }else{
                //相等时，看下一个
                left++;
            }
        }
        swap(arr, more, right);
        return new int[]{less, more};
    }

    public static void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

}
