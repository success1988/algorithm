package dynamic_programming.split_sum_closed;

/**
 * 给定一个正数数组arr
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：最接近的情况下，较小集合的累加和
 *
 * @author admin
 * @date 2022/10/7 21:07
 */
public class WithNumLimit {


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

        if((arr.length & 1) == 0){
            return process(arr, 0, arr.length/2, sum/2);
        }else{
            return Math.max(process(arr, 0, arr.length/2, sum/2)
                    ,process(arr, 0, arr.length/2 +1, sum/2));
        }

    }

    private static int process(int[] arr, int index, int restNum, int restSum) {
        if(index == arr.length){
            return restNum == 0 ? 0 : -1;
        }else {
            int p1 = process(arr, index+1, restNum, restSum);

            int p2 = -1;
            int next = -1;
            if(arr[index] <= restSum){
                next =  process(arr, index+1, restNum-1, restSum-arr[index]);
            }
            if(next != -1){
                p2 = arr[index]+next;
            }
            return Math.max(p1, p2);
        }
    }
}
