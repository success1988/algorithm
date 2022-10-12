package dynamic_programming.makeup;

/**
 * @author admin
 * @date 2022/10/7 17:38
 */
public class WaysFromUnlimitedFaceValues {


    public static void main(String[] args) {
        //面值数组
        int[] faceValueArray = new int[]{1,2,5,10,20,50,100};
        //要凑的钱数
        int aim = 29;

        int ways = process(faceValueArray,0, aim);
        System.out.println("【暴力递归】凑出目标钱数的方法数为:"+ways);

        int waysDp = processDp(faceValueArray, aim);
        System.out.println("【动态规划】凑出目标钱数的方法数为:"+waysDp);


        int waysDp2 = processDp2(faceValueArray, aim);
        System.out.println("【动态规划-斜率优化】凑出目标钱数的方法数为:"+waysDp2);

    }

    private static int processDp(int[] faceValueArray, int aim) {
        int N = faceValueArray.length;
        int[][] dp = new int[N+1][aim+1];
        //对第N行赋值，由于默认值未0，无需对其他元素处理
        dp[N][0] = 1;

        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang*faceValueArray[index] <= rest; zhang++) {
                    ways += dp[index+1][rest - zhang*faceValueArray[index]];
                }
                dp[index][rest] = ways;
            }
        }

        return dp[0][aim];
    }

    private static int processDp2(int[] faceValueArray, int aim) {
        int N = faceValueArray.length;
        int[][] dp = new int[N+1][aim+1];
        //对第N行赋值，由于默认值未0，无需对其他元素处理
        dp[N][0] = 1;

        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if(rest-faceValueArray[index] >= 0){
                    dp[index][rest] += dp[index][rest-faceValueArray[index]];
                }
            }
        }

        return dp[0][aim];
    }

    private static int process(int[] faceValueArray, int index, int rest) {
        if(index == faceValueArray.length){
            return rest == 0 ? 1 : 0;
        }

        //对于每个面值faceValue，都有0~rest/faceValue 共rest/faceValue +1种尝试方法
        int ways = 0;
        for (int zhang = 0; zhang*faceValueArray[index] <= rest; zhang++) {
            ways += process(faceValueArray,index+1, rest - zhang*faceValueArray[index]);
        }
        return ways;
    }

}
