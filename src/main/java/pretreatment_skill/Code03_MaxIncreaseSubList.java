package pretreatment_skill;
 
import org.junit.Test;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
 
/**
 * 最长递增子序列
 */
public class Code03_MaxIncreaseSubList {
 
    //分析：对于数组arr进行设计辅助数组dp，dp[i]表示子序列必须以arr[i]位置结尾的最长长度。
    //方法一： 时间复杂度O(N^2)
    public int[] getMaxIncreaseSubList(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        int[] dp = getDp(arr); //获得辅助数组dp  O(N^2)
        int maxIndex = 0;
        for (int i = 1;i < dp.length;i++){
            if (dp[i - 1] < dp[i]){
                maxIndex = i;
            }
        }
        int N = dp[maxIndex];
        int[] subArr = new int[N];
        subArr[--N] = arr[maxIndex];
        for (int i = maxIndex - 1;i >= 0;i--){
            if (arr[i + 1] > arr[i]) {
                subArr[--N] = arr[i];
            }
        }
        return subArr;
    }
 
    //或得以每个位置结尾的最长递增子序列的个数
    public int[] getDp(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1;i < arr.length;i++){
            dp[i] = 1; //初始为1
            for (int j = i - 1;j >= 0;j--){ // 找比i位置小的数的个数
                if (arr[i] > arr[j]){
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
        }
        return dp;
    }
 
    //方法二：时间复杂度O(NlogN)
    public int[] getMaxIncreaseSubList2(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        //通过二分查找的方式，将辅助数组的时间复杂度降低到O(NlogN)
        int[] ends = new int[arr.length];  //存放i+1递增子序列的最小位
        ends[0] = arr[0];
        int len = 1;  //记录ends有效的数据长度
        //采用二分查找的方法查找arr[i]在ends中放在哪里
        for(int i = 1;i < arr.length;i++){
            if (arr[i] > ends[len - 1]){
                ends[len++] = arr[i];
            }else {
                int pos = binarySearch(ends,len,arr[i]);
                ends[pos] = arr[i];
            }
        }
 
        int[] res = new int[len];
        int index = arr.length - 1; //找到ends最后一个数，以该数向前搜索即可
        for(;index >= 0;index--){
            if (arr[index] == ends[len - 1]){
                break;
            }
        }
        res[--len] = ends[len];
        for (int i = index - 1;i >= 0 && len >= 0;i--){
            if (arr[i + 1] > arr[i]) {
                res[--len] = arr[i];
            }
        }
        return res;
    }
 
    //查找ends中  ends[i-1] < target end[i] > target
    public int binarySearch(int[] ends,int len,int target){
        int left = 0;
        int right = len - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(ends[mid] > target){
                right = mid - 1;
            }else if (ends[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return left;
    }
 
    @Test
    public void test(){
        int[] arr = {3,1,2,6,3,4,0};
        int[] maxIncreaseSubList = getMaxIncreaseSubList2(arr);
        System.out.println(Arrays.toString(maxIncreaseSubList));
    }
 
 
}