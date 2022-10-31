package bit_compute;

/**
 * 快速幂（Exponentiation by squaring，平方求幂）是一种简单而有效的小算法，
 * 它可以以O（logN）的时间复杂度计算乘方
 *
 * 我们换一个角度来引入快速幂。还是7的10次方，
 * 但这次，我们把10写成二进制的形式，也就是 1010。于是这个问题就变成了求7的二进制（1010）次幂
 * @author wangcg
 * @date 2022/10/31 17:40
 */
public class QuickPow {
    //快速幂
    public int quickPow(int a, int n){
        int ans = 1;
        while(n>0){
            if( (n&1) > 0) {       //如果n的当前末位为1
                ans *= a;  //ans乘上当前的a
            }
            a *= a;        //a自乘
            n >>= 1;       //n往右移一位,表示除以2
        }
        return ans;
    }
}
