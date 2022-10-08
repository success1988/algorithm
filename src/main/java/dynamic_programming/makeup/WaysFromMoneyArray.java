package dynamic_programming.makeup;

/**
 * @author admin
 * @date 2022/10/7 17:38
 */
public class WaysFromMoneyArray {


    public static void main(String[] args) {
        //每张钱币即使面值相同也视作不同的钱币
        int[] moneyArray = new int[]{1,2,4,3,1,5,10,5,2,2};
        //要凑的钱数
        int aim = 30;

        int ways = process(moneyArray, aim, 0, aim);
        System.out.println("【暴力递归】凑出目标钱数的方法数为:"+ways);


    }

    private static int process(int[] moneyArray, int aim, int index, int rest) {
        if(index == moneyArray.length){
            //正好凑齐，才表示此种凑法是有效的
            return rest == 0 ? 1: 0;
        }

        //使用index位置的钱币
        int w1 = process(moneyArray, aim, index+1, rest-moneyArray[index]);
        //不使用index位置的钱币
        int w2 = process(moneyArray, aim, index+1, rest);
        return w1+w2;
    }


}
