package binary;

public class FindNumCount2 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,3,3,3,4,4,5,5,5,5,6,7,7,7,8};
        int targetNum = 2;
        int count = getCountOfNum(arr, targetNum);
        System.out.println(targetNum+"出现的次数为:"+count);
    }

    private static int getCountOfNum(int[] arr, int targetNum) {

        if(arr == null || arr.length == 0){
            return 0;
        }

        FirstFindResult findResult = getIndexOfTargetNum(arr, targetNum);
        int targetNumIndex = findResult.targetNumIndex;
        if(targetNumIndex == -1){
            return 0;
        }

        int closestLeftIndex = findResult.closestLeftIndex;
        int closestRightIndex = findResult.closestRightIndex;

        System.out.println("标准的二分查找过程中,第一次找到"+targetNum+"时的索引为:"+targetNumIndex);
        System.out.println("标准的二分查找过程中,第一次找到"+targetNum+"时的left索引为:"+closestLeftIndex);
        System.out.println("标准的二分查找过程中,第一次找到"+targetNum+"时的right索引为:"+closestRightIndex);

        //在[closestLeftIndex,targetNumIndex]查找出现targetNum的左边界
        int leftBound = findLeftBoundOfTargetNum(arr, targetNum, closestLeftIndex, targetNumIndex);
        System.out.println(targetNum+"的左边界为:"+leftBound);

        //在[targetNumIndex,closestRightIndex]查找出现targetNum的右边界
        int rightBound = findRightBoundOfTargetNum(arr, targetNum, targetNumIndex, closestRightIndex);
        System.out.println(targetNum+"的右边界为:"+rightBound);

        return rightBound+1-leftBound;
    }

    public static class FirstFindResult{
        int targetNumIndex;
        int closestLeftIndex;
        int closestRightIndex;
    }

    /**
     * 标准的二分查找
     * @param arr
     * @param targetNum
     * @return
     */
    public static FirstFindResult getIndexOfTargetNum(int[] arr, int targetNum){
        FirstFindResult findResult = new FirstFindResult();

        int closestLeftIndex = 0;
        int closestRightIndex = arr.length-1;

        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int mid = left + ((right-left) >> 1);
            if(arr[mid] < targetNum){
                left = mid+1;
            }else if(arr[mid] > targetNum){
                right = mid-1;
            }else {
                closestLeftIndex = left;
                closestRightIndex = right;

                findResult.targetNumIndex = mid;
                findResult.closestLeftIndex = closestLeftIndex;
                findResult.closestRightIndex = closestRightIndex;
                return findResult;
            }
        }

        findResult.targetNumIndex = -1;
        findResult.closestLeftIndex = closestLeftIndex;
        findResult.closestRightIndex = closestRightIndex;
        return findResult;
    }


    /***
     * 查找左边界：查找第一个值等于目标值的索引位置
     */
    public static int findLeftBoundOfTargetNum(int[] arr, int targetNum, int closestLeftIndex, int targetNumIndex){
        int left = closestLeftIndex;
        int right = targetNumIndex;

        while(left <= right){
            int mid = left + ((right-left) >> 1);
            if(arr[mid] < targetNum){
                left = mid+1;
            }else if(arr[mid] > targetNum){
                right = mid-1;
            }else {
                if(mid == closestLeftIndex || arr[mid-1] < targetNum){
                    return mid;
                }else{
                    right = mid-1;
                }
            }
        }
        //不会走到这里
        return -1;
    }

    /***
     * 查找右边界：查找最后一个值等于目标值的索引位置
     */
    public static int findRightBoundOfTargetNum(int[] arr, int targetNum, int targetNumIndex, int closestRightIndex){
        int left = targetNumIndex;
        int right = closestRightIndex;

        while(left <= right){
            int mid = left + ((right-left) >> 1);
            if(arr[mid] < targetNum){
                left = mid+1;
            }else if(arr[mid] > targetNum){
                right = mid-1;
            }else {
                if(mid == closestRightIndex || arr[mid+1] > targetNum){
                    return mid;
                }else{
                    left = mid+1;
                }
            }
        }
        //不会走到这里
        return -1;
    }
}