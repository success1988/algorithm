package without_categories;
 
import org.junit.Test;
 
/**
 * 给定一个元素为非负整数的二维数组matrix，每行和每列都是从小到达排序的。
 * 再给定一个非负整数aim，请判断aim是否在matrix中。
 */
public class Code08_SearchInDoubleArray {
 
    //从右上角开始查询  查询最多一行+一列
    // arr 行列是从小到大的 非负整数
    public static boolean isInDoubleArray(int[][] arr,int num){
        if(arr == null || num < 0){
            return false;
        }
        int N = arr.length;
        int M = arr[0].length;
        int row = 0;
        int col = M - 1;  //初始位置在右上角
        while(row < N && col >= 0){
            int cur = arr[row][col];
            if(cur > num){
                col--;
            }else if(cur < num) {
                row++;
            }else {
                return true;
            }
        }
        return false;
    }
}