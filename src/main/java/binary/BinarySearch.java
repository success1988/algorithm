package binary;

/**
 * @Author admin
 * @Date 2022/8/7
 */
public class BinarySearch {
    /**
     * 经典的二分查找： 数组中的元素是升序的，并且没有重复
     * @param arr
     * @param value
     * @return
     */
    public static int bsearch(int[] arr, int value){
        if(arr == null || arr.length == 0){
            return -1;
        }

        int low = 0 ;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] == value){
                return mid;
            }else if(arr[mid] < value){
                //需要在当前区间的右侧继续查找
                low = mid+1;
            }else{
                //需要在当前区间的左侧继续查找
                high = mid-1;
            }
        }
        return -1;
    }


    //变形1.查找第一个值等于给定值的元素
    public static int bsearchFirstEq(int[] arr, int value){
        if(arr == null || arr.length == 0){
            return -1;
        }

        int low = 0 ;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] > value){
                high = mid-1;
            }else if(arr[mid] < value){
                low = mid+1;
            }else{
                if(mid == 0 || arr[mid-1] < value){
                    return mid;
                }else{
                    high = mid-1;
                }
            }
        }
        return -1;
    }


    //变形2.查找最后一个值等于给定值的元素
    public static int bsearchLastEq(int[] arr, int value){
        if(arr == null || arr.length == 0){
            return -1;
        }

        int low = 0 ;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] < value){
                low = mid+1;
            }else if(arr[mid] > value){
                high = mid-1;
            }else{
                if(mid == arr.length - 1 || arr[mid+1] > value){
                    return mid;
                }else{
                    low = mid+1;
                }
            }
        }
        return -1;
    }

    //变形3.查找第一个值大于等于给定值的元素
    public static int bsearchLastGE(int[] arr, int value){
        if(arr == null || arr.length == 0){
            return -1;
        }

        int low = 0 ;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] < value){
                low = mid+1;
            }else{
                if(mid == 0 || arr[mid-1] < value){
                    return mid;
                }else{
                    high = mid-1;
                }
            }
        }
        return -1;
    }

    //变形4.查找最后一个值小于等于给定值的元素
    public static int bsearchLastLE(int[] arr, int value){
        if(arr == null || arr.length == 0){
            return -1;
        }

        int low = 0 ;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] > value){
                high = mid-1;
            }else{
                if(mid == arr.length - 1 || arr[mid+1] > value){
                    return mid;
                }else{
                    low = mid+1;
                }
            }
        }
        return -1;
    }



}
