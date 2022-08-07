package binary;

public class FindNumCount {
    public static void main(String[] args) {
        int[] arr = {1,2,3,3,3,3,3,4,4,5,5,5,5,6,7,7,7,8};
        int targetNum = 3;
        int count = getCountOfNum(arr, targetNum);
        System.out.println(targetNum+"出现的次数为:"+count);
    }

    private static int getCountOfNum(int[] arr, int targetNum) {

        if(arr == null || arr.length == 0){
            return 0;
        }

        FindResult firstIndexResult = getFirstIndexOfTargetNum(arr, targetNum);
        int firstIndex = firstIndexResult.firstIndex;
        if(firstIndex == -1){
            return 0;
        }

        int closestRightIndex = firstIndexResult.closestRightIndex;
        System.out.println(targetNum+"第一次出现的索引为:"+firstIndex);
        System.out.println("二分查找过程中,第一次找到"+targetNum+"时的right索引为:"+closestRightIndex);

        if(arr[closestRightIndex] == targetNum){
            return closestRightIndex+1-firstIndex;
        }

        int left = firstIndex;
        int right = closestRightIndex;
        while(left <= right){
            int mid = left + ((right-left) >> 1);
            if(arr[mid] > targetNum){
                right = mid-1;
            }else {
                if(arr[mid+1] > targetNum){
                    //此时的mid即为最后一个值等于目标值的索引
                    return mid+1-firstIndex;
                }else{
                    left = mid+1;
                }
            }
        }
        return 0;
    }

    public static class FindResult{
        int firstIndex;
        int closestRightIndex;
    }

    public static FindResult getFirstIndexOfTargetNum(int[] arr, int targetNum){
        FindResult findResult = new FindResult();

        int left = 0;
        int right = arr.length-1;
        int closestRightIndex = arr.length-1;

        while(left <= right){
            int mid = left + ((right-left) >> 1);
            if(arr[mid] < targetNum){
                left = mid+1;
            }else if(arr[mid] > targetNum){
                right = mid-1;
            }else {
                if(arr[right] != targetNum){
                    closestRightIndex = right;
                }
                if(mid == 0 || arr[mid-1] < targetNum){
                    findResult.firstIndex = mid;
                    findResult.closestRightIndex = closestRightIndex;
                    return findResult;
                }else{
                    right = mid-1;
                }
            }
        }
        findResult.firstIndex = -1;
        findResult.closestRightIndex = -1;
        return findResult;
    }

}