package dynamic_programming.makeup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2022/10/7 17:37
 */
public class WaysFromLimitedFaceValues {

    public static void main(String[] args) {
        //纸币数组
        int[] moneyArray = new int[]{1,1,1,2,5,10,100,20,20,2,5,10,20,50,100};
        //要凑的钱数
        int aim = 59;

        int ways = getWays(moneyArray, aim);
        System.out.println("【暴力递归】凑出目标钱数的方法数为:"+ways);

    }

    private static int getWays(int[] moneyArray, int aim) {
        //先将moneyArray加工处理为两个数组：面值数组及对应的数量数组
        Map<Integer,Integer> faceValueNumMap = new HashMap<>();
        for(int faceValue : moneyArray){
            if(faceValueNumMap.containsKey(faceValue)){
                faceValueNumMap.put(faceValue,faceValueNumMap.get(faceValue)+1);
            }else{
                faceValueNumMap.put(faceValue, 1);
            }
        }

        int[] faceValues = new int[faceValueNumMap.size()];
        int[] moneyNums = new int[faceValueNumMap.size()];
        int i = 0;
        for(Map.Entry<Integer,Integer> entry : faceValueNumMap.entrySet()){
            faceValues[i] = entry.getKey();
            moneyNums[i] = entry.getValue();
            i++;
        }

        return process(faceValues, moneyNums, 0, aim);
    }


    private static int process(int[] faceValues, int[] moneyNums, int index, int rest) {
        if(index == faceValues.length){
            return rest == 0 ? 1 : 0;
        }

        //对于每个面值，都可以选取0~Math.min(rest/faceValues[index],moneyNums[index])张进行尝试
        int ways = 0;
        for (int zhang = 0; zhang*faceValues[index] <= rest && zhang <= moneyNums[index]; zhang++) {
            ways += process(faceValues, moneyNums, index+1, rest - zhang*faceValues[index]);
        }
        return ways;
    }
}
