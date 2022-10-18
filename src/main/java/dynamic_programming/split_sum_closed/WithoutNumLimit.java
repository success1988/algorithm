package dynamic_programming.split_sum_closed;

/**
 * 给定一个正数数组arr
 * 请把arr中所有的数分成两个集合，两个集合的元素数量相差不超过1， 尽量让两个集合的累加和接近
 * 返回：最接近的情况下，较小集合的累加和
 * @author admin
 * @date 2022/10/7 21:07
 */
public class WithoutNumLimit {


    public static void main(String[] args) {
        int[] arr = {2,5,3,6,9,1,7,4};
        int lowSum = getLowSum(arr);
        System.out.println("【暴力递归】最接近的情况下，较小集合的累加和为:"+lowSum);
    }

    private static int getLowSum(int[] arr) {
        if(arr == null || arr.length < 2){
            return 0;
        }
        int sum = 0;
        for(int num : arr){
            sum += num;
        }
        return process(arr,  0,   sum/2);
    }

    //从index开始往后的lowSum
    private static int process(int[] arr, int index, int rest) {
        if(index == arr.length){
            return 0;
        }else {
            //不要index位置的元素
            int p1 = process(arr, index+1, rest);

            //要index位置的元素
            int p2 = rest-arr[index] >= 0 ? process(arr, index+1, rest-arr[index]) : 0;

            //取两者的最大值
            return  Math.max(p1, p2);
        }

    }


}
