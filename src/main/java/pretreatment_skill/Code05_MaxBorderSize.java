package pretreatment_skill;
 
import org.junit.Test;
 
import java.util.Arrays;

/**
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * eg:
 *
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 *
 * 其中边框全为1的最大正方形的大小为4*4，所以返回4
 */
public class Code05_MaxBorderSize {
 
    public static int getMaxOneBorder(int[][] arr){  //时间复杂度O(N^3)
        if(arr == null){
            return 0;
        }
        int N = arr.length;
        int M = arr[0].length;
        int[][] rightArr = getRightOne(arr);  //右边连续为1的数量（包含该位置）
        int[][] downArr = getDownOne(arr);   //下边连续为1的数量（包含该位置）
//        print(rightArr);
//        System.out.println();
//        print(downArr);
        int maxBorderSize = Integer.MIN_VALUE;
        for(int row = 0;row < N;row++){
            for(int col = 0;col < M;col++){
                //确定正方形的左上角顶点 (row,col)
                // 正方形宽度为 border ，依次枚举
                for (int border = 1;border <= Math.min(N - row,M - col);border++){
                    //判断其四条边的值是不是 1   四个边都需要判断
                    //这里使用预处理的数组信息，可以直接得到右侧或者下面连续1的数量
                    //如果不使用预处理信息，在这里需要对四条边进行遍历，判断每条边的值是不是为1，则整体时间复杂度会上升为O(N^4)
                    if(rightArr[row][col] < border && downArr[row][col + border - 1] < border
                            && rightArr[row + border - 1][col] < border){
                        break;
                    }
                    maxBorderSize = Math.max(maxBorderSize,border);
                }
 
            }
        }
        return maxBorderSize;
    }
 
    //获得该位置以及该位置右侧连续1的数量
    public static int[][] getRightOne(int[][] arr){
        if(arr == null){
            return null;
        }
        int N = arr.length;     //N行
        int M = arr[0].length;  //M列
        int[][] rightArr = new int[N][M];
        for(int row = 0;row <= N - 1;row++){
            rightArr[row][M - 1] = arr[row][M - 1] == 0 ? 0 : 1;
            for(int j = M - 2;j >= 0;j--){
                if(arr[row][j] == 0){
                    rightArr[row][j] = 0;
                }
                if(arr[row][j] == 1){
                    rightArr[row][j] = rightArr[row][j + 1] + 1;
                }
            }
        }
        return rightArr;
    }
 
    //获得该位置以及该位置下面连续1的数量
    public static int[][] getDownOne(int[][] arr){
        if(arr == null){
            return null;
        }
        int N = arr.length;
        int M = arr[0].length;
        int[][] downArr = new int[N][M];
        for(int col = 0;col <= M - 1;col++){
            downArr[N - 1][col] = arr[N - 1][col] == 0 ? 0 : 1;
            for(int j = N - 2;j >= 0;j--){
                if(arr[j][col] == 0){
                    downArr[j][col] = 0;
                }
                if(arr[j][col] == 1){
                    downArr[j][col] = downArr[j + 1][col] + 1;
                }
            }
        }
        return downArr;
    }
 
    public static void print(int[][] arr){
        for(int[] a : arr){
            System.out.println(Arrays.toString(a));
        }
    }
 
    @Test
    public void test(){
 
        int[][] arr = {
                {0,1,1,1,1,1},
                {0,1,0,0,1,1},
                {0,1,0,0,1,1},
                {0,1,1,1,1,1},
                {0,1,0,1,1,1}
        };
        int maxOneBorder = getMaxOneBorder(arr);
        System.out.println(maxOneBorder);
    }
}