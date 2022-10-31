package bit_compute;

/**
 * 给定两个有符号32位整数a和b，不能使用算术运算符，
 * 分别实现a和b的加、减、乘、除运算
 * [如果给定a、b执行加减乘除的运算结果就会导致数据的溢出，
 * 那么你实现的函数不必对此负责，除此之外请保证计算过程不发生溢出]
 */
public class JiaJianChengChu {
 
    public static int add(int a,int b){
        int sum = a;
        while (b != 0){
            sum = a ^ b;  //相当于无进位相加
            b = (a & b) << 1;  //进位信息
            a = sum;
        }
        return sum;
    }
 
    public static int negNum(int n){
        return add(~n,1);
    }
 
    public static int minus(int a,int b){ //a - b = a + (-b)  b的相反数就是b取反加1
        return add(a,negNum(b));
    }
 
    public static int multi(int a,int b){
        int res = 0;
        while (b != 0){
            if((b & 1) != 0){
                res = add(res,a);
            }
            a <<= 1;  //左移
            b >>>= 1;  //右移，看最后一位是不是1
        }
        return res;
    }
 
    public static boolean isNeg(int n){
        return n < 0;
    }
 
    public static int div(int a,int b){  //b左移多少位（扩大倍数）,可以减掉的话，对应位数位1
        //先把数都变为正数
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for(int i = 31;i > -1;i--){  //只能处理正数
            //右移比左移安全
            if((x >> i) >= y){ //x右移多少位还比y大，说明x至少是y的 2^i 倍  (y << i) <= x可能会有溢出的情况
                res |= (1 << i); //把对应位置为1
                x = minus(x,y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }
}