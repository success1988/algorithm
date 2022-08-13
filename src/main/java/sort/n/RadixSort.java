package sort.n;

import java.util.Arrays;

/**
 * 基数排序
 * @Author admin
 * @Date 2022/8/14
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {234,21,3243,45443,2,3143,23,100,1777,889,66};
        int maxDigit = getMaxDigit(arr);
        radixSort(arr, 0, arr.length-1, maxDigit);
        System.out.println(Arrays.toString(arr));
    }



    /**
     * 基数排序
     * @param arr
     * @param L
     * @param R
     * @param digit 最大数字的位数
     */
    public static void radixSort(int[] arr, int L, int R, int digit){
        final int radix = 10;
        //源数组的下标
        int i = 0;
        //“前缀和”数组的下标
        int j = 0;
        //辅助空间，与源数组等规模
        int[] bucket = new int[R - L +1];
        //有多少位，就要进出桶多少次
        for (int d = 1; d <= digit; d++) {
            //“前缀和”数组
            int[] count = new int[radix];
            for (i = L; i <= R ; i++) {
                //计算一个数字在特定位上的值
                j = getDigit(arr[i], d);
                //计数
                count[j]++;
            }

            //将计数结果变形为前缀和
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i-1];
            }

            //对源数组进行到倒序排列
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j]-1] = arr[i];
                count[j]--;
            }

            //将该轮处理结果放回源数组
            for (i = L ,j = 0; i <= R; i++,j++) {
                arr[i] = bucket[j];
            }
        }
    }

    //12304  百位上是3 ，如何计算呢?   先整除100，得到123， 再对10取模，结果是3
    private static int getDigit(int x, int d) {
        return (x/(int)(Math.pow(10, d-1)) % 10);
    }

    //12304  有5位，
    private static int getMaxDigit(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length ; i++) {
            max = Math.max(max, arr[i]);
        }

        int digit = 0;
        while (max > 0) {
            max /= 10;
            digit++;
        }
        return digit;
    }

}
