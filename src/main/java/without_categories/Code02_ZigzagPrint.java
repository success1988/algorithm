package without_categories;
 
import org.junit.Test;
 
/**
 * 【宏观调控】
 * 用zigzag的方式打印矩阵，比如如下的矩阵
 *
 * 0 1 2 3
 *
 * 4 5 6 7
 *
 * 8 9 10 11
 *
 * 打印顺序为：0 1 4 8 5 2 3 6 9  10 7 11
 */
public class Code02_ZigzagPrint {
 
    //给两个点，都是在左上角，一个向右，一个向下，打印斜角
    public void zigzagPrint(int[][] arr){
        //定义两个点，都在左上角
        int a_row = 0;
        int a_col = 0;
        int b_row = 0;
        int b_col = 0;
        int N = arr.length - 1;
        int M = arr[0].length - 1;
        boolean flag = false;
        while (a_row != N + 1){
            printObliqueLine(arr,a_row,a_col,b_row,b_col,flag);
            //a 点向右移动，当到达末端时，向下移动
            a_row = a_col == M ? a_row + 1 : a_row;  //a点先判断行，在判断列 （列的变化会导致行变化）
            a_col = a_col == M ? a_col : a_col + 1;
            //b 点向下移动，当到达底端时，向右移动
            b_col = b_row == N ? b_col + 1 : b_col;  //b点先判断列，在判断行 （行的变化会导致列变化）
            b_row = b_row == N ? b_row : b_row + 1;
            flag = !flag;
        }
        System.out.println();
    }
 
    public void printObliqueLine(int[][] arr,int a_row,int a_col,int b_row,int b_col,boolean flag){
        if(flag){
            while (a_row != b_row + 1){  //上向下打印
                System.out.print(arr[a_row++][a_col--] + "\t");
            }
        }else {
            while (b_col != a_col + 1){  //下向上打印
                System.out.print(arr[b_row--][b_col++] + "\t");
            }
        }
    }
}