package dynamic_programming;

/**
 * @author wangcg
 * @date 2022/9/22 12:15
 */
public class ZeroOneBag {

    public static void main(String[] args) {

        int[] w = new int[]{3, 4, 1, 5, 9,12,7};
        int[] v = new int[]{12,5,20,15,19,2, 13};
        int bag = 20;

        int maxValue = getMaxValue(w, v, bag);
        System.out.println("暴力递归 最大价值为:"+maxValue);

        int maxValue2 = getMaxValue2(w, v, bag);
        System.out.println("动态规划 最大价值为:"+maxValue2);

    }

    private static int getMaxValue(int[] w, int[] v, int bag) {
        if(w == null || w.length == 0 || v == null || v.length == 0 || w.length != v.length || bag < 0){
            return 0;
        }

        return process(0, bag, w, v);
    }

    private static int process(int index, int rest, int[] w, int[] v) {
        if(index == w.length){
            return 0;
        }
        //不要当前的物品
        int p1 = process(index+1, rest, w, v);
        //要当前物品，前提是不超重
        int p2 = 0;
        if(rest >= w[index]){
            p2 = v[index] + process(index+1, rest-w[index], w, v);
        }
        return Math.max(p1, p2);
    }

    //=====================================动态规划==================================
    private static int getMaxValue2(int[] w, int[] v, int bag) {
        if(w == null || w.length == 0 || v == null || v.length == 0 || w.length != v.length || bag < 0){
            return 0;
        }

        int N = w.length;
        //用行来表示index的取值， 用列来表示rest的取值
        int[][] dpMap = new int[N+1][bag+1];
        
        //由于dpMap[index][?]仅仅依赖dpMap[index+1][?]的取值
        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest <= bag ; rest++) {
                //不要当前的物品
                int p1 = dpMap[index+1][rest];
                //要当前物品，前提是不超重
                int p2 = 0;
                if(rest >= w[index]){
                    p2 = v[index] + dpMap[index+1][rest-w[index]];
                }
                dpMap[index][rest] = Math.max(p1, p2);
            }
        }
        return dpMap[0][bag];
    }


}