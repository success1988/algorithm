package without_categories;
 
import org.junit.Test;
 
import javax.swing.*;
 
/**
 * 给定一个数组arr，如果通过调整可以做到arr中任意两个相邻的数字相乘是4的倍数，返回true；如果不能返回false。
 */
public class Code01_Multi4 {
 
    //先遍历一遍数组，找到奇数个数，偶数中只含有一个2因子的个数，和包含4因子的个数
    public boolean Can4(int[] arr){
        int oddNum = 0;
        int num_2 = 0;
        int num_4 = 0;
        for (int i = 0;i < arr.length;i++){
            if (isOddNum(arr[i])){
                oddNum++;
            }else {
                if (arr[i] % 4 == 0){
                    num_4++;
                }else {
                    num_2++;
                }
            }
        }
        //判断只有一个2因子的个数
        //没有2因子，说明只有奇数和4的倍数，那么 奇4奇4奇4.....  这样排列即可
        //有2的因子，两个个2排列在一起可以成为4的倍数 22222，最后一个2后面需要加4的倍数
        // 即 222224...  那么排列方式 2222...4奇4奇4.....这样
        if(num_2 == 0){
            return oddNum == 0 ? (num_4 >= 1 ? true : false) : (num_4 >= oddNum - 1 ? true : false);
        }else {
            return num_4 >= oddNum ? true : false;
        }
    }
 
    //判断一个数是不是奇数
    public boolean isOddNum(int n){
        return n % 2 == 0 ? false : true;
    }
}