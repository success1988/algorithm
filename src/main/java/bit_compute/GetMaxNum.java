package bit_compute;

/**
 * 给定两个有符号32位整数a和b，返回a和b中较大的。[不用做任何判断]
 */
public class GetMaxNum {
    
    //保证参数n，不是1就是0的情况下
    //n = 1 -->  0
    //n = 0 -->  1
    public static int flip(int n){
        return n ^ 1;
    }
    
    //n是非负数，返回1
    //n是负数，返回0
    public static int sign(int n){
        return flip( (n >> 31) & 1 );  //取出n的符号位位
    }
    
    public static int getMax1(int a,int b){
        int c = a - b;   //可能会溢出
        int scA = sign(c);    //a-b非负，scA为 1；a-b为负，scA为 0
        int scB = flip(scA);  //scA 为0，scB为1； scA为1，scB为0
        return a * scA + b * scB;
    }
    
    public static int getMax2(int a,int b){
        int c = a - b;
        int sa = sign(a);  //a的符号状态 ，非负为1，负数为0
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb; //a和b的符号不一样，为1；一样，为0
        int sameSab = flip(difSab);  //a和b的符号一样，为1，不一样，为0
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }
}