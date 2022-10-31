package without_categories;

/**
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的作为右部分。
 * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，左部分最大值减去右部分最大值的绝对值。
 */
public class Code06_MaxArrayLeftRightSub {
 
    //先找出数组的最大值，在判断左边第一个和右边第一个的差值即可
    public int getSubMaxLeftRight(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int max = Integer.MIN_VALUE;
        for(int i : arr){
            max = Math.max(max,i);
        }
        //当最大值包含在左侧时，需要找到右侧相对较小的最大值，而右侧一定会包含arr[arr.length - 1]
        // 当右侧最后一个数为右侧最大值时，右侧怎么变化，左右两侧最大值差值也是 max - arr[arr.length - 1]
        // 当右侧最后一个数不是右侧最大值时，那么左右两个的最大差值会变小，是不会取到的
        // 同理，最大值包含在右侧，只需要找左侧相对较小的最大值，而arr[0]是一定包含的
        // 因此，最后只需要判断arr[0] 和 arr[arr.length - 1]两个数的大小，再返回即可
        return arr[0] > arr[arr.length - 1] ? max - arr[arr.length - 1] : max - arr[0];
    }
}