package dynamic_programming.makeup;

/**
 * @author admin
 * @date 2022/10/7 17:37
 */
public class MinNumFromUnlimitedFaceValues {


    public static void main(String[] args) {
        //面值数组
        int[] faceValueArray = new int[]{1,2,5,10,20,50,100};
        //要凑的钱数
        int aim = 28;

        int minNum = process(faceValueArray,0, aim);
        System.out.println("【暴力递归】凑出目标钱数的最小张数为:"+ (minNum == Integer.MAX_VALUE ? -1 : minNum));

        int minNum2 = processDp(faceValueArray, aim);
        System.out.println("【动态规划】凑出目标钱数的最小张数为:"+ (minNum2 == Integer.MAX_VALUE ? -1 : minNum2));

        int minNum3 = processDp2(faceValueArray, aim);
        System.out.println("【动态规划-斜率优化】凑出目标钱数的最小张数为:"+ (minNum3 == Integer.MAX_VALUE ? -1 : minNum3));

    }

    private static int processDp2(int[] faceValueArray, int aim) {

        //根据暴力递归的代码，需要两个变量，所以dp表是二维表, 行代表面值元素索引，列代表剩余要凑的钱数
        int N = faceValueArray.length;
        int[][] dp = new int[N+1][aim+1];
        //第N行
        dp[N][0] = 0;
        for (int rest = 1; rest <= aim ; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }

        for (int index = N-1; index >= 0; index--) {

            for (int rest = 0; rest <= aim ; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if(rest - faceValueArray[index] >= 0 && dp[index][rest - faceValueArray[index]] != Integer.MAX_VALUE){
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - faceValueArray[index]]+1);
                }
            }

        }

        return dp[0][aim];
    }

    private static int processDp(int[] faceValueArray, int aim) {

        //根据暴力递归的代码，需要两个变量，所以dp表是二维表, 行代表面值元素索引，列代表剩余要凑的钱数
        int N = faceValueArray.length;
        int[][] dp = new int[N+1][aim+1];
        //第N行
        dp[N][0] = 0;
        for (int rest = 1; rest <= aim ; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }

        for (int index = N-1; index >= 0; index--) {

            for (int rest = 0; rest <= aim ; rest++) {
                //对于每个面值，都有0~rest/faceValue 共rest/faceValue +1种尝试方法
                int finalNum = Integer.MAX_VALUE;
                for (int zhang = 0; zhang*faceValueArray[index] <= rest; zhang++) {
                    int restNum = dp[index+1][rest - zhang*faceValueArray[index]];
                    if(restNum != Integer.MAX_VALUE){
                        finalNum = Math.min(finalNum, restNum+zhang);
                    }
                }
                dp[index][rest] = finalNum;
            }

        }

        return dp[0][aim];
    }

    private static int process(int[] faceValueArray, int index, int rest) {
        if(index == faceValueArray.length){
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        //对于每个面值，都有0~rest/faceValue 共rest/faceValue +1种尝试方法
        int finalNum = Integer.MAX_VALUE;
        for (int zhang = 0; zhang*faceValueArray[index] <= rest; zhang++) {
            int restNum = process(faceValueArray,index+1, rest - zhang*faceValueArray[index]);
            if(restNum != Integer.MAX_VALUE){
                finalNum = Math.min(finalNum, restNum+zhang);
            }
        }
        return finalNum;
    }
}
