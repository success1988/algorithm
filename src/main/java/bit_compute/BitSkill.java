package bit_compute;

/**
 * @Author admin
 * @Date 2022/8/7
 */
public class BitSkill {

    /**
     * 利用位的异或运算进行交换操作
     */
    public static void swap(){
        int a = 23;
        int b = 45;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a:"+a+"====b:"+b);
    }


    public static void findOneAppearOdd(){
        int[] arr = {2,3,4,3,4,2,2,7,2,5,6,6,5};

        int result = 0;
        //将数组中所有的数都进行异或，那些出现偶数次的数字就抵消掉了
        for(int i=0; i<arr.length; i++){
            result ^= arr[i];
        }
        System.out.println("数组中出现奇数次的一种数为:"+result);
    }


    public static void findTwoAppearOdd(){
        int[] arr = {2,3,4,3,4,2,2,7,2,5,6,6,5,8};

        int result = 0;
        //将数组中所有的数都进行异或，那些出现偶数次的数字就抵消掉了
        for(int i=0; i<arr.length; i++){
            result ^= arr[i];
        }

        //查找result最右边为1的数字
        int rightOne = result & (~result + 1);
        int oneTarget = 0;
        for(int i=0; i<arr.length; i++){
            if((rightOne & arr[i]) > 0){
                oneTarget ^=  arr[i];
            }
        }

        int anotherTarget = result ^ oneTarget;
        System.out.println("查找结果为:"+oneTarget +"======="+ anotherTarget);
    }


    /**
     * 校验一个数是否为2的整数次幂
     * @param a
     * @return
     */
    private static boolean check2Pow(int a) {
        //去掉最右侧的1以后，理论上该变为0的
        return (a & (a-1)) == 0;
    }

    /**
     * 位操作统计二进制中 1 的个数
     * @param m
     * @return
     */
    private static int getNumOfOne(int m){
        int count = 0;
        while(m > 0){
            //该操作会卸下m最右侧的1
            m = m & (m-1);
            count++;
        }
        return count;
    }



}
