package dynamic_programming;

/**
 * 组合目标和
 * @author wangcg
 * @date 2022/9/21 19:16
 */
public class ArrAimSum {

    public static void main(String[] args) {

        int[] arr = {4,2,5,7,4,8,10,23,15};
        int aim = 49;
        boolean hasAimSum = process1(arr, aim, 0, 0);
        System.out.println("[暴力递归]是否存在元素组合后的和为目标值:"+hasAimSum);

        boolean hasAimSum2 = processDp(arr, aim);
        System.out.println("[动态规划]是否存在元素组合后的和为目标值:"+hasAimSum2);

    }


    /**
     * 从currIndex到arr.length-1之间是否可以组合出来的和
     * @param arr
     * @param aim
     * @param currIndex
     * @param currSum
     * @return
     */
    private static boolean process1(int[] arr, int aim, int currIndex, int currSum){

        if(currIndex == arr.length){
            return currSum == aim;
        }

        boolean f1 = process1(arr, aim, currIndex+1, currSum);
        boolean f2 = process1(arr, aim, currIndex+1, currSum+arr[currIndex]);

        return f1 || f2;
    }

    //==========================动态规划版本=============================
    private static boolean processDp(int[] arr, int aim) {
        if(arr == null || arr.length == 0){
            return false;
        }
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if(sum < aim){
            return false;
        }

        boolean[][] dp = new boolean[n+1][sum+1];
        //先对最后一行赋值
        dp[n][aim] = true;
        for (int currIndex = n-1; currIndex >= 0; currIndex--) {
            for (int currSum = 0; currSum <= sum; currSum++) {
                boolean f1 = dp[currIndex+1][currSum];
                boolean f2 = currSum+arr[currIndex] > aim ? false : dp[currIndex+1][currSum+arr[currIndex]];
                dp[currIndex][currSum] = (f1 || f2);
            }
        }
        return dp[0][0];
    }

}