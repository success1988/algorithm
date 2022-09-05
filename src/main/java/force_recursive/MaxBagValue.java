package force_recursive;

/**
 * 背包的最大价值
 * @author admin
 * @date 2022/9/5 22:20
 */
public class MaxBagValue {


    /**
     * 从i往后（包含i）背包所装物品的最大价值
     * @param weights 重量数组
     * @param values 价值数组
     * @param i 第几个物品
     * @param alreadyWeight 背包现在的重量
     * @param bag 背包的最大重量
     * @return 最大价值
     */
    private static int process1(int[] weights, int[] values,int i, int alreadyWeight, int bag){
        if(alreadyWeight > bag){
            return 0;
        }
        if(i == weights.length){
            return 0;
        }

        //装第i件物品
        int withCurrentObject = values[i] + process1(weights, values, i+1, alreadyWeight+weights[i], bag);
        //不装第i件物品
        int withoutCurrentObject = process1(weights, values, i+1, alreadyWeight, bag);
        return Math.max(withCurrentObject, withoutCurrentObject);
    }

    


}
