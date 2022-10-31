package bit_compute;

/**
 * 判断一个32位正数是不是2的幂、4的幂
 */
public class TwoOrFourPower {
    
    //看这个数是不是只有一个1  
    // 2^0 = ...00000001;2^1 = ...00000010;2^2 = ...00000100;2^3 = ...00001000
    public static boolean is2Power(int n){
        return (  n & (n - 1)  ) == 0;//x&(x – 1)是将x的最后一个1置0
    }
    
    //一个数是4的幂一定先是2的幂
    public static boolean is4Power(int n){
                                             //......01010101 4的幂位是1
        return (  n & (n - 1)  ) == 0 && ( n & 0x55555555 ) != 0;
    }
}