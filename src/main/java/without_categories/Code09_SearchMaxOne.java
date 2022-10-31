package without_categories;
 
import java.util.ArrayList;
import java.util.List;
 
/**
 * 对于一个只有01的二维数组，0始终在1的左边，返回具有1最多的行。
 */
public class Code09_SearchMaxOne {
 
    public static List<Integer> getMaxOne(int[][] arr){
        int N = arr.length;
        int M = arr[0].length;
        int row = 0;
        int col = M - 1;  //初始化位置在左上角
        List<Integer> list = new ArrayList<>();
        int maxSize = 0;
        while (row < N && col >= 0){
            int cur = maxSize;
            if(arr[row][col] == 1) {
                while (col - 1 >= 0 && arr[row][col - 1] == 1) {
                    col--;
                    maxSize++;
                }
                //如果最大值变化，则需要把原来的最大值行数全部清空
                //如果最大值没有变化，则直接添加新的行就可
                if(maxSize != cur){
                    list.clear();
                }
                list.add(row);
            }
            row++;
        }
        return list;
    }
}