package binary;

/**
 * @Title：局部最小值问题
 * @Author：wangchenggong
 * @Date 2022/8/5 7:21
 * @Description
 * @Version
 */
public class LocalMinValue {

    public static void main(String[] args) {
        int[] arr = {6,5,7,2,3,9,4,8};
        int localMinValueIndex = findLocalMinValueIndex(arr);
        System.out.println("局部最小值的索引位置为:"+localMinValueIndex+",对应的值为："+arr[localMinValueIndex]);
    }

    private static int findLocalMinValueIndex(int[] arr) {
        if(arr == null || arr.length == 0){
            return -1;
        }
        if(arr.length == 1){
            return 0;
        }

        if(arr[0] < arr[1]){
            return 0;
        }

        if(arr[arr.length-1] < arr[arr.length-2]){
            return arr.length-1;
        }

        //在[1,arr.length-2]查找局部最小值
        int left = 1;
        int right = arr.length-2;
        while(left<=right){
            int mid = left+((right-left)>>1);
            if(arr[mid] > arr[mid-1]){
                right = mid-1;
            }else if(arr[mid] > arr[mid+1]){
                left = mid+1;
            }else{
                //没有左边大，也没有右边大，又由于各个元素值不相等，所以此时arr[mid]同时小于左右两侧的值
                return mid;
            }
        }
        return -1;
    }
    
}