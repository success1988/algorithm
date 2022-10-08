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
        int aim = 4;

        int ways = process(faceValueArray, aim, 0, aim);
        System.out.println("【暴力递归】凑出目标钱数的方法数为:"+ways);

    }

    private static int process(int[] faceValueArray, int aim, int index, int rest) {
        if(index == faceValueArray.length){
            return rest == 0 ? 1 : 0;
        }

        //对于每个面值，都有0~rest/faceValue 共rest/faceValue +1种尝试方法
        int ways = 0;
        for (int i = 0; i <= rest/faceValueArray[index]; i++) {
            ways += process(faceValueArray, aim, index+1, rest - i*faceValueArray[index]);
        }
        return ways;
    }

}
