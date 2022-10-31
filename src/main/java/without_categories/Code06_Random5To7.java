package without_categories;

/**
 * 给定一个函数f，可以1~5的数字等概率返回一个。请加工出1~7的数字等概率返回一个的函数g。
 */
public class Code06_Random5To7 {
    
    public static int f(){
        return (int)(Math.random() * 5) + 1;
    }
    
    //等概率返回0，1
    public static int r01(){
        int res = 0;
        do{
            res = f();
        }while (res == 3);
        return res < 3 ? 0 : 1;
    }
    
    //通过二进制位拼
    //返回1~7 相当于就是等概率返回0~6 + 1
    public static int g(){
        int res = 0;
        do{
            res = (r01() << 2) + (r01() << 1) + r01();   //0~7
        }while (res == 7);
        return res + 1;
    }
}