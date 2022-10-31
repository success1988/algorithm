package without_categories;
 
import org.junit.Test;
 
/**
 * 正方形矩阵翻转90度
 */
public class Code04_SquareFlipped90 {
 
    //根据螺旋打印，进行每次的边界翻转，每次四条边上的数据一起变
    //  arr为正方形数组
    public int[][] squareFlipped90(int[][] arr){
        int N = arr.length;
        int M = arr[0].length;
        //定义左上角和右下角  由于正方形，左上角和右下角的行列相等
        int left = 0;
        int right = N - 1 - left;
        int temp = 0;
        while (left < right){  //对边框数据进行变换 因为是正方形，left=right时只有一个值，省去
            for (int i = left,j = right;i < right;i++,j--){
                temp = arr[left][i];
                arr[left][i] = arr[j][left];  //左边框 -> 上边框
                arr[j][left] = arr[right][j]; // 下边框 -> 左边框
                arr[right][j] = arr[i][right]; // 下边框 -> 右边框
                arr[i][right] = temp;  //右边框 -> 上边框
            }
            left++;
            right--;
        }
        return arr;
    }
}