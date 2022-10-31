package without_categories;
 
import org.junit.Test;
 
/**
 * 最高分定义：用户所有打分记录中，连续打分数据之和的最大值即认为是帖子曾经获得的最高分。
 * 。例如：帖子10001010近期打分记录为：
 * [1,1,-1,-10,11,4,-6,9,20,-2]，那么该条帖子曾经达到过的最高分数为11+4+(-6)+9+20=38。
 */
public class Code04_GradeSystem {
    
    public int maxGrade1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < arr.length;i++){
            cur += arr[i];
            max = Math.max(max,cur);
            cur = cur < 0 ? 0 : cur;   //cur小于0说明之前累加和负数
        }
        return max;
    }
 
    //暴力解法，时间复杂度为O(N^2)
    public int maxGrade2(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int[] sum_01 = new int[arr.length];  //记录第一位到当前位置的总和
        sum_01[0] = arr[0];
        for(int i = 1;i < sum_01.length;i++){
            sum_01[i] = sum_01[i-1] + arr[i];
        }
        int maxGrade = sum_01[0];
        for (int i = 1;i < sum_01.length;i++){  //这个里面会丢失与sum_01[0]的比较
            for (int j = i;j < sum_01.length;j++){
                int cur = sum_01[j] - sum_01[i - 1];
                maxGrade = Math.max(maxGrade,cur);
            }
        }
        return maxGrade;
    }
 
    @Test
    public void test(){
        int[] arr = {1,-1,-1,-10,11,-4,-6,-9,-20,-2};
        System.out.println(maxGrade2(arr));
    }
}