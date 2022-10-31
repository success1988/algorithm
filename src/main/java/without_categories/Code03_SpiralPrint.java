package without_categories;
 
import org.junit.Test;
 
/**
 * 螺旋打印
 *
 * 用螺旋的方式打印矩阵，比如如下的矩阵：
 *
 * 0 1 2 3
 *
 * 4 5 6 7
 *
 * 8 9 10 11
 *
 * 打印顺序为：0 1 2 3 7 11 10 9  8 4 5 6
 */
public class Code03_SpiralPrint {
 
    //定义左上角和右下角形成一个框，来打印框的边界，再缩小框，继续打印即可
    public void spiralPrint(int[][] arr){
        if(arr == null || arr.length == 0){
            System.out.println();
        }
        int leftRow = 0;  //左上角
        int leftCol = 0;
        int rightRow = arr.length - 1;  //右下角
        int rightCol = arr[arr.length - 1].length - 1;
        while (leftCol < rightCol && leftRow < rightRow){  //打印标准情况下的数字（左上角和右上角不同行也不同列）
            for (int i = leftCol;i <= rightCol;i++){
                System.out.print(arr[leftRow][i] + "\t");
            }
            for (int i = leftRow + 1;i <= rightRow;i++){
                System.out.print(arr[i][rightCol] + "\t");
            }
            for (int i = rightCol - 1;i >= leftCol;i--){
                System.out.print(arr[rightRow][i] + "\t");
            }
            for (int i = rightRow - 1;i >= leftRow + 1;i--){
                System.out.print(arr[i][leftCol] + "\t");
            }
            leftCol++;   //左上角下移
            leftRow++;
            rightCol--;   //右下角上移
            rightRow--;
        }
        //剩余同行或者同列的没有打印
        if (leftRow == rightRow){
            for (int i = leftCol;i <= rightCol;i++){
                System.out.print(arr[leftRow][i] + "\t");
            }
        }else if(leftCol == rightCol){
            for (int i = leftRow;i <= rightRow;i++){
                System.out.print(arr[i][rightCol] + "\t");
            }
        }
    }
}