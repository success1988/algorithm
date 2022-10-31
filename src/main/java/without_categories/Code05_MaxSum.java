package without_categories;
 
/**
 * 给定一个整型矩阵，返回子矩阵的最大累加和
 *
 * 分析：逐层分析，0~0，0~1，0~2，0~3...1~1，1~2...2~2,2~3...
 * 把每一层对应列加在一起，就变成了一个一维的数组，变成求一维数组的最大累加和
 *
 */
public class Code05_MaxSum {
    
    public int maxSum(int[][] arr){
        if (arr == null || arr.length == 0 || arr[0].length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;  //定义一个一维数组，保存每一列累加起来的值
        for(int i = 0;i < arr.length;i++){  //开始的行号 i
            s = new int[arr[0].length];
            for (int j = i;j < arr.length;j++){  //结束的行号 j  i~j
                cur = 0;
                for (int k = 0;k < s.length;k++){ //每次的 i~j列 的累加值放在 s 数组中
                    s[k] += arr[j][k];
                    cur += s[k];
                    max = Math.max(max,cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }
}