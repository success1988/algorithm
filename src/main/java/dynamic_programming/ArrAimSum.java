package dynamic_programming;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangcg
 * @date 2022/9/21 19:16
 */
public class ArrAimSum {

    public static void main(String[] args) {

        int[] arr = {4,2,5,7,4,8,10,23,15};
        int aim = 49;
        boolean hasAimSum = checkHasAimSum(arr, aim);
        System.out.println("是否存在元素组合后的和为目标值:"+hasAimSum);
    }

    private static boolean checkHasAimSum(int[] arr, int aim) {

        //每个元素都选择要或者不要, 跟全排列类似，得出各种组合的结果
        Set<Integer> currSumSet = new HashSet<>();
        process(arr, 0, 0, currSumSet);

        System.out.println(currSumSet);
        return currSumSet.contains(aim);
    }

    /**
     * 从currIndex到arr.length-1之间可以组合出来的和
     * @param arr
     * @param currIndex
     * @param currSum
     * @param currSumSet
     * @return
     */
    private static void process(int[] arr, int currIndex, int currSum, Set<Integer> currSumSet){

        if(currIndex == arr.length){
            currSumSet.add(currSum);
            return;
        }

        //要当前位置上的值
        process(arr, currIndex+1, currSum+arr[currIndex], currSumSet);

        //不要当前位置上的值
        process(arr, currIndex+1, currSum, currSumSet);

    }

}