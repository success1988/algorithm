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
